/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.avi_urm;

import Objects.User;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Makin Artavia
 */
public class RemoteConnection {

    //variables
    private JSONArray universitiesList = new JSONArray();
    private JSONArray facultiesList = new JSONArray();
    private JSONArray departmentsList = new JSONArray();
    private JSONArray usersList = new JSONArray();
    private JSONArray coursesList = new JSONArray();
    private JSONArray answersList = new JSONArray();
    private JSONArray registrationsList = new JSONArray();
    private JSONArray assignmentsList = new JSONArray();
    private JSONArray submissionsList = new JSONArray();
    //Patron Singleton
    private static RemoteConnection conn;

    public static RemoteConnection GetInstance() {
        if (conn == null) {
            conn = new RemoteConnection();
        }
        return conn;
    }

    public JSONArray getFacultiesList() {
        return facultiesList;
    }

    public JSONArray getUniversitiesList() {
        return universitiesList;
    }

    public JSONArray getDepartmentsList() {
        return departmentsList;
    }

    public JSONArray getUsersList() {
        return usersList;
    }

    public JSONArray getCoursesList() {
        return coursesList;
    }

    public JSONArray getAnswersList() {
        return answersList;
    }

    public JSONArray getRegistrationsList() {
        return registrationsList;
    }

    public JSONArray getAssignmentsList() {
        return assignmentsList;
    }

    public JSONArray getSubmissionsList() {
        return submissionsList;
    }

    //////////// USUARIOS //////////////
    public boolean login(String user, String pass) {
        String url = "http://localhost:5000/users/login";
        String params = "id=" + user + "&password=" + pass;

        String response = connectToServer(url, "POST", params, 1);
        
        if (response != null && !response.isEmpty()) {
            try {
                JSONObject jsonResponse = new JSONObject(response);
                if (jsonResponse.has("jwt") && jsonResponse.getString("jwt") != null) {
                    App.setUser(new User(user, pass,
                            jsonResponse.getString("type"),
                            jsonResponse.getString("name"),
                            jsonResponse.getString("jwt"),
                            jsonResponse.getInt("Uid"),
                            jsonResponse.getString("userCed"),
                            jsonResponse.getInt("userID")));
                    return true;
                }
            } catch (JSONException e) {
                System.out.println("Error al procesar JSON: " + e.getMessage());
            }
        }

        return false;
    }

    public boolean registerUser(int universityId, String userName, String userPassword, String userType, String userIdentification) {
        String url = "http://localhost:5000/users/register";
        JSONObject params = new JSONObject();
        try {
            params.put("university_id", universityId);
            params.put("user_name", userName);
            params.put("user_password", userPassword);
            params.put("user_type", userType);
            params.put("user_identification", userIdentification);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String response = connectToServer(url, "POST", params.toString(), 2);

        if (response != null && !response.isEmpty()) {
            try {
                JSONObject jsonResponse = new JSONObject(response);
                if (jsonResponse.has("message") && jsonResponse.getString("message").equals("Usuario creado satisfactoriamente")) {
                    return true;
                } else {
                    System.out.println("Error: " + jsonResponse.getString("message"));
                }
            } catch (JSONException e) {
                System.out.println("Error al procesar JSON: " + e.getMessage());
            }
        }
        return false;
    }

    public void getUsers() {
        Thread thread = new Thread(() -> {
            String url = "http://localhost:5000/users/get";

            String response = connectToServer(url, "GET", App.getUser().getJwt(), 1);

            if (response != null && !response.isEmpty()) {
                try {
                    usersList = new JSONArray(response); 
                } catch (JSONException e) {
                    System.out.println("Error al procesar JSON: " + e.getMessage());
                }
            } else {
                System.out.println("No se encontraron usuarios o hubo un error en la solicitud.");
            }
        });
        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            System.out.println("El hilo fue interrumpido: " + e.getMessage());
        }
    }

