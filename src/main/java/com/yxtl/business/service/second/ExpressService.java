package com.yxtl.business.service.second;

import com.baomidou.mybatisplus.extension.api.R;
import com.yxtl.business.dto.second.ExpressDTO;
import com.yxtl.business.entity.second.ProductTransaction;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author px
 * @since 2021-09-19
 */
public interface ExpressService extends IService<ProductTransaction> {

    R expressInfo(Integer currentPage);

    R deliverGoods(ExpressDTO expressDTO);

}
