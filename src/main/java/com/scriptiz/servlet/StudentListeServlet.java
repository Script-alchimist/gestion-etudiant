package com.scriptiz.servlet;

import java.io.IOException;
import jakarta.persistence.PersistenceException;

import com.scriptiz.dao.StudentDao;
import com.scriptiz.model.StudentModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;


@WebServlet("/student-liste")
public class StudentListeServlet extends HttpServlet {
    
    

    private final StudentDao studentDao = new StudentDao();
    public StudentListeServlet() {
        super();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        try{
            List<com.scriptiz.model.StudentModel> students = studentDao.findAll();

            if (students.isEmpty()) {
                studentDao.save(new StudentModel("Durand", "Jean", "ousmane.script@gmail.com", "Licence", java.time.LocalDate.of(1999, 8, 22)));
                students = studentDao.findAll();
            }

            request.setAttribute("students", students);

            request.getRequestDispatcher("/views/student-manager.jsp").forward(request, response);
        }  catch (PersistenceException | IOException e) {
            request.setAttribute("errorMessage", "Erreur lors de l'enregistrement de l'étudiant: " + e.getMessage());
            this.getServletContext().getRequestDispatcher("/View/addstudent.jsp").forward(request, response);
        }

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        // Handle POST request if needed
        
    }
}
