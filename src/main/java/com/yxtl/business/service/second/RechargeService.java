package com.yxtl.business.service.second;

import com.baomidou.mybatisplus.extension.api.R;
import com.yxtl.business.dto.LoginIdentityDTO;
import com.yxtl.business.dto.second.RechargeDTO;

import java.math.BigDecimal;

/**
 * <p>
 * 充值记录表 服务类
 * </p>
 *
 * @author px
 * @since 2021-09-23
 */
public interface RechargeService {

    R rechargeList(Integer currentPage);

    R offlineRecharge(RechargeDTO rechargeDTO);

    R onlineRecharge(RechargeDTO rechargeDTO);

    R addAccount(Long accountId, BigDecimal amount);

    R rechargeRecord(Integer currentPage, LoginIdentityDTO loginIdentityDTO);

    R recordDetail(Integer id);

}
