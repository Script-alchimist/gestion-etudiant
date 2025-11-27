<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>

<!DOCTYPE html>

<html lang="fr">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <script src="https://cdn.jsdelivr.net/npm/@tailwindcss/browser@4"></script>
        <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    <title>
        <c:choose>
            <c:when test="${!empty student.id}">Modifier l'Étudiant</c:when>
            <c:otherwise>Ajouter un Étudiant</c:otherwise>
        </c:choose>
    </title>
    <style>
        body {
            font-family: 'Inter', sans-serif;
        }
    </style>
</head>

<body class="bg-gray-50 p-6">

    <div class="max-w-xl mx-auto bg-white shadow-2xl rounded-xl p-8 mt-10">

        <!-- Le titre change selon le mode (Ajout ou Modification) -->
        <h1 class="text-3xl font-extrabold text-indigo-700 mb-6 border-b pb-2">
            <c:choose>
                <c:when test="${!empty student.id}">Modification de l'Étudiant</c:when>
                <c:otherwise>Ajouter un Nouvel Étudiant</c:otherwise>
            </c:choose>
        </h1>

        <!-- Affichage du message d'erreur/validation -->
        <c:if test="${!empty errorMessage}">
            <div class="mb-4 p-4 bg-red-100 border-l-4 border-red-500 text-red-700 rounded-md" role="alert">
                <p class="font-bold">Erreur :</p>
                <p>${errorMessage}</p>
            </div>
        </c:if>

        <!-- Le formulaire envoie les données en POST au servlet /student/form -->
        <form 
            action="${pageContext.request.contextPath}${empty student.id ? '/student/form' : '/student/update'}"
            method="POST"
            class="space-y-6"
        >

            <!-- Champ caché pour l'ID -->
            <c:if test="${!empty student.id}">
                <input type="hidden" name="id" value="${student.id}" />
            </c:if>

            <!-- Champ Prénom -->
            <div>
                <label for="prenom" class="block text-sm font-medium text-gray-700">Prénom</label>
                <input type="text" id="prenom" name="prenom" required value="${student.prenom}"
                    class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm">
            </div>

            <!-- Champ Nom -->
            <div>
                <label for="nom" class="block text-sm font-medium text-gray-700">Nom</label>
                <input type="text" id="nom" name="nom" required value="${student.nom}"
                    class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm">
            </div>
            
            <!-- Champ Date de Naissance -->
            <div>
                <label for="dateNaissance" class="block text-sm font-medium text-gray-700">Date de Naissance</label>
                ${student.dateNaissance.toString()}
                <input type="date" id="dateNaissance" name="dateNaissance" required value="${student.dateNaissance}"
                    class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm">
            </div>

            <div>
                <label for="niveau" class="block text-sm font-medium text-gray-700">Niveau</label>
                <input type="text" id="niveau" name="niveau" required value="${student.niveau}"
                    class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm">
            </div>

            <!-- Champ Email -->
            <div>
                <label for="email" class="block text-sm font-medium text-gray-700">Email</label>
                <input type="email" id="email" name="email" required value="${student.email}"
                    class="mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm">
            </div>

            <div class="flex justify-between items-center pt-4">
                <!-- Bouton de soumission -->
                <button type="submit"
                    class="px-4 py-2 font-semibold text-white bg-indigo-600 rounded-lg shadow-md hover:bg-indigo-700 transition duration-150 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500">
                    <c:choose>
                        <c:when test="${!empty student.id}">Mettre à Jour</c:when>
                        <c:otherwise>Enregistrer</c:otherwise>
                    </c:choose>
                </button>

                <!-- Lien de retour à la liste -->
                <a href="${pageContext.request.contextPath}/studentlist"
                    class="text-sm font-medium text-gray-600 hover:text-gray-800 transition duration-150">
                    Annuler
                </a>
            </div>
        </form>
    </div>

</body>

</html>