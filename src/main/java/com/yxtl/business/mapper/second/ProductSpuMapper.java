package com.yxtl.business.mapper.second;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yxtl.business.entity.second.ProductSpu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 商品的标准化产品单元spu表(商品种类) Mapper 接口
 * </p>
 *
 * @author px
 * @since 2021-11-03
 */
public interface ProductSpuMapper extends BaseMapper<ProductSpu> {

    List<ProductSpu> showSpu(IPage<ProductSpu> page);

}
