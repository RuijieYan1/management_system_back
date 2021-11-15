package com.yxtl.business.service.second.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yxtl.business.constant.Constant;
import com.yxtl.business.constant.ResApiFailCode;
import com.yxtl.business.constant.ResApiSuccessCode;
import com.yxtl.business.dto.LoginIdentityDTO;
import com.yxtl.business.dto.PageInfoDTO;
import com.yxtl.business.dto.PublishDTO;
import com.yxtl.business.dto.second.RechargeDTO;
import com.yxtl.business.entity.second.BankTransfer;
import com.yxtl.business.entity.second.ProductAccount;
import com.yxtl.business.entity.second.ProductTransaction;
import com.yxtl.business.mapper.first.AdminMapper;
import com.yxtl.business.mapper.first.UserMapper;
import com.yxtl.business.mapper.second.BankTransferMapper;
import com.yxtl.business.mapper.second.ProductAccountMapper;
import com.yxtl.business.mapper.second.ProductTransactionMapper;
import com.yxtl.business.service.second.RechargeService;
import com.yxtl.business.util.mqtt.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

/**
 * <p>
 * 充值记录表 服务实现类
 * </p>
 *
 * @author px
 * @since 2021-09-23
 */
@Service
@DS("second")
public class RechargeServiceImpl implements RechargeService {

    @Autowired
    ProductAccountMapper productAccountMapper;

    @Autowired
    BankTransferMapper bankTransferMapper;

    @Autowired
    ProductTransactionMapper productTransactionMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    AdminMapper adminMapper;

    @Override
    @DS("second")
    public R rechargeList(Integer currentPage) {
        IPage<BankTransfer> page = new Page<>(currentPage, 10);
        List<BankTransfer> list = bankTransferMapper.showByPage(page);
        System.out.println("待处理充值订单记录数: " + page.getTotal());
        PageInfoDTO pageInfoDTO = new PageInfoDTO();
        pageInfoDTO.setRechargeInfoList(list);
        pageInfoDTO.setRechargeTotal(page.getTotal());
        return R.restResult(pageInfoDTO, ResApiSuccessCode.SUCCESS);
    }

    @Override
    @DS("second")
    public R offlineRecharge(RechargeDTO rechargeDTO) {
        ProductAccount productAccount = productAccountMapper.selectOne(new QueryWrapper<ProductAccount>().lambda()
                .eq(ProductAccount::getId, rechargeDTO.getAccountId()));
        System.out.println("公司账户信息: " + productAccount);
        if (null != productAccount) {
            //生成订单号:type+时间（到毫秒）+三位随机数：除非在这一毫秒内生成2个，恰好这2个随机的数字还是一样的，才会出现重复的订单号
            SimpleDateFormat sdfTime = new SimpleDateFormat("yyMMddHHmmssSSS");
            SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            String timeStamp = sdfTime.format(date);
            String random = String.format("%03d", (int) Math.floor(Math.random() * 1000));
            String type = Constant.OFFLINE;
            String orderSn = type + timeStamp + random;
            System.out.println("新生成的线下充值订单号是: " + orderSn);
            BigDecimal amount = rechargeDTO.getAmount().setScale(2);
            System.out.println("实际充值金额: " + amount);
            Long accountId = rechargeDTO.getAccountId();
            addAccount(accountId, amount);
            BankTransfer bankTransfer = new BankTransfer();
            bankTransfer.setOrderSn(orderSn);
            bankTransfer.setAccountId(accountId);
            bankTransfer.setRealAmount(amount);
            bankTransfer.setImg(rechargeDTO.getImg());
            bankTransfer.setOperatorName(rechargeDTO.getName());
            bankTransfer.setReviewStatus(Constant.AGREE);
            bankTransfer.setReviewTime(LocalDateTime.now());
            bankTransferMapper.insert(bankTransfer);
            return R.restResult(amount, ResApiSuccessCode.RECHARGE_SUCCESS);
        } else {
            return R.restResult(null, ResApiFailCode.NULL_ACCOUNTID);
        }
    }

