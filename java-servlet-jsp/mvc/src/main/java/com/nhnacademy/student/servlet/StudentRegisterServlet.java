package com.nhnacademy.student.servlet;

import com.nhnacademy.student.domain.Gender;
import com.nhnacademy.student.domain.Student;
import com.nhnacademy.student.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

@Slf4j
@WebServlet(name = "studentRegisterServlet", urlPatterns = "/student/register")
public class StudentRegisterServlet extends HttpServlet {

    private StudentRepository studentRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        //todo  init studentRepository
        super.init(config);
        studentRepository = (StudentRepository) config.getServletContext().getAttribute("studentRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //todo  /student/register.jsp forward 합니다.
        log.debug("doGet execute");
//        RequestDispatcher rd = req.getRequestDispatcher("/student/register.jsp");
//        rd.forward(req, resp);
        req.setAttribute("view", "/student/register.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");

        Gender gender = null;
        log.debug(req.getParameter("gender"));
        if(Objects.nonNull(req.getParameter("gender"))){
            gender = req.getParameter("gender").equals("M") ? Gender.M : Gender.F;
        }

        Integer age = null;
        if(Objects.nonNull(req.getParameter("age"))){
            age = Integer.parseInt(req.getParameter("age"));
        }

        if(Objects.isNull(id) || Objects.isNull(name) || Objects.isNull(gender) || Objects.isNull(age)){
            throw new RuntimeException("id,name,gender,age 확인해주세요!");
        }

        Student student = new Student(id,name,gender,age);
        studentRepository.save(student);
//        resp.sendRedirect("/student/view?id="+student.getId());
        req.setAttribute("view", "redirect:/student/view?id="+student.getId());


//        //todo redirect view attribute 설정   resp.sendRedirect("/student/view?id="+student.getId());
//

//        log.debug("doPost execute");
//
//        //todo null check
//        String id = req.getParameter("id");
//        String name = req.getParameter("name");
//
//        log.debug(req.getParameter("gender"));
//        Gender gender = Gender.valueOf(req.getParameter("gender"));
//        int age = Integer.valueOf(req.getParameter("age"));
//        log.debug("set variable");
//
//        if(Objects.isNull(id) || Objects.isNull(name) || Objects.isNull(gender) || Objects.isNull(age)) {
//            log.debug("it is null");
//            throw new IOException("null ");
//        }
//        log.debug("check null");
//
//        //todo save 구현
//        studentRepository.save(new Student(id, name, gender, age, LocalDateTime.now()));
//        log.debug("student saved");
//        //todo redirect /student/view?id=student1
//
//        RequestDispatcher rd = req.getRequestDispatcher("/student/view?id=" + id);
//        log.debug("request dispatcher forward");
//        rd.include(req, resp);
    }

}
