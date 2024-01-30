package com.nhnacademy.student.listner;

import com.nhnacademy.student.domain.Gender;
import com.nhnacademy.student.domain.Student;
import com.nhnacademy.student.repository.MapStudentRepository;
import com.nhnacademy.student.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.time.LocalDateTime;
import java.util.Random;

@Slf4j
@WebListener
public class WebApplicationListener implements ServletContextListener {

    Random rand = new Random();
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        StudentRepository studentRepository = new MapStudentRepository();
        for(int i=1; i<=10; i++){
            // ... student 1 ~ 10 생성하기
            // 나이 : random 처리 : 20~30
            log.info("saving student : " + Integer.toString(i));
            studentRepository.save(new Student(Integer.toString(i), "student" + Integer.toString(i), i%2 == 0 ? Gender.F : Gender.M, rand.nextInt(10) + 10, LocalDateTime.now()));
        }
        // ... application scope에서 studentRepository 객체에 접근할 수 있도록 구현하기
        context.setAttribute("studentRepository",studentRepository);
    }
}
