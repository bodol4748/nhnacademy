package com.nhnacademy.student.servlet;

import com.nhnacademy.student.domain.Gender;
import com.nhnacademy.student.domain.Student;
import com.nhnacademy.student.repository.StudentRepository;

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

@WebServlet(name = "studentUpdateServlet", urlPatterns = "/student/update")
public class StudentUpdateServlet extends HttpServlet {
    private StudentRepository studentRepository;
    @Override
    public void init(ServletConfig config) throws ServletException {
        //todo init studentRepository
        super.init(config);
        studentRepository = (StudentRepository) config.getServletContext().getAttribute("studentRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //todo 학생조회
        String id = req.getParameter("id");
        if ( !studentRepository.existById(id) ) {
            throw new IOException("no such student");
        }
        req.setAttribute("student", studentRepository.getStudentById(id));

        //todo forward : /student/register.jsp
//        RequestDispatcher rd = req.getRequestDispatcher("/student/register.jsp");
//        rd.forward(req,resp);

        req.setAttribute("view", "/student/register.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //todo null check
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        Gender gender = req.getParameter("gender").equals("M") ? Gender.M : Gender.F;
        int age = Integer.valueOf(req.getParameter("age"));
        if (Objects.isNull(id) || Objects.isNull(name) || Objects.isNull(gender) || Objects.isNull(age)) {
            throw new IOException("null");
        }
        //todo student 저장

        studentRepository.update(new Student(id, name, gender, age, LocalDateTime.now()));

        //todo /student/view?id=student1 <-- redirect
//        resp.sendRedirect("/student/view?id=" + id);
        req.setAttribute("view", "redirect:/student/view?id=" + id);
    }
}
