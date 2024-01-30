package com.nhnacademy.student.repository;

import com.nhnacademy.student.domain.Student;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class MapStudentRepository implements StudentRepository{
    private Map<String, Student> studentsMap = new ConcurrentHashMap<>();

    @Override
    public void save(Student student) {
        studentsMap.put(student.getId(), student);
    }

    @Override
    public void update(Student student) {
        studentsMap.replace(student.getId(), student);
    }

    @Override
    public void deleteById(String id) {
        studentsMap.remove(id);
    }

    @Override
    public Student getStudentById(String id) {
        return studentsMap.get(id);
    }

    @Override
    public List<Student> getStudents() {
        log.info("getStudents called");
        log.info(studentsMap.values().toString());
        List<Student> studentlist = new ArrayList<>(studentsMap.values());
        return studentlist;
    }

    @Override
    public boolean existById(String id) {
        return studentsMap.containsKey(id);
    }
    // ...
}