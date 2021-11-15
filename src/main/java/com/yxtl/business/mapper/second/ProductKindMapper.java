package com.yxtl.business.mapper.second;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yxtl.business.entity.second.ProductKind;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 商品规格表 Mapper 接口
 * </p>
 *
 * @author px
 * @since 2021-11-03
 */
public interface ProductKindMapper extends BaseMapper<ProductKind> {

    List<ProductKind> showKind(IPage<ProductKind> page);

}
