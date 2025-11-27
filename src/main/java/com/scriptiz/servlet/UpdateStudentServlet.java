package com.scriptiz.servlet;

import java.io.IOException;

import com.scriptiz.dao.StudentDao;
import com.scriptiz.model.StudentModel;

import jakarta.persistence.PersistenceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/student/update")
public class UpdateStudentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            long id = Long.parseLong(request.getParameter("id"));
            StudentDao dao = new StudentDao();

            StudentModel student = dao.findById(id);

            student.setNom(request.getParameter("nom"));
            student.setPrenom(request.getParameter("prenom"));
            student.setEmail(request.getParameter("email"));
            student.setNiveau(request.getParameter("niveau"));
            student.setDateNaissance(java.time.LocalDate.parse(request.getParameter("dateNaissance")));

            dao.update(student);

            response.sendRedirect(request.getContextPath() + "/student-liste");

        } catch (PersistenceException | IOException e) {
            request.setAttribute("errorMessage", "Erreur lors de l'enregistrement de l'étudiant: " + e.getMessage());
            this.getServletContext().getRequestDispatcher("/View/addstudent.jsp").forward(request, response);
        }
    }
}
