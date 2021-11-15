package com.yxtl.business.service.second.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yxtl.business.constant.Constant;
import com.yxtl.business.constant.ResApiFailCode;
import com.yxtl.business.constant.ResApiSuccessCode;
import com.yxtl.business.dto.PageInfoDTO;
import com.yxtl.business.dto.second.KindDTO;
import com.yxtl.business.dto.second.ProductDTO;
import com.yxtl.business.dto.second.ProductSpuDTO;
import com.yxtl.business.entity.second.Product;
import com.yxtl.business.entity.second.ProductKind;
import com.yxtl.business.entity.second.ProductSpu;
import com.yxtl.business.mapper.second.ProductKindMapper;
import com.yxtl.business.mapper.second.ProductMapper;
import com.yxtl.business.mapper.second.ProductSetMapper;
import com.yxtl.business.mapper.second.ProductSpuMapper;
import com.yxtl.business.service.second.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 商品sku表（库存量单元） 服务实现类
 * </p>
 *
 * @author px
 * @since 2021-11-03
 */
@Service
@DS("second")
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Autowired
    ProductMapper productMapper;

    @Autowired
    ProductSpuMapper productSpuMapper;

    @Autowired
    ProductKindMapper productKindMapper;

    @Autowired
    ProductSetMapper productSetMapper;

    @Override
    @DS("second")
    public R getSpu(Integer currentPage) {
        IPage<ProductSpu> page = new Page<>(currentPage, 10);
        List<ProductSpu> list = productSpuMapper.showSpu(page);
        System.out.println("商品类型总数: " + page.getTotal());
        PageInfoDTO pageInfoDTO = new PageInfoDTO();
        pageInfoDTO.setSpuList(list);
        pageInfoDTO.setSpuTotal(page.getTotal());
        return R.restResult(pageInfoDTO, ResApiSuccessCode.SUCCESS);
    }

    @Override
    @DS("second")
    public R spuOnShelf(Integer id) {
        ProductSpu productSpu = productSpuMapper.selectOne(new QueryWrapper<ProductSpu>().lambda().eq(ProductSpu::getId, id));
        productSpu.setIsSale(Constant.ON_SHELF);
        productSpuMapper.updateById(productSpu);
        return R.restResult(null, ResApiSuccessCode.SUCCESS);
    }

    @Override
    @DS("second")
    public R spuOffShelf(Integer id) {
        ProductSpu productSpu = productSpuMapper.selectOne(new QueryWrapper<ProductSpu>().lambda().eq(ProductSpu::getId, id));
        productSpu.setIsSale(Constant.OFF_SHELF);
        productSpuMapper.updateById(productSpu);
        return R.restResult(null, ResApiSuccessCode.SUCCESS);
    }

    @Override
    @DS("second")
    public R addSpu(ProductSpuDTO productSpuDTO) {
        Integer count = productSpuMapper.selectCount(new QueryWrapper<ProductSpu>().lambda().eq(ProductSpu::getName, productSpuDTO.getName()));
        if (0 == count) {
            ProductSpu productSpu = new ProductSpu();
            productSpu.setName(productSpuDTO.getName());
            productSpu.setSeries(productSpuDTO.getSeries());
            productSpu.setSketch(productSpuDTO.getSketch());
            productSpu.setImg(productSpuDTO.getImg());
            productSpu.setMinPrice(productSpuDTO.getMinPrice());
            productSpu.setIsSale(Constant.ON_SHELF);
            productSpuMapper.insert(productSpu);
            return R.restResult(null, ResApiSuccessCode.SUCCESS);
        } else {
            return R.restResult(null, ResApiFailCode.ERROR_SPU);
        }
    }

    @Override
    @DS("second")
    public R editSpu(ProductSpuDTO productSpuDTO) {
        ProductSpu productSpu = productSpuMapper.selectOne(new QueryWrapper<ProductSpu>().lambda().eq(ProductSpu::getId, productSpuDTO.getId()));
        productSpu.setName(productSpuDTO.getName());
        productSpu.setSeries(productSpuDTO.getSeries());
        productSpu.setSketch(productSpuDTO.getSketch());
        productSpu.setImg(productSpuDTO.getImg());
        productSpu.setMinPrice(productSpuDTO.getMinPrice());
        productSpuMapper.updateById(productSpu);
        return R.restResult(null, ResApiSuccessCode.SUCCESS);
    }

    @Override
    @DS("second")
    public R deleteSpu(Integer id) {
        productSpuMapper.deleteById(id);
        return R.restResult(null, ResApiSuccessCode.SUCCESS);
    }

    @Override
    @DS("second")
    public R getKind(Integer currentPage) {
        IPage<ProductKind> page = new Page<>(currentPage, 10);
        List<ProductKind> list = productKindMapper.showKind(page);
        System.out.println("商品种类总数: " + page.getTotal());
        PageInfoDTO pageInfoDTO = new PageInfoDTO();
        pageInfoDTO.setKindList(list);
        pageInfoDTO.setKindTotal(page.getTotal());
        return R.restResult(pageInfoDTO, ResApiSuccessCode.SUCCESS);
    }

    @Override
    @DS("second")
    public R addKind(KindDTO kindDTO) {
        Integer count = productKindMapper.selectCount(new QueryWrapper<ProductKind>().lambda().eq(ProductKind::getName, kindDTO.getName()));
        if (0 == count) {
            ProductKind productKind = new ProductKind();
            productKind.setName(kindDTO.getName());
            productKindMapper.insert(productKind);
            return R.restResult(null, ResApiSuccessCode.SUCCESS);
        } else {
            return R.restResult(null, ResApiFailCode.ERROR_KIND);
        }
    }

    @Override
    @DS("second")
    public R editKind(KindDTO kindDTO) {
        ProductKind productKind = productKindMapper.selectOne(new QueryWrapper<ProductKind>().lambda().eq(ProductKind::getId, kindDTO.getId()));
        productKind.setName(kindDTO.getName());
        productKindMapper.updateById(productKind);
        return R.restResult(null, ResApiSuccessCode.SUCCESS);
    }

    @Override
    @DS("second")
    public R deleteKind(Integer id) {
        productKindMapper.deleteById(id);
        return R.restResult(null, ResApiSuccessCode.SUCCESS);
    }

    @Override
    @DS("second")
    public R getGood(Integer currentPage) {
        IPage<ProductDTO> page = new Page<>(currentPage, 10);
        List<ProductDTO> list = productMapper.showGood(page);
        System.out.println("商品详情总数: " + page.getTotal());
        PageInfoDTO pageInfoDTO = new PageInfoDTO();
        pageInfoDTO.setProductList(list);
        pageInfoDTO.setProductTotal(page.getTotal());
        return R.restResult(pageInfoDTO, ResApiSuccessCode.SUCCESS);
    }

    @Override
    @DS("second")
    public R goodOnShelf(Integer id) {
        Product product = productMapper.selectOne(new QueryWrapper<Product>().lambda().eq(Product::getId, id));
        product.setIsSale(Constant.ON_SHELF);
        product.setUpdatedAt(LocalDateTime.now());
        product.setDeletedAt(null);
        productMapper.updateById(product);
        return R.restResult(null, ResApiSuccessCode.SUCCESS);
    }

    @Override
    @DS("second")
    public R goodOffShelf(Integer id) {
        Product product = productMapper.selectOne(new QueryWrapper<Product>().lambda().eq(Product::getId, id));
        product.setIsSale(Constant.OFF_SHELF);
        product.setUpdatedAt(LocalDateTime.now());
        product.setDeletedAt(LocalDateTime.now());
        productMapper.updateById(product);
        return R.restResult(null, ResApiSuccessCode.SUCCESS);
    }

    @Override
    @DS("second")
    public R addGood(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setSketch(productDTO.getSketch());
        product.setIntro(productDTO.getIntro());
        product.setSeries(productDTO.getSeries());
        product.setSpuId(productDTO.getSpuId());
        product.setKindId(productDTO.getKindId());
        product.setImg(productDTO.getImg());
        product.setPrice(productDTO.getPrice());
        product.setStock(productDTO.getStock());
        product.setCreatedAt(LocalDateTime.now());
        product.setIsSet(productDTO.getIsSet());
        product.setIsSale(Constant.ON_SHELF);
        productMapper.insert(product);
        return R.restResult(null, ResApiSuccessCode.SUCCESS);
    }

    @Override
    @DS("second")
    public R editGood(ProductDTO productDTO) {
        Product product = productMapper.selectOne(new QueryWrapper<Product>().lambda().eq(Product::getId, productDTO.getId()));
        product.setName(productDTO.getName());
        product.setSketch(productDTO.getSketch());
        product.setIntro(productDTO.getIntro());
        product.setSeries(productDTO.getSeries());
        product.setSpuId(productDTO.getSpuId());
        product.setKindId(productDTO.getKindId());
        product.setImg(productDTO.getImg());
        product.setPrice(productDTO.getPrice());
        product.setStock(productDTO.getStock());
        product.setIsSet(productDTO.getIsSet());
        productMapper.updateById(product);
        return R.restResult(null, ResApiSuccessCode.SUCCESS);
    }

    @Override
    @DS("second")
    public R deleteGood(Integer id) {
        productMapper.deleteById(id);
        return R.restResult(null, ResApiSuccessCode.SUCCESS);
    }

}
