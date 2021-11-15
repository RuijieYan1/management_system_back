package com.yxtl.business.mapper.second;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yxtl.business.dto.second.ExpressDTO;
import com.yxtl.business.entity.second.ProductTransaction;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author px
 * @since 2021-09-19
 */
public interface ProductTransactionMapper extends BaseMapper<ProductTransaction> {

    List<ExpressDTO> showOrderByPage(IPage<ExpressDTO> page);

}
