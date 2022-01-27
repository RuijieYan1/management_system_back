package com.yxtl.business.mapper.second;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.yxtl.business.dto.second.CompanyDTO;
import com.yxtl.business.dto.second.NodeDTO;
import com.yxtl.business.entity.second.Company;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 公司表 Mapper 接口
 * </p>
 *
 * @author px
 * @since 2021-09-23
 */
public interface CompanyMapper extends BaseMapper<Company> {

    List<String> searchCompany();

    List<CompanyDTO> showCompanyByPage(IPage<CompanyDTO> page);

    List<NodeDTO> searchNodeByCompany(String companyName);

    List<String> searchCompanyByName(String companyName);

    List<String> searchCompanyUser(@Param("userName") String userName,
                                   @Param("companyName") String companyName);

    Integer searchCompanyIdByName(String companyName);

}