    @Override
    @DS("second")
    public R onlineRecharge(RechargeDTO rechargeDTO) {
        ProductAccount productAccount = productAccountMapper.selectOne(new QueryWrapper<ProductAccount>().lambda()
                .eq(ProductAccount::getId, rechargeDTO.getAccountId()));
        System.out.println("公司账户信息: " + productAccount);
        //判断该账户ID是否存在
        if (null != productAccount) {
            BankTransfer bankTransfer = bankTransferMapper.selectOne(new QueryWrapper<BankTransfer>().lambda()
                    .eq(BankTransfer::getOrderSn, rechargeDTO.getOrderSn()));
            System.out.println("充值前-->充值记录信息: " + bankTransfer);
            if (bankTransfer.getReviewStatus() == Constant.WAITTING_REVIEW) {
                BigDecimal amount2 = bankTransfer.getAmount();
                System.out.println("申请充值金额: " + amount2);
                BigDecimal amount1 = rechargeDTO.getAmount().setScale(2);
                System.out.println("实际充值金额: " + amount1);
                Long accountId1 = rechargeDTO.getAccountId();
                Long accountId2 = bankTransfer.getAccountId();
                if (accountId1.equals(accountId2)) {
                    Long accountId = rechargeDTO.getAccountId();
                    addAccount(accountId, amount1);
                    bankTransfer.setImg(rechargeDTO.getImg()).setReviewStatus(Constant.AGREE)
                            .setReviewTime(LocalDateTime.now()).setOperatorName(rechargeDTO.getName()).setRealAmount(amount1);
                    System.out.println("充值后-->充值记录信息: " + bankTransfer);
                    ProductTransaction productTransaction = productTransactionMapper.selectOne(new QueryWrapper<ProductTransaction>().lambda()
                            .eq(ProductTransaction::getOrderSn, rechargeDTO.getOrderSn()));
                    productTransaction.setOrderStatus(Constant.COMPLETE);
                    productTransaction.setCompletionTime(LocalDateTime.now());
                    productTransaction.setPaymentType(Constant.PAYMENT_TYPE);
                    bankTransferMapper.updateById(bankTransfer);
                    productTransactionMapper.updateById(productTransaction);
                    //mqtt发消息提醒公众号
                    PublishDTO publishDTO = new PublishDTO();
                    publishDTO.setData_type("recharge_result");
                    Map map = new HashMap();
                    map.put("order_sn", productTransaction.getOrderSn());
                    if (amount1.equals(amount2)) {
                        map.put("status", Constant.SAME.toString());
                    } else {
                        map.put("status", Constant.UNSAME.toString());
                    }
                    List<Map> mqttData = new ArrayList<>();
                    mqttData.add(map);
                    publishDTO.setData(mqttData);
                    System.out.println("发送的mqtt数据是: " + publishDTO);
                    if (Client.publishCommunication(publishDTO)) {
                        System.out.println("发送充值的提示信息成功");
                    }
                    return R.restResult(amount1, ResApiSuccessCode.RECHARGE_SUCCESS);
                } else {
                    return R.restResult(null, ResApiFailCode.ERROR_ACCOUNT);
                }
            } else {
                return R.restResult(bankTransfer.getReviewStatus(), ResApiFailCode.RECHARGE_WARNING);
            }
        } else {
            return R.restResult(null, ResApiFailCode.NULL_ACCOUNTID);
        }
    }

    @Override
    @DS("second")
    public R addAccount(Long accountId, BigDecimal amount) {
        ProductAccount productAccount = productAccountMapper.selectOne(new QueryWrapper<ProductAccount>().lambda().eq(ProductAccount::getId, accountId));
        if (null != productAccount) {
            BigDecimal balance = productAccount.getBalance();
            System.out.println("充值前余额: " + balance);
            balance = balance.add(amount);
            productAccount.setBalance(balance);
            System.out.println("充值后余额: " + balance);
            productAccountMapper.updateById(productAccount);
            return R.restResult(null, ResApiSuccessCode.RECHARGE_SUCCESS);
        } else {
            return R.restResult(null, ResApiFailCode.RECHARGE_FAILED);
        }
    }

    @Override
    @DS("second")
    public R rechargeRecord(Integer currentPage, LoginIdentityDTO loginIdentityDTO) {
        if (loginIdentityDTO.getIdentify().equals(Constant.ADMIN)) {
            IPage<BankTransfer> page = new Page<>(currentPage, 10);
            List<BankTransfer> list = bankTransferMapper.showAdminRecordByPage(page);
            System.out.println("超管显示的充值记录总数: " + page.getTotal());
            PageInfoDTO pageInfoDTO = new PageInfoDTO();
            pageInfoDTO.setRechargeRecordInfoList(list);
            pageInfoDTO.setRechargeRecordTotal(page.getTotal());
            return R.restResult(pageInfoDTO, ResApiSuccessCode.SUCCESS);
        } else {
            Page tePage = new Page(currentPage, 10);
            QueryWrapper<BankTransfer> queryWrapper = new QueryWrapper<BankTransfer>();
            queryWrapper.eq("review_status", Constant.AGREE);
            queryWrapper.eq("operator_name", loginIdentityDTO.getName());
            IPage<BankTransfer> page = bankTransferMapper.selectPage(tePage, queryWrapper.orderByDesc("id"));
            List<BankTransfer> list = page.getRecords();
            System.out.println("普通管理员显示的记录总数: " + page.getTotal());
            PageInfoDTO pageInfoDTO = new PageInfoDTO();
            pageInfoDTO.setRechargeRecordInfoList(list);
            pageInfoDTO.setRechargeRecordTotal(page.getTotal());
            return R.restResult(pageInfoDTO, ResApiSuccessCode.SUCCESS);
        }
    }

    @Override
    @DS("second")
    public R recordDetail(Integer id) {
        String path = bankTransferMapper.selectById(id).getImg();
        path = path.replaceAll("\\\\", "/");
        return R.restResult(path, ResApiSuccessCode.SUCCESS);
    }

}
