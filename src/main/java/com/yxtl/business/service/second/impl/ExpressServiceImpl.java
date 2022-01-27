package com.yxtl.business.service.second.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yxtl.business.constant.Constant;
import com.yxtl.business.constant.ResApiFailCode;
import com.yxtl.business.constant.ResApiSuccessCode;
import com.yxtl.business.dto.PublishDTO;
import com.yxtl.business.dto.second.ExpressDTO;
import com.yxtl.business.dto.PageInfoDTO;
import com.yxtl.business.dto.second.NodeDTO;
import com.yxtl.business.dto.second.OfflineOrderDTO;
import com.yxtl.business.entity.second.ProductAddr;
import com.yxtl.business.entity.second.ProductOrderDetail;
import com.yxtl.business.entity.second.ProductTransaction;
import com.yxtl.business.mapper.first.AdminMapper;
import com.yxtl.business.mapper.first.UserMapper;
import com.yxtl.business.mapper.second.CompanyMapper;
import com.yxtl.business.mapper.second.ProductAddrMapper;
import com.yxtl.business.mapper.second.ProductOrderDetailMapper;
import com.yxtl.business.mapper.second.ProductTransactionMapper;
import com.yxtl.business.service.second.ExpressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxtl.business.util.mqtt.Client;
import com.yxtl.business.util.redis.RedisService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author px
 * @since 2021-09-19
 */
@Service
@DS("second")
public class ExpressServiceImpl extends ServiceImpl<ProductTransactionMapper, ProductTransaction> implements ExpressService {

    @Autowired
    AdminMapper adminMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    ProductTransactionMapper productTransactionMapper;

    @Autowired
    CompanyMapper companyMapper;

    @Autowired
    RedisService redisService;

    @Autowired
    ProductAddrMapper productAddrMapper;

    @Autowired
    ProductOrderDetailMapper productOrderDetailMapper;

    @Override
    @DS("second")
    public R expressInfo(Integer currentPage) {
        IPage<ExpressDTO> page = new Page<>(currentPage, 10);
        //查询当前页商品订单信息（已过滤充值订单）
        List<ExpressDTO> lists = productTransactionMapper.showOrderByPage(page);
//        System.out.println("总页数: " + page.getPages());
        System.out.println("物流信息记录总数: " + page.getTotal());
        for (int i = 0; i < lists.size(); i++) {
//            System.out.println("lists.get(i).getLogistics: " + lists.get(i).getLogistics());
            String expNum = lists.get(i).getLogistics();
            Integer status = lists.get(i).getOrderStatus();
            if (status.equals(Constant.WAITTING)) {
                lists.get(i).setShowStatus(Constant.SHOWWAIT);
            }
            if (status.equals(Constant.DELIVER)) {
                lists.get(i).setShowStatus(Constant.SHOWDELIVER);
            }
            if (status.equals(Constant.CANCEL)) {
                lists.get(i).setShowStatus(Constant.SHOWCANCEL);
            }
            if (status.equals(Constant.COMPLETE)) {
                lists.get(i).setShowStatus(Constant.SHOWCOMPLETE);
            }
//            System.out.println("expNum: " + expNum);
            if (null != expNum) {
                String type = expNum.substring(0,2);
                if (type.equals("SF")) {
                    expNum = expNum.substring(0,expNum.length()-5);
                    lists.get(i).setLogistics(expNum);
                }
            }
        }
        PageInfoDTO pageInfoDTO = new PageInfoDTO();
        pageInfoDTO.setExpInfoList(lists);
        pageInfoDTO.setExpTotal(page.getTotal());
        return R.restResult(pageInfoDTO, ResApiSuccessCode.SUCCESS);
    }

