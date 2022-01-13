package com.yjxxt.service;

import com.yjxxt.base.BaseService;

import com.yjxxt.bean.Module;
import com.yjxxt.dto.TreeDto;
import com.yjxxt.mapper.ModuleMapper;
import com.yjxxt.mapper.PermissionMapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class ModuleService extends BaseService<Module,Integer> {

    @Resource
    public ModuleMapper moduleMapper;
    @Resource
    public PermissionMapper permissionMapper;

    public List<TreeDto> findModules(){
        return moduleMapper.selectModules();
    }





    public List<TreeDto> findModulesByRoleId(Integer roleId){
        //获取所有资源信息
        List<TreeDto> slist = moduleMapper.selectModules();
        //获取当前角色的拥有的咨询信息
        List<Integer> roleHasModules = permissionMapper.selectModelByRoleId(roleId);
        //遍历
        for (TreeDto treeDto: slist){
            if (roleHasModules.contains(treeDto.getId())){
                treeDto.setChecked(true);
            }
        }
        //判断比对，checked=true
        return slist;
    }



}
