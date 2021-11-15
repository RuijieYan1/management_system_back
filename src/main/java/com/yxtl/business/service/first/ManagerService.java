package com.yxtl.business.service.first;

import com.baomidou.mybatisplus.extension.api.R;
import com.yxtl.business.dto.first.UserDTO;
import com.yxtl.business.entity.first.User;

/**
 * @author px
 * @date 2021/9/22 9:34
 */
public interface ManagerService {

    R allUserInfo(Integer currentPage);

    R addUser(UserDTO userDTO);

    R editUser(User user);

    R deleteUser(Integer id);

}