    @Override
    @DS("second")
    public R deliverGoods(ExpressDTO expressDTO) {
        ProductTransaction transaction = productTransactionMapper.selectOne(new QueryWrapper<ProductTransaction>().lambda()
                .eq(ProductTransaction::getOrderSn, expressDTO.getOrderSn()));
        System.out.println("发货前: " + transaction);
        String expNum = expressDTO.getLogistics();
        String tel = expressDTO.getTel();
        //订单状态为待发货（状态码为1）才能执行以下操作，否则发货失败
        if (transaction.getOrderStatus().equals(Constant.WAITTING) ) {
            //更新物流编号
            if (expNum.substring(0,2).equals("SF")) {
                expNum = expNum + ":" + tel.substring(tel.length()-4);
                System.out.println("expNum: " + expNum);
                transaction.setLogistics(expNum);
            } else {
                transaction.setLogistics(expNum);
            }
            //更新订单状态
            transaction.setOrderStatus(Constant.DELIVER);
            //更新发货时间
            transaction.setDeliverTime(LocalDateTime.now());
            //更新物流公司
            transaction.setExpName(expressDTO.getExpName());
            //更新发货人
            transaction.setDeliverOperator(expressDTO.getOperator());
            productTransactionMapper.updateById(transaction);
            if (redisService.hasKey("order:" + expressDTO.getOrderSn())) {
                redisService.delete("order:" + expressDTO.getOrderSn());
            }
            if (redisService.hasKey("order:detail:" + expressDTO.getOrderSn())) {
                redisService.delete("order:detail:" + expressDTO.getOrderSn());
            }
            System.out.println("发货后: " + transaction);
            //mqtt发消息提醒公众号
            PublishDTO publishDTO = new PublishDTO();
            publishDTO.setData_type("deliver_goods");
            Map map = new HashMap();
            map.put("order_sn", transaction.getOrderSn());
            map.put("logistics", transaction.getLogistics());
            List<Map> mqttData = new ArrayList<>();
            mqttData.add(map);
            publishDTO.setData(mqttData);
            System.out.println("发送的mqtt数据是: " + publishDTO);
            if (Client.publishCommunication(publishDTO)) {
                System.out.println("发送发货的提示信息成功");
            }
            return R.restResult(null, ResApiSuccessCode.SUCCESS);
        } else {
            return R.restResult(null, ResApiFailCode.ERROR_DELIVER);
        }
    }

    @Override
    public R getAllCompany() {
        List<String> list = companyMapper.searchCompany();
        return R.restResult(list, ResApiSuccessCode.SUCCESS);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public R addOfflineOrder(OfflineOrderDTO offlineOrderDTO) {
        //三个关联表添加数据，添加事务，发生异常时回滚
        String orderSn=offlineOrderDTO.getOrderSn();
        Integer companyId=companyMapper.searchCompanyIdByName(offlineOrderDTO.getCompanyName());
        ProductAddr productAddr=new ProductAddr();
        productAddr.setCompanyId(companyId);
        productAddr.setName(offlineOrderDTO.getName());
        productAddr.setTel(offlineOrderDTO.getTel());
        productAddr.setProvince(offlineOrderDTO.getProvince());
        productAddr.setCity(offlineOrderDTO.getCity());
        productAddr.setCounty(offlineOrderDTO.getCounty());
        productAddr.setAddressDetail(offlineOrderDTO.getAddressDetail());
        productAddrMapper.insert(productAddr);

        ProductTransaction productTransaction=new ProductTransaction();
        productTransaction.setAddrId(productAddr.getId());
        productTransaction.setOrderSn(orderSn);
        //线下用户订单 userId为-1
        productTransaction.setUserId(-1);
        productTransaction.setCompanyId(companyId);
        productTransaction.setOrderStatus(1);
        productTransactionMapper.insert(productTransaction);

        List<Map<String,String>> list=offlineOrderDTO.getProductList();
        ProductOrderDetail productOrderDetail=new ProductOrderDetail();
        for(Map<String,String> map:list){
            Integer productId=Integer.parseInt(map.get("type"));
            Integer number=Integer.parseInt(map.get("number"));
            productOrderDetail.setProductId(productId);
            productOrderDetail.setProductCount(number);
            productOrderDetail.setOrderSn(orderSn);
            productOrderDetailMapper.insert(productOrderDetail);
        }
        return R.restResult(null, ResApiSuccessCode.SUCCESS);
    }

}
