package com.scriptiz.servlet;

import com.scriptiz.dao.StudentDao;
import com.scriptiz.model.StudentModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/editstudent")
public class EditStudentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        long id = Long.parseLong(request.getParameter("id"));
        StudentDao dao = new StudentDao();
        StudentModel student = dao.findById(id);

        request.setAttribute("student", student);
        request.getRequestDispatcher("/views/addstudent.jsp").forward(request, response);
    }
}
