📚 Student Management System (Système de Gestion des Étudiants)

Ce projet est une application web de gestion d'étudiant développée en Java utilisant les technologies Jakarta EE (Servlets, JSP, JSTL, JPA) pour gérer une liste d'étudiants.

L'application suit une architecture MVC (Modèle-Vue-Contrôleur) de base, où :

Modèle (Model) : Classes StudentModel (Entité JPA) et StudentDAO (Accès aux données).

Vue (View) : Fichiers JSP .

Contrôleur (Controller) : Servlets (StudentListServlet, AddStudentServlet, etc.).

🚀 1. Technologies Utilisées

Technologie

Version / Rôle

Langage

Java (17 ou supérieur)

Serveur

Apache Tomcat 10+ ou équivalent Jakarta EE

Base de Données

MySQL / MariaDB

Persistance

Jakarta Persistence API (JPA 3.0) avec Hibernate

Frontend

JSP, JSTL (3.0), HTML, Tailwind CSS (CDN)

Gestion des Dépendances

Maven

⚙️ 2. Prérequis

Pour compiler et exécuter ce projet, vous aurez besoin des éléments suivants installés sur votre machine :

JDK (Java Development Kit) : Version 17 ou plus récente.

Apache Maven : Pour gérer les dépendances et la construction du projet.

Serveur d'Applications : Apache Tomcat 10 ou 11 (compatible Jakarta Servlet 5.0/6.0) est requis.

Base de Données MySQL : Un serveur MySQL en cours d'exécution.

Un IDE : (IntelliJ IDEA, Eclipse, VS Code) pour l'édition et le déploiement.

💾 3. Configuration de la Base de Données

Ce projet utilise JPA en mode RESOURCE_LOCAL, ce qui signifie que les informations de connexion sont directement gérées par l'application via le fichier persistence.xml.

Étape 3.1 : Création de la Base

Créez une base de données MySQL.

CREATE DATABASE IF NOT EXISTS student_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;


Étape 3.2 : Fichier persistence.xml

Vérifiez et ajustez le fichier src/main/resources/META-INF/persistence.xml pour qu'il corresponde à votre environnement MySQL.

Chemin : src/main/resources/META-INF/persistence.xml

<?xml version="1.0" encoding="UTF-8"?>
<persistence xmlns="https://jakarta.ee/xml/ns/persistence"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="https://jakarta.ee/xml/ns/persistence https://jakarta.ee/xml/ns/persistence/persistence_3_0.xsd"
             version="3.0">

    <persistence-unit name="studentManagerPU" transaction-type="RESOURCE_LOCAL">

        <provider>org.hibernate.jpa.HibernatePersistenceProvider</provider>

        <class>com.scriptiz.model.StudentModel</class>

        <properties>

            <!-- Connexion DB -->
            <property name="jakarta.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/etudiantdb?useSSL=false&amp;serverTimezone=UTC"/>
            <property name="jakarta.persistence.jdbc.user" value="root"/>
            <property name="jakarta.persistence.jdbc.password" value=""/>
            <property name="jakarta.persistence.jdbc.driver" value="com.mysql.cj.jdbc.Driver"/>

            <!-- Hibernate -->
            <property name="hibernate.hbm2ddl.auto" value="update"/>
            <property name="hibernate.dialect" value="org.hibernate.dialect.MySQL8Dialect"/>
            <property name="hibernate.show_sql" value="true"/>
            <property name="hibernate.format_sql" value="true"/>

        </properties>

    </persistence-unit>

</persistence>



📦 4. Dépendances Maven (pom.xml)

Le projet nécessite les dépendances suivantes, qui doivent être présentes dans votre pom.xml :

Servlet API (pour Tomcat 10+)

JSP/JSTL (pour Jakarta Tags)

JPA API

Hibernate (Implémentation JPA)

MySQL Connector/J (Driver JDBC)

Assurez-vous que vos versions sont compatibles avec Jakarta EE 9+ (ou 10 si vous utilisez Tomcat 11).

Exemple des Dépendances Clés :

<dependencies>
    <!-- Servlet API (Scope: provided par Tomcat) -->
    <dependency>
        <groupId>jakarta.servlet</groupId>
        <artifactId>jakarta.servlet-api</artifactId>
        <version>6.0.0</version>
        <scope>provided</scope>
    </dependency>
    
    <!-- JSTL pour les balises c:if, c:forEach, etc. -->
    <dependency>
        <groupId>org.glassfish.web</groupId>
        <artifactId>jakarta.servlet.jsp.jstl</artifactId>
        <version>3.0.1</version>
    </dependency>

    <!-- Jakarta Persistence API (JPA) -->
    <dependency>
        <groupId>jakarta.persistence</groupId>
        <artifactId>jakarta.persistence-api</artifactId>
        <version>3.1.0</version>
    </dependency>
    
    <!-- Implémentation JPA (Hibernate) -->
    <dependency>
        <groupId>org.hibernate.orm</groupId>
        <artifactId>hibernate-core</artifactId>
        <version>6.4.4.Final</version>
    </dependency>

    <!-- MySQL Driver -->
    <dependency>
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
        <version>8.0.33</version>
    </dependency>
</dependencies>


🏃 5. Compilation et Exécution

Compiler le projet :
Ouvrez un terminal à la racine du projet et exécutez :

mvn clean package


Ceci créera le fichier WAR (.war) dans le dossier target/.

Déploiement sur Tomcat :

Lancez votre serveur Apache Tomcat.

Copiez le fichier student-manager.war (ou le nom donné par Maven) du dossier target/ vers le dossier webapps/ de votre installation Tomcat.

Tomcat déploiera automatiquement l'application.

Accès à l'Application :
Ouvrez votre navigateur et accédez à l'URL :

http://localhost:8080/student-manager


(L'URL dépend du port de Tomcat et du nom que vous avez donné à votre fichier WAR.)
