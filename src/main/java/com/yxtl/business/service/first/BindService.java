package com.yxtl.business.service.first;

import com.baomidou.mybatisplus.extension.api.R;
import com.yxtl.business.dto.LoginIdentityDTO;
import com.yxtl.business.entity.first.Bind;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author px
 * @since 2021-10-18
 */
public interface BindService extends IService<Bind> {

    R bindNotes(Bind bind);

    R unBindNotes(Bind bind);

    R bindRecords(Integer currentPage, LoginIdentityDTO loginIdentityDTO);

    R unBindRecords(Integer currentPage, LoginIdentityDTO loginIdentityDTO);

    R bindRecordDetail(Integer id);

}
