package com.nhnacademy.jdbc.student.repository.impl;

import com.nhnacademy.jdbc.student.domain.Student;
import com.nhnacademy.jdbc.student.repository.StudentRepository;
import com.nhnacademy.jdbc.util.DbUtils;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

@Slf4j
public class StatementStudentRepository implements StudentRepository {

    @Override
    public int save(Student student){
        log.debug("save execute");
        Statement statement;
        int resultSet;
        //todo#1 insert student
        String sql = "insert into jdbc_students values (\'" + student.getId() + "\',\'" + student.getName() + "\',\'" +
                student.getGender().toString() + "\'," + student.getAge().toString() + ",\'" + student.getCreatedAt() + "\')";
        log.debug(sql);
        try {
            statement = DbUtils.getConnection().createStatement();
            resultSet = statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }

    @Override
    public Optional<Student> findById(String id){
        log.debug("findbyid execute");
        //todo#2 student 조회
        Statement statement;
        ResultSet resultSet;
        String sql = "select * from jdbc_students where id = \'" + id + "\'";
        log.debug(sql);
        try {
            statement = DbUtils.getConnection().createStatement();
            resultSet = statement.executeQuery(sql);
            if(resultSet.next()) {
                Student student = new Student(resultSet.getString("id"), resultSet.getString("name"), Student.GENDER.valueOf(resultSet.getString("gender")), resultSet.getInt("age"), resultSet.getTimestamp("created_at").toLocalDateTime());
                return Optional.of(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }

    @Override
    public int update(Student student){
        log.debug("update execute");
        //todo#3 student 수정, name <- 수정합니다.
        Statement statement;
        int result;
        String sql = String.format("UPDATE jdbc_students SET name = '%s', gender = '%s', age = %s WHERE id= '%s'", student.getName(), student.getGender().toString(), student.getAge().toString(), student.getId());
        log.debug(sql);
        try {
            statement = DbUtils.getConnection().createStatement();
            result = statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public int deleteById(String id){
        log.debug("deletebyid execute");
       //todo#4 student 삭제
        Statement statement;
        int result;
        String sql = "DELETE FROM jdbc_students WHERE id=\'" + id + "\'";
        log.debug(sql);
        try {
            statement = DbUtils.getConnection().createStatement();
            result = statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

}
