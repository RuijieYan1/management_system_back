package com.yxtl.business.mapper.second;

import com.yxtl.business.entity.second.ProductAccount;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 公司账户表 Mapper 接口
 * </p>
 *
 * @author px
 * @since 2021-09-24
 */
public interface ProductAccountMapper extends BaseMapper<ProductAccount> {

    List<Integer> searchAccount(@Param("id") Integer id);

}
