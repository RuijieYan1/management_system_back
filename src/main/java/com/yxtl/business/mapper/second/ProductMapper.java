package com.yxtl.business.mapper.second;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yxtl.business.dto.second.ProductDTO;
import com.yxtl.business.entity.second.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 商品sku表（库存量单元） Mapper 接口
 * </p>
 *
 * @author px
 * @since 2021-11-03
 */
public interface ProductMapper extends BaseMapper<Product> {

    List<ProductDTO> showGood(IPage<ProductDTO> page);

}
