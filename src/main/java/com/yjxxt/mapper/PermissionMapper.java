package com.yjxxt.mapper;
import com.yjxxt.base.BaseMapper;
import com.yjxxt.bean.Permission;
import java.util.List;

public interface PermissionMapper extends BaseMapper<Permission,Integer> {


    List<String> selectStudentHasRolesHasPermissions(Integer studentId);

    int deleteRoleModuleByRoleId(Integer roleId);

    int countRoleModulesByRoleId(Integer roleId);

    List<Integer> selectModelByRoleId(Integer roleId);
}