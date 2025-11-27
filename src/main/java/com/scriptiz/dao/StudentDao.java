package com.scriptiz.dao;

import com.scriptiz.model.StudentModel;
import jakarta.persistence.*;

import java.util.List;

/**
 * Data Access Object (DAO) pour l'entité StudentModel.
 * Utilise la stratégie de persistance RESOURCE_LOCAL (gestion manuelle des transactions).
 */
public class StudentDao {



    // On crée la Factory une seule fois, au chargement de la classe.
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("studentManagerPU");

    // -------------------- SAVE --------------------
    public void save(StudentModel student) {
        try (EntityManager em = emf.createEntityManager()) {
            EntityTransaction tx = em.getTransaction();
            try {
                tx.begin();
                em.persist(student);
                tx.commit();
            } catch (Exception e) {
        em.getTransaction().rollback();

        if (e.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
            throw new RuntimeException("Cet email existe déjà.");
        }

        throw new RuntimeException("Erreur lors de l'enregistrement de l'étudiant.");
    } finally {
        em.close();
    }
        }
    }

    // -------------------- UPDATE (MERGE) --------------------
    public StudentModel update(StudentModel student) {
        try (EntityManager em = emf.createEntityManager()) {
            EntityTransaction tx = em.getTransaction();
            try {
                tx.begin();
                // On utilise merge pour rattacher l'objet (potentiellement détaché) et le mettre à jour
                StudentModel merged = em.merge(student);
                tx.commit();
                return merged;
            } catch (Exception e) {
                em.getTransaction().rollback();
                throw new RuntimeException("Erreur lors de la mise à jour de l'étudiant.");
            } finally {
                em.close();
            }
        }
    }

    // -------------------- DELETE --------------------
    public void delete(Long id) {
        try (EntityManager em = emf.createEntityManager()) {
            EntityTransaction tx = em.getTransaction();
            try {
                tx.begin();
                // Cherche l'entité
                StudentModel s = em.find(StudentModel.class, id);
                
                // Supprime uniquement si l'entité est trouvée
                if (s != null) {
                    em.remove(s);
                }
                tx.commit();
            } catch (Exception e) {
                if (tx.isActive()) tx.rollback();
                throw new RuntimeException("Erreur lors de la suppression de l'étudiant.", e);
            }
        }
    }

    // -------------------- FIND BY ID--------------------
    public StudentModel findById(Long id) {
        try (EntityManager em = emf.createEntityManager()) {
            // Pas besoin de transaction pour une simple lecture
            return em.find(StudentModel.class, id);
        }
    }

    // -------------------- FIND ALL--------------------
    public List<StudentModel> findAll() {
        try (EntityManager em = emf.createEntityManager()) {
            // Requête JPQL simple pour récupérer tous les étudiants
            return em.createQuery("SELECT s FROM StudentModel s", StudentModel.class)
                    .getResultList();
        }
    }
}