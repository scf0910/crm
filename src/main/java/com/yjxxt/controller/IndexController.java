package com.yjxxt.controller;

import com.yjxxt.base.BaseController;
import com.yjxxt.bean.Student;
import com.yjxxt.service.PermissionService;
import com.yjxxt.service.StudentService;
import com.yjxxt.utils.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class IndexController extends BaseController  {

    @Autowired
    private StudentService studentService;

    @Autowired
    private PermissionService permissionService;


    @RequestMapping("index")
    public String index(){
        return "index";
    }


    @RequestMapping("main")
    public String main(HttpServletRequest request){
        //通过工具类，从cookie获取studentId
        Integer studentId= LoginUserUtil.releaseUserIdFromCookie(request);
        //调用service层方法，通过studentId主键查询用户对象
        Student stu=studentService.selectByPrimaryKey(studentId);
        //存储
        request.setAttribute("student",stu);
        //将用户的资源权限码存储到Session中
        List<String> permissions = permissionService.queryStudentHasRolesHasPermissions(studentId);
        //将用户的权限存储到session作用域
        request.getSession().setAttribute("permissions",permissions);
        //转发
        return "main";
    }


    @RequestMapping("welcome")
    public String welcome(){
        return "welcome";
    }



}
