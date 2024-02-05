package com.nhnacademy.jdbc.student.repository.impl;

import com.nhnacademy.jdbc.student.domain.Student;
import com.nhnacademy.jdbc.student.repository.StudentRepository;
import com.nhnacademy.jdbc.util.DbUtils;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.Optional;

@Slf4j
public class PreparedStatementStudentRepository implements StudentRepository {

    @Override
    public int save(Student student){
        //todo#1 학생 등록
        try (PreparedStatement stmt
                     = DbUtils.getConnection().prepareStatement("INSERT INTO  jdbc_students values (?, ?, ?, ?, ?)")) {
            stmt.setString(1, student.getId());
            stmt.setString(2, student.getName());
            stmt.setString(3, student.getGender().toString());
            stmt.setInt(4, student.getAge());
            stmt.setTimestamp(5, java.sql.Timestamp.valueOf(student.getCreatedAt()));
            int rs = stmt.executeUpdate();
            return rs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Student> findById(String id){
        //todo#2 학생 조회
        try (PreparedStatement stmt
                     = DbUtils.getConnection().prepareStatement("SELECT * FROM jdbc_students WHERE id = ?")) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Optional<Student> optionalStudent = Optional.of(new Student(
                        rs.getString("id"),
                        rs.getString("name"),
                        Student.GENDER.valueOf(rs.getString("gender")),
                        rs.getInt("age"),
                        rs.getTimestamp("created_at").toLocalDateTime()
                ));
                return optionalStudent;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public int update(Student student){
        //todo#3 학생 수정 , name 수정
        int rs;
        log.debug("execute update : student date is " + student.toString());
        try (PreparedStatement stmt
                     = DbUtils.getConnection().prepareStatement("UPDATE jdbc_students SET name= ?, gender= ?, age = ?, created_at = ? WHERE id = ?")) {
                stmt.setString(1, student.getName());
                stmt.setString(2, student.getGender().toString());
                stmt.setInt(3, student.getAge());
                stmt.setTimestamp(4, java.sql.Timestamp.valueOf(student.getCreatedAt()));
                stmt.setString(5, student.getId());
                log.debug(stmt.toString());
            rs = stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }

    @Override
    public int deleteById(String id){
        //todo#4 학생 삭제
        int rs;
        try (PreparedStatement stmt
                     = DbUtils.getConnection().prepareStatement("DELETE FROM jdbc_students WHERE id = ?")) {
            stmt.setString(1, id);
            rs = stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rs;
    }

}
