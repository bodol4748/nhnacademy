package com.nhnacademy.student.servlet;


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
import java.util.List;

@Slf4j
@WebServlet(name = "studentListServlet", urlPatterns = "/student/list")
public class StudentListServlet extends HttpServlet {

    private StudentRepository studentRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        studentRepository = (StudentRepository) config.getServletContext().getAttribute("studentRepository");
        log.info("init finished");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //student list 구하기
        log.info("doGet called");
        List<Student> studentList = studentRepository.getStudents();
        req.setAttribute("studentList",studentList);
        log.info("setAttribute");

//         /student/list.jsp <- forward 하기
//        RequestDispatcher rd = req.getRequestDispatcher("/student/list.jsp");
        log.info("forward finished");
//        rd.forward(req, resp);
        req.setAttribute("view", "/student/list.jsp");
    }

}

