package com.yuxiu.edu.service.impl;

import com.yuxiu.edu.model.Student;
import com.yuxiu.edu.service.IStudentService;
import com.yuxiu.edu.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class StudentImpl extends BaseServiceImpl<Student> implements IStudentService {
    @Override
    public Student findById(Integer id) {
        return null;
    }

    @Override
    public Student findByUUID(String uuid) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void deleteByUUID(String uuid) {

    }

    @Override
    public void update(Student student) {

    }

    @Override
    public void insert(Student student) {

    }
}
