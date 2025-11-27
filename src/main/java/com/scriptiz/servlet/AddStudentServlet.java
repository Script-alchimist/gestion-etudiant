package com.scriptiz.servlet;

import java.io.IOException;

import com.scriptiz.model.StudentModel;
import com.scriptiz.dao.StudentDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.persistence.PersistenceException;

@WebServlet("/student/form")
public class AddStudentServlet extends HttpServlet {
    public AddStudentServlet() {
        super();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {

        this.getServletContext().getRequestDispatcher("/views/addstudent.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        try {
            StudentDao studentDao = new StudentDao();
            StudentModel student = new StudentModel();
            
            student.setNom(request.getParameter("nom"));
            student.setPrenom(request.getParameter("prenom"));
            student.setDateNaissance(java.time.LocalDate.parse(request.getParameter("dateNaissance")));
            student.setNiveau(request.getParameter("niveau"));
            student.setEmail(request.getParameter("email"));
            studentDao.save(student);
            response.sendRedirect(request.getContextPath() + "/student-liste");
        } catch (PersistenceException | IOException e) {
            request.setAttribute("errorMessage", "Erreur lors de l'enregistrement de l'étudiant: " + e.getMessage());
            this.getServletContext().getRequestDispatcher("/View/addstudent.jsp").forward(request, response);
        }
    }
}
