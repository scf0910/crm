package com.yjxxt.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjxxt.base.BaseService;
import com.yjxxt.bean.Permission;
import com.yjxxt.bean.Role;
import com.yjxxt.mapper.ModuleMapper;
import com.yjxxt.mapper.PermissionMapper;
import com.yjxxt.mapper.RoleMapper;
import com.yjxxt.query.RoleQuery;
import com.yjxxt.utils.AssertUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;


@Service
public class RoleService extends BaseService<Role,Integer> {

    @Autowired(required = false)
    private ModuleMapper moduleaMapper;

    @Autowired(required = false)
    private PermissionMapper permissionMapper;

    @Autowired(required = false)
    private RoleMapper roleMapper;

    /**
     * 角色的条件查以及分页
     * @param roleQuery
     * @return
     */
    public Map<String,Object> findRoleByParams(RoleQuery roleQuery){
        //实例化Map
        Map<String,Object> map=new HashMap<String,Object>();//开启分页单位
        PageHelper.startPage(roleQuery.getPage(),roleQuery.getLimit());
        PageInfo<Role> rlist=new PageInfo<>(selectByParams(roleQuery));
        //准备数据
        map.put("code",0);
        map.put("msg","success");
        map.put("count",rlist.getTotal());
        map.put("data",rlist.getList());
        //返回目标map
        return map;
    }


    /**
     * 授权
     *  原来有的资源
     *       新增
     *       删除一部分
     *       避免添加重复
     *  原来没有的部分
     * @param roleId
     * @param mids
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addGrant(Integer roleId,Integer [] mids){

        AssertUtil.isTrue(roleId==null || roleMapper.selectByPrimaryKey(roleId)==null,"请选择角色");
        List<Permission> plist=new ArrayList<Permission>();

        //统计当前角色的资源数量
        int count=permissionMapper.countRoleModulesByRoleId(roleId);
        if (count>0){
            //删除角色的资源信息
            AssertUtil.isTrue(permissionMapper.deleteRoleModuleByRoleId(roleId)!=count,"角色资源分配失败");
        }
        if (mids!=null && mids.length>0){
            //遍历mids
            for(Integer mid:mids){
                //实例化对象
                Permission permission=new Permission();
                permission.setRoleId(roleId);
                permission.setModuleId(mid);
                //
                //权限码
                permission.setAclValue(moduleaMapper.selectByPrimaryKey(mid).getOptValue());
                permission.setCreateDate(new Date());
                permission.setUpdateDate(new Date());
                plist.add(permission);
            }
        }

        AssertUtil.isTrue(permissionMapper.insertBatch(plist)!=plist.size(),"授权失败");

    }
}
