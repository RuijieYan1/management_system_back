package com.yxtl.business.controller.second;


import com.baomidou.mybatisplus.extension.api.R;
import com.yxtl.business.dto.second.KindDTO;
import com.yxtl.business.dto.second.ProductDTO;
import com.yxtl.business.dto.second.ProductSpuDTO;
import com.yxtl.business.dto.second.SuitDTO;
import com.yxtl.business.service.second.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 商品sku表（库存量单元） 前端控制器
 * </p>
 *
 * @author px
 * @since 2021-11-03
 */
@Api(value = "商品管理", tags = {"商品管理"})
@Slf4j
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @ApiOperation(value = "型号信息", notes = "型号信息")
    @ApiResponses({
            @ApiResponse(code = 200, message = "执行成功"),
            @ApiResponse(code = -1, message = "出错了")
    })
    @GetMapping("/spu")
    public R getSpu(@RequestParam Integer currentPage) {
        return productService.getSpu(currentPage);
    }

    @ApiOperation(value = "型号上架", notes = "型号上架")
    @ApiResponses({
            @ApiResponse(code = 200, message = "执行成功"),
            @ApiResponse(code = -1, message = "出错了")
    })
    @PostMapping("/spuOnShelf")
    public R spuOnShelf(@RequestParam Integer id) {
        return productService.spuOnShelf(id);
    }

    @ApiOperation(value = "型号下架", notes = "型号下架")
    @ApiResponses({
            @ApiResponse(code = 200, message = "执行成功"),
            @ApiResponse(code = -1, message = "出错了")
    })
    @PostMapping("/spuOffShelf")
    public R spuOffShelf(@RequestParam Integer id) {
        return productService.spuOffShelf(id);
    }

    @ApiOperation(value = "新增型号", notes = "新增型号")
    @ApiResponses({
            @ApiResponse(code = 200, message = "执行成功"),
            @ApiResponse(code = -1, message = "出错了")
    })
    @PostMapping("/addSpu")
    public R addSpu(ProductSpuDTO productSpuDTO) {
        return productService.addSpu(productSpuDTO);
    }

    @ApiOperation(value = "编辑型号", notes = "编辑型号")
    @ApiResponses({
            @ApiResponse(code = 200, message = "执行成功"),
            @ApiResponse(code = -1, message = "出错了")
    })
    @PostMapping("/editSpu")
    public R editSpu(ProductSpuDTO productSpuDTO) {
        return productService.editSpu(productSpuDTO);
    }

    @ApiOperation(value = "删除型号", notes = "删除型号")
    @ApiResponses({
            @ApiResponse(code = 200, message = "执行成功"),
            @ApiResponse(code = -1, message = "出错了")
    })
    @DeleteMapping("/deleteSpu/{id}")
    public R deleteSpu(@PathVariable Integer id) {
        return productService.deleteSpu(id);
    }

    @ApiOperation(value = "规格信息", notes = "规格信息")
    @ApiResponses({
            @ApiResponse(code = 200, message = "执行成功"),
            @ApiResponse(code = -1, message = "出错了")
    })
    @GetMapping("/kind")
    public R getKind(@RequestParam Integer currentPage) {
        return productService.getKind(currentPage);
    }

    @ApiOperation(value = "新增规格", notes = "新增规格")
    @ApiResponses({
            @ApiResponse(code = 200, message = "执行成功"),
            @ApiResponse(code = -1, message = "出错了")
    })
    @PostMapping("/addKind")
    public R addKind(@RequestBody KindDTO kindDTO) {
        return productService.addKind(kindDTO);
    }

    @ApiOperation(value = "编辑规格", notes = "编辑规格")
    @ApiResponses({
            @ApiResponse(code = 200, message = "执行成功"),
            @ApiResponse(code = -1, message = "出错了")
    })
    @PostMapping("/editKind")
    public R editKind(@RequestBody KindDTO kindDTO) {
        return productService.editKind(kindDTO);
    }

    @ApiOperation(value = "删除规格", notes = "删除规格")
    @ApiResponses({
            @ApiResponse(code = 200, message = "执行成功"),
            @ApiResponse(code = -1, message = "出错了")
    })
    @DeleteMapping("/deleteKind/{id}")
    public R deleteKind(@PathVariable Integer id) {
        return productService.deleteKind(id);
    }

    @ApiOperation(value = "商品信息", notes = "商品信息")
    @ApiResponses({
            @ApiResponse(code = 200, message = "执行成功"),
            @ApiResponse(code = -1, message = "出错了")
    })
    @GetMapping("/good")
    public R getGood(@RequestParam Integer currentPage) {
        return productService.getGood(currentPage);
    }

    @ApiOperation(value = "型号上架", notes = "型号上架")
    @ApiResponses({
            @ApiResponse(code = 200, message = "执行成功"),
            @ApiResponse(code = -1, message = "出错了")
    })
    @PostMapping("/goodOnShelf")
    public R goodOnShelf(@RequestParam Integer id) {
        return productService.goodOnShelf(id);
    }

    @ApiOperation(value = "型号下架", notes = "型号下架")
    @ApiResponses({
            @ApiResponse(code = 200, message = "执行成功"),
            @ApiResponse(code = -1, message = "出错了")
    })
    @PostMapping("/goodOffShelf")
    public R goodOffShelf(@RequestParam Integer id) {
        return productService.goodOffShelf(id);
    }

    @ApiOperation(value = "新增商品", notes = "新增商品")
    @ApiResponses({
            @ApiResponse(code = 200, message = "执行成功"),
            @ApiResponse(code = -1, message = "出错了")
    })
    @PostMapping("/addGood")
    public R addGood(ProductDTO productDTO) {
        return productService.addGood(productDTO);
    }

    @ApiOperation(value = "新增商品", notes = "新增商品")
    @ApiResponses({
            @ApiResponse(code = 200, message = "执行成功"),
            @ApiResponse(code = -1, message = "出错了")
    })
    @PostMapping("/editGood")
    public R editGood(ProductDTO productDTO) {
        return productService.editGood(productDTO);
    }

    @ApiOperation(value = "删除商品", notes = "删除商品")
    @ApiResponses({
            @ApiResponse(code = 200, message = "执行成功"),
            @ApiResponse(code = -1, message = "出错了")
    })
    @DeleteMapping("/deleteGood/{id}")
    public R deleteGood(@PathVariable Integer id) {
        return productService.deleteGood(id);
    }

    @ApiOperation(value = "商品列表", notes = "商品列表")
    @ApiResponses({
            @ApiResponse(code = 200, message = "执行成功"),
            @ApiResponse(code = -1, message = "出错了")
    })
    @GetMapping("/showProduct")
    public R showProduct(Integer currentPage) {
        return productService.showProduct(currentPage);
    }

    @ApiOperation(value = "套装列表", notes = "套装列表")
    @ApiResponses({
            @ApiResponse(code = 200, message = "执行成功"),
            @ApiResponse(code = -1, message = "出错了")
    })
    @GetMapping("/showSuit")
    public R showSuit() {
        return productService.showSuit();
    }

    @ApiOperation(value = "添加套装商品", notes = "添加套装商品")
    @ApiResponses({
            @ApiResponse(code = 200, message = "执行成功"),
            @ApiResponse(code = -1, message = "出错了")
    })
    @PostMapping("/addSuit")
    public R addSuit(@RequestBody SuitDTO suitDTO) {
        return productService.addSuit(suitDTO);
    }

}

