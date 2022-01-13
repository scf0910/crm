package com.yjxxt.service;


import com.yjxxt.base.BaseService;
import com.yjxxt.bean.Student;
import com.yjxxt.mapper.StudentMapper;
import com.yjxxt.model.StudentModel;
import com.yjxxt.utils.AssertUtil;
import com.yjxxt.utils.Md5Util;
import com.yjxxt.utils.UserIDBase64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StudentService extends BaseService<Student,Integer> {


           @Resource
           private StudentMapper studentmapper;
        /*** 用户登录 *
         *  @param studentName
         *  *
         *  @param studentPwd
         *
         *  * @return */
           public StudentModel studentLogin(String studentName, String studentPwd) {
            // 1. 验证参数
            checkLoginParams(studentName, studentPwd);
            // 2. 根据用户名，查询用户对象
            Student student = studentmapper.queryStudentByStudentName(studentName);
            // 3. 判断用户是否存在 (用户对象为空，记录不存在，方法结束)
            AssertUtil.isTrue(null == student, "用户不存在或已注销！");
            //4. 用户对象不为空（用户存在，校验密码。密码不正确，方法结束）
            checkLoginPwd(studentPwd, student.getStudentPwd());
            // 5. 密码正确（用户登录成功，返回用户的相关信息）
            return buildStudentInfo(student);
        }
        /*** 构建返回的用户信息
         *
         * *
         * @param student
         * * @return
         * */
            private StudentModel buildStudentInfo(Student student) {
                StudentModel studentModel = new StudentModel();
                // 设置用户信息
                studentModel.setStudentIdStr(UserIDBase64.encoderUserID(student.getId()));
                studentModel.setStudentName(student.getStudentName());
                return studentModel;
            }
            /*** 验证登录密码
             * *
             * @param
             * studentPwd
             * 前台传递的密码 *
             * @param spwd
             * 数据库中查询到的密码 *
             * @return
             * */
            private void checkLoginPwd(String studentPwd, String spwd) {
                // 数据库中的密码是经过加密的，将前台传递的密码先加密，再与数据库中的密码作比较
                studentPwd = Md5Util.encode(spwd);
                // 比较密码
                AssertUtil.isTrue(!studentPwd.equals(spwd), "用户密码不正确！");
            }
                /*** 验证用户登录参数 * @param userName * @param userPwd */
                private void checkLoginParams(String studentName, String studentPwd) {
                    // 判断姓名
                    AssertUtil.isTrue(StringUtils.isBlank(studentName), "用户姓名不能为空！");
                    // 判断密码
                    AssertUtil.isTrue(StringUtils.isBlank(studentPwd), "用户密码不能为空！");

                }
    }