    public boolean deleteUser(String userName) {
        String url = "http://localhost:5000/users/delete";
        JSONObject params = new JSONObject();
        try {
            params.put("user_jwt", App.getUser().getJwt());
            params.put("user_name", userName);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String response = connectToServer(url, "DELETE", params.toString(), 2);

        if (response != null && !response.isEmpty()) {
            try {
                JSONObject jsonResponse = new JSONObject(response);
                if (jsonResponse.has("message") && jsonResponse.getString("message").equals("Usuario eliminado correctamente")) {
                    return true;
                } else {
                    System.out.println("Error: " + jsonResponse.getString("message"));
                }
            } catch (JSONException e) {
                System.out.println("Error al procesar JSON: " + e.getMessage());
            }
        }
        return false;
    }


    public boolean updateUser(int userId, Integer universityId, String userName, String userType, String userPassword, String userIdentification) {
        String url = "http://localhost:5000/users/update";
        JSONObject params = new JSONObject();
        try {
            params.put("user_jwt", App.getUser().getJwt());
            params.put("user_id", userId);
            if (universityId != null) {
                params.put("university_id", universityId);
            }
            if (userName != null) {
                params.put("user_name", userName);
            }
            if (userType != null) {
                params.put("user_type", userType);
            }
            if (userPassword != null) {
                params.put("user_password", userPassword);
            }
            if (userIdentification != null) {
                params.put("user_identification", userIdentification);
            }
            System.out.println("Enviando datos: " + params.toString());
                    
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String response = connectToServer(url, "PUT", params.toString(), 2);
        if (response != null && !response.isEmpty()) {
            try {
                JSONObject jsonResponse = new JSONObject(response);
                if (jsonResponse.has("message") && jsonResponse.getString("message").equals("Usuario actualizado satisfactoriamente")) {
                    return true;
                } else {
                    System.out.println("Error: " + jsonResponse.getString("message"));
                }
            } catch (JSONException e) {
                System.out.println("Error al procesar JSON: " + e.getMessage());
            }
        }
        return false;
    }

    //////////// UNIVERSIDADES //////////////
    public boolean addUniversity(String universityName) {
        String url = "http://localhost:5000/universities/register";
        JSONObject params = new JSONObject();
        try {
            params.put("user_jwt", App.getUser().getJwt());
            params.put("university_name", universityName);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String response = connectToServer(url, "POST", params.toString(), 2);
        System.out.println(App.getUser().getJwt());
        if (response != null && !response.isEmpty()) {
            try {
                JSONObject jsonResponse = new JSONObject(response);
                if (jsonResponse.has("message") && jsonResponse.getString("message").equals("Universidad creada satisfactoriamente")) {
                    return true;
                } else {
                    System.out.println("Error: " + jsonResponse.getString("message"));
                }
            } catch (JSONException e) {
                System.out.println("Error al procesar JSON: " + e.getMessage());
            }
        }
        return false;
    }

    public void getUniversities() {
        Thread thread = new Thread(() -> {
            String url = "http://localhost:5000/universities/get";
            String param = App.getUser().getJwt();

            String response = connectToServer(url, "GET", param, 1);

            if (response != null && !response.isEmpty()) {
                try {
                    universitiesList = new JSONArray(response);
                } catch (JSONException e) {
                    System.out.println("Error al procesar JSON: " + e.getMessage());
                }
            } else {
                System.out.println("No se encontraron universidades o hubo un error en la solicitud.");
            }
        });

        thread.start();
        try {
            thread.join(); 
        } catch (InterruptedException e) {
            System.out.println("El hilo fue interrumpido: " + e.getMessage());
        }
    }

    public boolean deleteUniversity(String universityName) {
        String url = "http://localhost:5000/universities/delete";
        JSONObject params = new JSONObject();
        try {
            params.put("user_jwt", App.getUser().getJwt());
            params.put("university_name", universityName);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String response = connectToServer(url, "DELETE", params.toString(), 2);

        if (response != null && !response.isEmpty()) {
            try {
                JSONObject jsonResponse = new JSONObject(response);
                if (jsonResponse.has("message") && jsonResponse.getString("message").equals("Universidad eliminada correctamente")) {
                    return true;
                } else {
                    System.out.println("Error: " + jsonResponse.getString("message"));
                }
            } catch (JSONException e) {
                System.out.println("Error al procesar JSON: " + e.getMessage());
            }
        }
        return false;
    }

    public boolean updateUniversity(int universityId, String universityName) {
        String url = "http://localhost:5000/universities/update";
        JSONObject params = new JSONObject();
        try {
            params.put("user_jwt", App.getUser().getJwt());
            params.put("university_id", universityId);
            if (universityName != null) {
                params.put("university_name", universityName);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String response = connectToServer(url, "PUT", params.toString(), 2);

        if (response != null && !response.isEmpty()) {
            try {
                JSONObject jsonResponse = new JSONObject(response);
                if (jsonResponse.has("message") && jsonResponse.getString("message").equals("Universidad actualizada satisfactoriamente")) {
                    return true;
                } else {
                    System.out.println("Error: " + jsonResponse.getString("message"));
                }
            } catch (JSONException e) {
                System.out.println("Error al procesar JSON: " + e.getMessage());
            }
        }
        return false;
    }

    //////////// FACULTADES  //////////////    
    public boolean addFaculty(String facultyName, String facultyInformation, int universityId) {
        String url = "http://localhost:5000/faculties/register";
        JSONObject params = new JSONObject();
        try {
            params.put("user_jwt", App.getUser().getJwt());
            params.put("faculty_name", facultyName);
            params.put("faculty_information", facultyInformation);
            params.put("university_id", universityId);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String response = connectToServer(url, "POST", params.toString(), 2);

        if (response != null && !response.isEmpty()) {
            try {
                JSONObject jsonResponse = new JSONObject(response);
                if (jsonResponse.has("message") && jsonResponse.getString("message").equals("Facultad creada satisfactoriamente")) {
                    return true;
                } else {
                    System.out.println("Error: " + jsonResponse.getString("message"));
                }
            } catch (JSONException e) {
                System.out.println("Error al procesar JSON: " + e.getMessage());
            }
        }
        return false;
    }

    public void getFaculties() {
        Thread thread = new Thread(() -> {
            String url = "http://localhost:5000/faculties/get";

            String response = connectToServer(url, "GET", App.getUser().getJwt(), 1);

            if (response != null && !response.isEmpty()) {
                try {
                    facultiesList = new JSONArray(response); 
                } catch (JSONException e) {
                    System.out.println("Error al procesar JSON: " + e.getMessage());
                }
            } else {
                System.out.println("No se encontraron facultades o hubo un error en la solicitud.");
            }
        });
        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            System.out.println("El hilo fue interrumpido: " + e.getMessage());
        }
    }

    public boolean deleteFaculty(String facultyName) {
        String url = "http://localhost:5000/faculties/delete";
        JSONObject params = new JSONObject();
        try {
            params.put("user_jwt", App.getUser().getJwt());
            params.put("faculty_name", facultyName);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String response = connectToServer(url, "DELETE", params.toString(), 2);

        if (response != null && !response.isEmpty()) {
            try {
                JSONObject jsonResponse = new JSONObject(response);
                if (jsonResponse.has("message") && jsonResponse.getString("message").equals("Facultad eliminada correctamente")) {
                    return true;
                } else {
                    System.out.println("Error: " + jsonResponse.getString("message"));
                }
            } catch (JSONException e) {
                System.out.println("Error al procesar JSON: " + e.getMessage());
            }
        }
        return false;
    }

    public boolean updateFaculty(int facultyId, String facultyName, String facultyInformation, Integer universityId) {
        String url = "http://localhost:5000/faculties/update";
        JSONObject params = new JSONObject();
        try {
            params.put("user_jwt", App.getUser().getJwt());
            params.put("faculty_id", facultyId);
            if (facultyName != null) {
                params.put("faculty_name", facultyName);
            }
            if (facultyInformation != null) {
                params.put("faculty_information", facultyInformation);
            }
            if (universityId != 0) {
                params.put("university_id", universityId);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String response = connectToServer(url, "PUT", params.toString(), 2);
        if (response != null && !response.isEmpty()) {
            try {
                JSONObject jsonResponse = new JSONObject(response);
                if (jsonResponse.has("message") && jsonResponse.getString("message").equals("Facultad actualizada satisfactoriamente")) {
                    return true;
                } else {
                    System.out.println("Error: " + jsonResponse.getString("message"));
                }
            } catch (JSONException e) {
                System.out.println("Error al procesar JSON: " + e.getMessage());
            }
        }
        return false;
    }

    //////////// DEPARTAMENTOS  //////////////   
    public boolean addDepartment(String departmentName, String departmentInformation, int facultyId) {
        String url = "http://localhost:5000/department/register";
        JSONObject params = new JSONObject();
        try {
            params.put("user_jwt", App.getUser().getJwt());
            params.put("department_name", departmentName);
            params.put("department_information", departmentInformation);
            params.put("faculty_id", facultyId);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String response = connectToServer(url, "POST", params.toString(), 2);

        if (response != null && !response.isEmpty()) {
            try {
                JSONObject jsonResponse = new JSONObject(response);
                if (jsonResponse.has("message") && jsonResponse.getString("message").equals("Departamento creada satisfactoriamente")) {
                    return true;
                } else {
                    System.out.println("Error: " + jsonResponse.getString("message"));
                }
            } catch (JSONException e) {
                System.out.println("Error al procesar JSON: " + e.getMessage());
            }
        }
        return false;
    }

    public void getDepartments() {
        Thread thread = new Thread(() -> {
            String url = "http://localhost:5000/department/get";

            String response = connectToServer(url, "GET", App.getUser().getJwt(), 1);

            if (response != null && !response.isEmpty()) {
                try {
                    departmentsList = new JSONArray(response);
                } catch (JSONException e) {
                    System.out.println("Error al procesar JSON: " + e.getMessage());
                }
            } else {
                System.out.println("No se encontraron departamentos o hubo un error en la solicitud.");
            }
        });
        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            System.out.println("El hilo fue interrumpido: " + e.getMessage());
        }
    }

    public boolean deleteDepartment(String departmentName) {
        String url = "http://localhost:5000/department/delete";
        JSONObject params = new JSONObject();
        try {
            params.put("user_jwt", App.getUser().getJwt());
            params.put("department_name", departmentName);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String response = connectToServer(url, "DELETE", params.toString(), 2);

        if (response != null && !response.isEmpty()) {
            try {
                JSONObject jsonResponse = new JSONObject(response);
                if (jsonResponse.has("message") && jsonResponse.getString("message").equals("Departamento eliminado correctamente")) {
                    return true;
                } else {
                    System.out.println("Error: " + jsonResponse.getString("message"));
                }
            } catch (JSONException e) {
                System.out.println("Error al procesar JSON: " + e.getMessage());
            }
        }
        return false;
    }

    public boolean updateDepartment(int departmentId, String departmentName, String departmentInformation, int facultyId) {
        String url = "http://localhost:5000/department/update";
        JSONObject params = new JSONObject();
        try {
            params.put("user_jwt", App.getUser().getJwt());
            params.put("department_id", departmentId);
            if (departmentName != null) {
                params.put("department_name", departmentName);
            }
            if (departmentInformation != null) {
                params.put("department_information", departmentInformation);
            }
            if (facultyId != 0) {
                params.put("faculty_id", facultyId);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String response = connectToServer(url, "PUT", params.toString(), 2);
        if (response != null && !response.isEmpty()) {
            try {
                JSONObject jsonResponse = new JSONObject(response);
                if (jsonResponse.has("message") && jsonResponse.getString("message").equals("Departamento actualizado satisfactoriamente")) {
                    return true;
                } else {
                    System.out.println("Error: " + jsonResponse.getString("message"));
                }
            } catch (JSONException e) {
                System.out.println("Error al procesar JSON: " + e.getMessage());
            }
        }
        return false;
    }

    //////////// CURSOS  ////////////// 
    public boolean addCourse(int professorId, int departmentId, String courseName, String courseInformation) {
        String url = "http://localhost:5000/course/register";
        JSONObject params = new JSONObject();
        try {
            params.put("user_jwt", App.getUser().getJwt());
            params.put("professor_id", professorId);
            params.put("department_id", departmentId);
            params.put("course_name", courseName);
            params.put("course_information", courseInformation);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String response = connectToServer(url, "POST", params.toString(), 2);

        if (response != null && !response.isEmpty()) {
            try {
                JSONObject jsonResponse = new JSONObject(response);
                if (jsonResponse.has("message") && jsonResponse.getString("message").equals("Curso creado satisfactoriamente")) {
                    return true;
                } else {
                    System.out.println("Error: " + jsonResponse.getString("message"));
                }
            } catch (JSONException e) {
                System.out.println("Error al procesar JSON: " + e.getMessage());
            }
        }
        return false;
    }

    public void getCourses() {
        Thread thread = new Thread(() -> {
            String url = "http://localhost:5000/course/get";

            String response = connectToServer(url, "GET", App.getUser().getJwt(), 1);

            if (response != null && !response.isEmpty()) {
                try {
                    coursesList = new JSONArray(response);
                } catch (JSONException e) {
                    System.out.println("Error al procesar JSON: " + e.getMessage());
                }
            } else {
                System.out.println("No se encontraron cursos o hubo un error en la solicitud.");
            }
        });
        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            System.out.println("El hilo fue interrumpido: " + e.getMessage());
        }
    }

    public boolean deleteCourse(String courseName) {
        String url = "http://localhost:5000/course/delete";
        JSONObject params = new JSONObject();
        try {
            params.put("user_jwt", App.getUser().getJwt());
            params.put("course_name", courseName);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String response = connectToServer(url, "DELETE", params.toString(), 2);

        if (response != null && !response.isEmpty()) {
            try {
                JSONObject jsonResponse = new JSONObject(response);
                if (jsonResponse.has("message") && jsonResponse.getString("message").equals("Curso eliminado correctamente")) {
                    return true;
                } else {
                    System.out.println("Error: " + jsonResponse.getString("message"));
                }
            } catch (JSONException e) {
                System.out.println("Error al procesar JSON: " + e.getMessage());
            }
        }
        return false;
    }

    public boolean updateCourse(int courseId, Integer professorId, Integer departmentId, String courseName, String courseInformation) {
        String url = "http://localhost:5000/course/update";
        JSONObject params = new JSONObject();
        try {
            params.put("user_jwt", App.getUser().getJwt());
            params.put("course_id", courseId);
            if (professorId != null) {
                params.put("professor_id", professorId);
            }
            if (departmentId != null) {
                params.put("department_id", departmentId);
            }
            if (courseName != null) {
                params.put("course_name", courseName);
            }
            if (courseInformation != null) {
                params.put("course_information", courseInformation);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String response = connectToServer(url, "PUT", params.toString(), 2);
        if (response != null && !response.isEmpty()) {
            try {
                JSONObject jsonResponse = new JSONObject(response);
                if (jsonResponse.has("message") && jsonResponse.getString("message").equals("Curso actualizado satisfactoriamente")) {
                    return true;
                } else {
                    System.out.println("Error: " + jsonResponse.getString("message"));
                }
            } catch (JSONException e) {
                System.out.println("Error al procesar JSON: " + e.getMessage());
            }
        }
        return false;
    }

    //////////// ASIGNACIONES  //////////////
    public boolean addAssignment(int courseId, int professorId, String courseStatement, String assignmentType, String initialDate, String finalDate) {
        String url = "http://localhost:5000/assignments/register";
        JSONObject params = new JSONObject();
        try {
            params.put("user_jwt", App.getUser().getJwt());
            params.put("course_id", courseId);
            params.put("professor_id", professorId);
            params.put("course_statement", courseStatement);
            params.put("assignment_type", assignmentType);
            params.put("initial_date", initialDate);
            params.put("final_date", finalDate);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String response = connectToServer(url, "POST", params.toString(), 2);

        if (response != null && !response.isEmpty()) {
            try {
                JSONObject jsonResponse = new JSONObject(response);
                if (jsonResponse.has("message") && jsonResponse.getString("message").equals("Asignacion creada satisfactoriamente")) {
                    return true;
                } else {
                    System.out.println("Error: " + jsonResponse.getString("message"));
                }
            } catch (JSONException e) {
                System.out.println("Error al procesar JSON: " + e.getMessage());
            }
        }
        return false;
    }

    public void getAssignments() {
        Thread thread = new Thread(() -> {
            String url = "http://localhost:5000/assignments/get";

            String response = connectToServer(url, "GET", App.getUser().getJwt(), 1);

            if (response != null && !response.isEmpty()) {
                try {
                    assignmentsList = new JSONArray(response);
                } catch (JSONException e) {
                    System.out.println("Error al procesar JSON: " + e.getMessage());
                }
            } else {
                System.out.println("No se encontraron asignaciones o hubo un error en la solicitud.");
            }
        });
        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            System.out.println("El hilo fue interrumpido: " + e.getMessage());
        }
    }

    public boolean deleteAssignment(int assignmentID) {
        String url = "http://localhost:5000/assignments/delete";
        JSONObject params = new JSONObject();
        try {
            params.put("user_jwt", App.getUser().getJwt());
            params.put("assignment_id", assignmentID);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String response = connectToServer(url, "DELETE", params.toString(), 2);

        if (response != null && !response.isEmpty()) {
            try {
                JSONObject jsonResponse = new JSONObject(response);
                if (jsonResponse.has("message") && jsonResponse.getString("message").equals("Asignacion eliminada correctamente")) {
                    return true;
                } else {
                    System.out.println("Error: " + jsonResponse.getString("message"));
                }
            } catch (JSONException e) {
                System.out.println("Error al procesar JSON: " + e.getMessage());
            }
        }
        return false;
    }

    public boolean updateAssignment(int assignmentId, Integer courseId, Integer professorId, String courseStatement, String assignmentType, String initialDate, String finalDate) {
        String url = "http://localhost:5000/assignments/update";
        JSONObject params = new JSONObject();
        try {
            params.put("user_jwt", App.getUser().getJwt());
            params.put("assignment_id", assignmentId);
            if (courseId != null) {
                params.put("course_id", courseId);
            }
            if (professorId != null) {
                params.put("professor_id", professorId);
            }
            if (courseStatement != null) {
                params.put("course_statement", courseStatement);
            }
            if (assignmentType != null) {
                params.put("assignment_type", assignmentType);
            }
            if (initialDate != null) {
                params.put("initial_date", initialDate);
            }
            if (finalDate != null) {
                params.put("final_date", finalDate);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String response = connectToServer(url, "PUT", params.toString(), 2);
        if (response != null && !response.isEmpty()) {
            try {
                JSONObject jsonResponse = new JSONObject(response);
                if (jsonResponse.has("message") && jsonResponse.getString("message").equals("Asignacion actualizada satisfactoriamente")) {
                    return true;
                } else {
                    System.out.println("Error: " + jsonResponse.getString("message"));
                }
            } catch (JSONException e) {
                System.out.println("Error al procesar JSON: " + e.getMessage());
            }
        }
        return false;
    }

    //////////// ENTREGAS  //////////////
    public boolean addSubmissionStudent(int assingmentID, int student_id, String submissionDate, String submissionFile, String submissionStatus) {
        String url = "http://localhost:5000/submission/register";
        JSONObject params = new JSONObject();

        try {
            String jwt = App.getUser().getJwt();
            if (jwt == null || jwt.isEmpty()) {
                System.out.println("Error: JWT está vacío o nulo");
                return false;
            }

            if (submissionDate == null || submissionDate.isEmpty()) {
                System.out.println("Error: Fecha de entrega está vacía");
                return false;
            }

            if (submissionFile == null || submissionFile.isEmpty()) {
                System.out.println("Error: Archivo de entrega está vacío");
                return false;
            }

            params.put("user_jwt", jwt);
            params.put("assignment_id", assingmentID);
            params.put("student_id", student_id);
            params.put("submission_date", submissionDate);
            params.put("submission_file", submissionFile);
            params.put("submission_status", submissionStatus);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String response = connectToServer(url, "POST", params.toString(), 2);

        if (response != null && !response.isEmpty()) {
            try {
                JSONObject jsonResponse = new JSONObject(response);
                if (jsonResponse.has("message") && jsonResponse.getString("message").equals("Entrega ralizada satisfactoriamente")) {
                    return true;
                } else {
                    System.out.println("Error: " + jsonResponse.getString("message"));
                }
            } catch (JSONException e) {
                System.out.println("Error al procesar JSON: " + e.getMessage());
            }
        }
        return false;
    }

    public void getSubmissions() {
        Thread thread = new Thread(() -> {
            String url = "http://localhost:5000/submission/get";

            String response = connectToServer(url, "GET", App.getUser().getJwt(), 1);

            if (response != null && !response.isEmpty()) {
                try {
                    submissionsList = new JSONArray(response);
                } catch (JSONException e) {
                    System.out.println("Error al procesar JSON: " + e.getMessage());
                }
            } else {
                System.out.println("No se encontraron entregas o hubo un error en la solicitud.");
            }
        });
        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            System.out.println("El hilo fue interrumpido: " + e.getMessage());
        }
    }

    public boolean deleteSubmission(int submissionID) {
        String url = "http://localhost:5000/submission/delete";
        JSONObject params = new JSONObject();
        try {
            params.put("user_jwt", App.getUser().getJwt());
            params.put("submission_id", submissionID);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String response = connectToServer(url, "DELETE", params.toString(), 2);

        if (response != null && !response.isEmpty()) {
            try {
                JSONObject jsonResponse = new JSONObject(response);
                if (jsonResponse.has("message") && jsonResponse.getString("message").equals("Entrega eliminada correctamente")) {
                    return true;
                } else {
                    System.out.println("Error: " + jsonResponse.getString("message"));
                }
            } catch (JSONException e) {
                System.out.println("Error al procesar JSON: " + e.getMessage());
            }
        }
        return false;
    }

    public boolean updateSubmission(int submission_id, float submission_score, int assingmentID, int student_id, String submissionDate, String submissionFile, String submissionStatus, String commentAI, String commentProfessor) {
        String url = "http://localhost:5000/submission/update";
        JSONObject params = new JSONObject();

        try {
            String jwt = App.getUser().getJwt();
            params.put("user_jwt", jwt);
            params.put("submission_id", submission_id);
            params.put("submission_score", submission_score);
            params.put("assignment_id", assingmentID);
            params.put("student_id", student_id);
            params.put("submission_date", submissionDate);
            params.put("submission_file", submissionFile);
            params.put("submission_status", submissionStatus);
            params.put("comment_ai", commentAI);
            params.put("comment_professor", commentProfessor);

            System.out.println("Enviando datos: " + params.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String response = connectToServer(url, "PUT", params.toString(), 2);

        if (response != null && !response.isEmpty()) {
            try {
                JSONObject jsonResponse = new JSONObject(response);
                if (jsonResponse.has("message") && jsonResponse.getString("message").equals("Entrega actualizada satisfactoriamente")) {
                    return true;
                } else {
                    System.out.println("Error: " + jsonResponse.getString("message"));
                }
            } catch (JSONException e) {
                System.out.println("Error al procesar JSON: " + e.getMessage());
            }
        }
        return false;
    }

    //////////// MATRICULAS  //////////////
    public boolean addRegistration(int studentId, int courseId) {
        String url = "http://localhost:5000/registration/register";
        JSONObject params = new JSONObject();
        try {
            params.put("user_jwt", App.getUser().getJwt());
            params.put("student_id", studentId);
            params.put("course_id", courseId);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String response = connectToServer(url, "POST", params.toString(), 2);

        if (response != null && !response.isEmpty()) {
            try {
                JSONObject jsonResponse = new JSONObject(response);
                if (jsonResponse.has("message") && jsonResponse.getString("message").equals("Estudiante matriculado satisfactoriamente")) {
                    return true;
                } else {
                    System.out.println("Error: " + jsonResponse.getString("message"));
                }
            } catch (JSONException e) {
                System.out.println("Error al procesar JSON: " + e.getMessage());
            }
        }
        return false;
    }

    public void getRegistrations() {
        Thread thread = new Thread(() -> {
            String url = "http://localhost:5000/registration/get";

            String response = connectToServer(url, "GET", App.getUser().getJwt(), 1);

            if (response != null && !response.isEmpty()) {
                try {
                    registrationsList = new JSONArray(response);
                } catch (JSONException e) {
                    System.out.println("Error al procesar JSON: " + e.getMessage());
                }
            } else {
                System.out.println("No se encontraron matriculas o hubo un error en la solicitud.");
            }
        });
        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            System.out.println("El hilo fue interrumpido: " + e.getMessage());
        }
    }

    public boolean deleteRegistration(int registrationId) {
        String url = "http://localhost:5000/registration/delete";
        JSONObject params = new JSONObject();
        try {
            params.put("user_jwt", App.getUser().getJwt());
            params.put("registration_id", registrationId);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String response = connectToServer(url, "DELETE", params.toString(), 2);

        if (response != null && !response.isEmpty()) {
            try {
                JSONObject jsonResponse = new JSONObject(response);
                if (jsonResponse.has("message") && jsonResponse.getString("message").equals("Matricula eliminada correctamente")) {
                    return true;
                } else {
                    System.out.println("Error: " + jsonResponse.getString("message"));
                }
            } catch (JSONException e) {
                System.out.println("Error al procesar JSON: " + e.getMessage());
            }
        }
        return false;
    }

    public boolean updateRegistration(int registrationId, Integer studentId, Integer courseId) {
        String url = "http://localhost:5000/registration/update";
        JSONObject params = new JSONObject();
        try {
            params.put("user_jwt", App.getUser().getJwt());
            params.put("registration_id", registrationId);
            if (studentId != null) {
                params.put("student_id", studentId);
            }
            if (courseId != null) {
                params.put("course_id", courseId);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String response = connectToServer(url, "PUT", params.toString(), 2);
        if (response != null && !response.isEmpty()) {
            try {
                JSONObject jsonResponse = new JSONObject(response);
                if (jsonResponse.has("message") && jsonResponse.getString("message").equals("Matricula actualizada satisfactoriamente")) {
                    return true;
                } else {
                    System.out.println("Error: " + jsonResponse.getString("message"));
                }
            } catch (JSONException e) {
                System.out.println("Error al procesar JSON: " + e.getMessage());
            }
        }
        return false;
    }

    //////////// RESPUESTAS  //////////////
    public boolean addAnswer(String answerText) {
        String url = "http://localhost:5000/answer/register";
        JSONObject params = new JSONObject();
        try {
            params.put("user_jwt", App.getUser().getJwt());
            params.put("answer", answerText);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String response = connectToServer(url, "POST", params.toString(), 2);

        if (response != null && !response.isEmpty()) {
            try {
                JSONObject jsonResponse = new JSONObject(response);
                if (jsonResponse.has("message") && jsonResponse.getString("message").equals("Respuesta creada satisfactoriamente")) {
                    return true;
                } else {
                    System.out.println("Error: " + jsonResponse.getString("message"));
                }
            } catch (JSONException e) {
                System.out.println("Error al procesar JSON: " + e.getMessage());
            }
        }

        return false;
    }

    public void getAnswers() {
        Thread thread = new Thread(() -> {
            String url = "http://localhost:5000/answer/get";

            String response = connectToServer(url, "GET", App.getUser().getJwt(), 1);

            if (response != null && !response.isEmpty()) {
                try {
                    answersList = new JSONArray(response);
                } catch (JSONException e) {
                    System.out.println("Error al procesar JSON: " + e.getMessage());
                }
            } else {
                System.out.println("No se encontraron respuestas o hubo un error en la solicitud.");
            }
        });
        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            System.out.println("El hilo fue interrumpido: " + e.getMessage());
        }
    }

    public boolean deleteAnswer(int answerId) {
        String url = "http://localhost:5000/answer/delete";
        JSONObject params = new JSONObject();
        try {
            params.put("user_jwt", App.getUser().getJwt());
            params.put("answer_id", answerId);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String response = connectToServer(url, "DELETE", params.toString(), 2);

        if (response != null && !response.isEmpty()) {
            try {
                JSONObject jsonResponse = new JSONObject(response);
                if (jsonResponse.has("message") && jsonResponse.getString("message").equals("Respuesta IA eliminada correctamente")) {
                    return true;
                } else {
                    System.out.println("Error: " + jsonResponse.getString("message"));
                }
            } catch (JSONException e) {
                System.out.println("Error al procesar JSON: " + e.getMessage());
            }
        }
        return false;
    }

    public boolean updateAnswer(int answerId, String newAnswer) {
        String url = "http://localhost:5000/answer/update";
        JSONObject params = new JSONObject();
        try {
            params.put("user_jwt", App.getUser().getJwt());
            params.put("answer_id", answerId);
            params.put("answer", newAnswer);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String response = connectToServer(url, "PUT", params.toString(), 2);

        if (response != null && !response.isEmpty()) {
            try {
                JSONObject jsonResponse = new JSONObject(response);
                if (jsonResponse.has("message") && jsonResponse.getString("message").equals("Respuesta IA actualizada satisfactoriamente")) {
                    return true;
                } else {
                    System.out.println("Error: " + jsonResponse.getString("message"));
                }
            } catch (JSONException e) {
                System.out.println("Error al procesar JSON: " + e.getMessage());
            }
        }
        return false;
    }
    
    public void getFacultiesInLobby() {
        Thread thread = new Thread(() -> {
            String url = "http://localhost:5000/faculties/get";       
            String jwt = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjoiUmV5bmVyIiwiZXhwIjoxNzM0NzUzMjgxfQ.e9dMHiV1fGZcezUrl6GrtSf8fmFGlzM8ITJC0Jh_oC4";
            String response = connectToServer(url, "GET", jwt, 1);
            
            if (response != null && !response.isEmpty()) {
                try {
                    facultiesList = new JSONArray(response); 
                    
                } catch (JSONException e) {
                    System.out.println("Error al procesar JSON: " + e.getMessage());
                }
            } else {
                System.out.println("No se encontraron facultades o hubo un error en la solicitud.");
            }
        });
        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            System.out.println("El hilo fue interrumpido: " + e.getMessage());
        }
    }
    ///////// Conexiones y solucitudes al servidor //////////
    private String connectToServer(String enlace, String metodo, String param, int type) {
        String response = null;
        HttpURLConnection urlConnection = null;
        try {
            if (metodo.equals("GET")) {
                enlace += "/" + param;
            }
            URL url = new URL(enlace);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod(metodo);

            if (type == 1) {
                urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            } else {
                urlConnection.setRequestProperty("Content-Type", "application/json");
            }

            if (metodo.equals("POST") || metodo.equals("PUT") || metodo.equals("DELETE")) {
                urlConnection.setDoOutput(true);
                if (param != null && !param.isEmpty()) {
                    urlConnection.setFixedLengthStreamingMode(param.getBytes().length);
                    try (OutputStream out = urlConnection.getOutputStream()) {
                        writeStream(out, param);
                    }
                }
            }

            int responseCode = urlConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED) { // Verifica los códigos 200 y 201
                response = readStream(urlConnection.getInputStream());
            } else {
                System.out.println("Error en la solicitud: Código " + responseCode);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return response;
    }

    private void writeStream(OutputStream out, String param) throws IOException {
        out.write(param.getBytes());
        out.flush();
    }

    private String readStream(InputStream in) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
