package com.yxtl.business.mapper.first;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yxtl.business.dto.second.ExpressDTO;
import com.yxtl.business.entity.first.User;
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
public interface UserMapper extends BaseMapper<User> {

    List<User> showInfoByPage(IPage<User> page);

}
