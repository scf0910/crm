package com.yjxxt.mapper;

import com.yjxxt.base.BaseMapper;
import com.yjxxt.bean.Student;

public interface StudentMapper extends BaseMapper<Student,Integer> {

    public Student queryStudentByStudentName(String studentName);
}