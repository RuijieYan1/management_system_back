package com.yxtl.business.service.second;

import com.baomidou.mybatisplus.extension.api.R;
import com.yxtl.business.dto.second.KindDTO;
import com.yxtl.business.dto.second.ProductDTO;
import com.yxtl.business.dto.second.ProductSpuDTO;
import com.yxtl.business.dto.second.SuitDTO;
import com.yxtl.business.entity.second.Product;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 商品sku表（库存量单元） 服务类
 * </p>
 *
 * @author px
 * @since 2021-11-03
 */
public interface ProductService extends IService<Product> {

    R getSpu(Integer currentPage);

    R spuOnShelf(Integer id);

    R spuOffShelf(Integer id);

    R addSpu(ProductSpuDTO productSpuDTO);

    R editSpu(ProductSpuDTO productSpuDTO);

    R deleteSpu(Integer id);

    R getKind(Integer currentPage);

    R addKind(KindDTO kindDTO);

    R editKind(KindDTO kindDTO);

    R deleteKind(Integer id);

    R getGood(Integer currentPage);

    R goodOnShelf(Integer id);

    R goodOffShelf(Integer id);

    R addGood(ProductDTO productDTO);

    R editGood(ProductDTO productDTO);

    R deleteGood(Integer id);

    R showProduct(Integer currentPage);

    R showSuit();

    R addSuit(SuitDTO suitDTO);

}
