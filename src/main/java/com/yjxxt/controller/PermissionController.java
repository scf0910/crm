package com.yjxxt.controller;

import com.yjxxt.base.BaseController;


import com.yjxxt.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;



@Controller
public class PermissionController extends BaseController {

    @Autowired
    private PermissionService permissionService;

//    @RequestMapping("addGrant")
//    @ResponseBody
//    public ResultInfo grant(Integer roleId, Integer [] mids){
//
//        permissionService.addGrant(roleId,mids);
//        return success("授权成功");
//
//    }
}
