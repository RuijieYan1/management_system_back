package com.yxtl.business.mapper.second;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yxtl.business.entity.second.BankTransfer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author px
 * @since 2021-10-08
 */
public interface BankTransferMapper extends BaseMapper<BankTransfer> {

    List<BankTransfer> showByPage(IPage<BankTransfer> page);

    List<BankTransfer> showAdminRecordByPage(IPage<BankTransfer> page);

}
