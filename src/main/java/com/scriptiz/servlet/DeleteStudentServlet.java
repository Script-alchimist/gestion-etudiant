package com.scriptiz.servlet;

import com.scriptiz.dao.StudentDao;

import jakarta.persistence.PersistenceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/deletestudent")
public class DeleteStudentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            Long id = Long.parseLong(request.getParameter("id"));

            new StudentDao().delete(id);

            response.sendRedirect(request.getContextPath() + "/student-liste");

        } catch (PersistenceException | IOException e) {
            request.setAttribute("errorMessage", "Erreur lors de la suppression de l'étudiant: " + e.getMessage());
            this.getServletContext().getRequestDispatcher("/View/student-manager.jsp").forward(request, response);
        }
    }
}
