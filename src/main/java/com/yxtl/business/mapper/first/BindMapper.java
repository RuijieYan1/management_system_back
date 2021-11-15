package com.yxtl.business.mapper.first;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yxtl.business.entity.first.Bind;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author px
 * @since 2021-10-18
 */
public interface BindMapper extends BaseMapper<Bind> {

    List<Bind> adminShowBindInfo(IPage<Bind> page);

    List<Bind> adminShowUnBindInfo(IPage<Bind> page);

}
