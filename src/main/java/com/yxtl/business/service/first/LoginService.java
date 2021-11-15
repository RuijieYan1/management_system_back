package com.yxtl.business.service.first;

import com.baomidou.mybatisplus.extension.api.R;
import com.yxtl.business.dto.first.LoginDTO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author px
 * @since 2021-09-19
 */
public interface LoginService {

    R adminLogin(LoginDTO loginDTO);

    R userLogin(LoginDTO loginDTO);

}
