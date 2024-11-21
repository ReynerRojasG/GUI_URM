/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.avi_urm;

import Objects.Answer;
import Objects.Course;
import Objects.Department;
import Objects.Faculty;
import Objects.Registration;
import Objects.University;
import Objects.User;
import Player.SoundPlayer;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author Makin Artavia
 */
public class AdminsPortalController implements Initializable {

    //Variables
    private JSONArray universitiesList = new JSONArray();
    private JSONArray facultiesList = new JSONArray();
    private JSONArray departmentsList = new JSONArray();
    private JSONArray coursesList = new JSONArray();
    private JSONArray usersList = new JSONArray();
    private JSONArray answersList = new JSONArray();
    private JSONArray registrationsList = new JSONArray();
    private User selectedUser = new User();
    private University selectedUniversity = new University();
    private Department selectedDepartment = new Department();
    private Faculty selectedFaculty = new Faculty();
    private Course selectedCourse = new Course();
    private Answer selectedAnswere = new Answer();
    private Registration selectedRegistration = new Registration();
    private int selectedTable = 0;
    @FXML
    private Label lbl_minimize;
    @FXML
    private Label lbl_exit;
    @FXML
    private TextField tf_UniverName;
    @FXML
    private Button btn_createU;
    @FXML
    private TextField tf_FacultyName;
    @FXML
    private Button btn_createFaculty;
    @FXML
    private TextArea ta_FacultyDescription;
    @FXML
    private ChoiceBox<String> cbx_UniverID;
    @FXML
    private TextField tf_DepartName;
    @FXML
    private Button btn_createDepartment;
    @FXML
    private TextArea ta_DepartmentDescription;
    @FXML
    private ChoiceBox<String> cbx_facultyID;
    @FXML
    private Label lbl_info;
    @FXML
    private Button btn_IAanswere;
    @FXML
    private TextArea ta_answereForIA;
    @FXML
    private Button btn_createCourse;
    @FXML
    private TextArea ta_CourseDescription;
    @FXML
    private ChoiceBox<String> cbx_departmentID;
    @FXML
    private ChoiceBox<String> cbx_professorID;
    @FXML
    private TextField tf_CourseName;
    @FXML
    private TextField tf_UserName;
    @FXML
    private Button btn_createUser;
    @FXML
    private ChoiceBox<String> cbx_UniverIDtoAddUser;
    @FXML
    private ChoiceBox<String> cbx_type;
    @FXML
    private TextField tf_user;
    @FXML
    private TextField tf_pass;
    @FXML
    private TableView<User> users_tv;
    @FXML
    private TableColumn<?, ?> user_id;
    @FXML
    private TableColumn<?, ?> university_id;
    @FXML
    private TableColumn<?, ?> name;
    @FXML
    private TableColumn<?, ?> type;
    @FXML
    private TextField tf_userEditName;
    @FXML
    private ChoiceBox<String> cbx_UniverIDtoEdit;
    @FXML
    private ChoiceBox<String> cbx_typeToEdit;
    @FXML
    private TextField tf_userEditCed;
    @FXML
    private Button btn_deleteUser;
    @FXML
    private Button btn_editUserInfo;
    @FXML
    private TableView<University> university_tv;
    @FXML
    private TableColumn<?, ?> tab_UniversityName;
    @FXML
    private TableColumn<?, ?> tab_UniversityID;
    @FXML
    private Button btn_deleteSelection;
    @FXML
    private Button btn_ChangeTab;
    @FXML
    private TableView<Faculty> faculties_tv;
    @FXML
    private TableColumn<?, ?> tab_facultyID;
    @FXML
    private TableColumn<?, ?> tab_facultyName;
    @FXML
    private TableColumn<?, ?> tab_facultyInfo;
    @FXML
    private TableColumn<?, ?> tab_facultyUniverID;
    @FXML
    private TableView<Department> departtments_tv;
    @FXML
    private TableColumn<?, ?> tab_departmentID;
    @FXML
    private TableColumn<?, ?> tab_departmentName;
    @FXML
    private TableColumn<?, ?> tab_departmentInfo;
    @FXML
    private TableColumn<?, ?> tab_departmentFacultyID;
    @FXML
    private TableView<Answer> iaAnswere_tv;
    @FXML
    private TableColumn<?, ?> tab_answereID;
    @FXML
    private TableColumn<?, ?> tab_contentAnswere;
    @FXML
    private TableView<Course> courses_tv;
    @FXML
    private TableColumn<?, ?> tab_courseID;
    @FXML
    private TableColumn<?, ?> tab_courseProfID;
    @FXML
    private TableColumn<?, ?> tab_courseDepartID;
    @FXML
    private TableColumn<?, ?> tab_courseName;
    @FXML
    private TableColumn<?, ?> tab_courseInfo;
    @FXML
    private TableView<User> students_tv;
    @FXML
    private TableColumn<?, ?> tab_studentID;
    @FXML
    private TableColumn<?, ?> tab_subUniversityID;
    @FXML
    private TableColumn<?, ?> tab_studentName;
    @FXML
    private Button btn_createRegistration;
    @FXML
    private TextField tf_subStudentID;
    @FXML
    private TextField tf_subCourseID;
    @FXML
    private TableView<Course> coursesToSubscribe_tv;
    @FXML
    private TableColumn<?, ?> tab_subCourseID;
    @FXML
    private TableColumn<?, ?> tab_subCourseProfID;
    @FXML
    private TableColumn<?, ?> tab_subCourseDepartID;
    @FXML
    private TableColumn<?, ?> tab_subCourseName;
    @FXML
    private TableColumn<?, ?> tab_subCourseInfo;
    @FXML
    private TableView<Registration> registration_tv;
    @FXML
    private TableColumn<?, ?> tab_registrationID;
    @FXML
    private TableColumn<?, ?> tab_SubCourseID;
    @FXML
    private TableColumn<?, ?> tab_SubStudentID;
    @FXML
    private AnchorPane editRegistration_ap;
    @FXML
    private Button btn_editRegistration;
    @FXML
    private TextField tf_subEditStudentID;
    @FXML
    private TextField tf_subEditCourseID;
    @FXML
    private Button btn_deleteRegistration;
    @FXML
    private TabPane tp_sections;
    @FXML
    private AnchorPane EditCurso_ap;
    @FXML
    private TextField tf_EditCourseName;
    @FXML
    private ChoiceBox<String> cbx_EditdepartmentID;
    @FXML
    private ChoiceBox<String> cbx_EditProfessorID;
    @FXML
    private TextArea ta_EditCourseDescription;
    @FXML
    private AnchorPane EditDepartment_ap;
    @FXML
    private TextField tf_EditDepartName;
    @FXML
    private TextArea ta_EditDepartmentDescription;
    @FXML
    private ChoiceBox<String> cbx_EditfacultyID;
    @FXML
    private AnchorPane EditFacultad_ap;
    @FXML
    private TextField tf_EditFacultyName;
    @FXML
    private ChoiceBox<String> cbx_EditUniverID;
    @FXML
    private TextArea ta_EditFacultyDescription;
    @FXML
    private AnchorPane EditUniversidad_ap;
    @FXML
    private TextField tf_EditUniverName;
    private AnchorPane EditRespuesta_ap;
    @FXML
    private TextArea ta_EditAnswereForIA;
    @FXML
    private Button btn_saveChanges;
    @FXML
    private TextField tf_UniversalID;
    @FXML
    private AnchorPane EditAnswere_ap;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        LoadCBX();
        loadTables();
    }

    private void clearInfo() {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        executorService.schedule(() -> {
            Platform.runLater(() -> {
                lbl_info.setText("");
            });
            executorService.shutdown();
        }, 2, TimeUnit.SECONDS);
    }

    public static void DropShadowToAnchorPane(AnchorPane anchorPane, String color) {
        if (anchorPane.getEffect() instanceof DropShadow) {
            DropShadow dropShadow = (DropShadow) anchorPane.getEffect();
            dropShadow.setWidth(25);
            dropShadow.setHeight(25);
            dropShadow.setColor(Color.web(color));
        }
    }

    private void LoadCBX() {
        //////////////////////Cargar el de las universidades//////////////////////////////
        RemoteConnection.GetInstance().getUniversities();
        universitiesList = RemoteConnection.GetInstance().getUniversitiesList();
        cbx_UniverID.getItems().clear();
        cbx_UniverIDtoAddUser.getItems().clear();
        cbx_UniverIDtoEdit.getItems().clear();
        cbx_EditUniverID.getItems().clear();
        if (universitiesList != null) {
            for (int i = 0; i < universitiesList.length(); i++) {
                try {
                    JSONObject university = universitiesList.getJSONObject(i);
                    String universityName = university.getString("university_name");
                    cbx_UniverID.getItems().add(universityName);
                    cbx_UniverIDtoAddUser.getItems().add(universityName);
                    cbx_UniverIDtoEdit.getItems().add(universityName);
                    cbx_EditUniverID.getItems().add(universityName);
                } catch (JSONException e) {
                    System.out.println("Error al procesar JSON: " + e.getMessage());
                }
            }
        } else {
            System.out.println("La lista de universidades está vacía o hubo un error.");

        }
        //////////////////////Cargar el de las facultades//////////////////////////////
        RemoteConnection.GetInstance().getFaculties();
        facultiesList = RemoteConnection.GetInstance().getFacultiesList();
        cbx_facultyID.getItems().clear();
        cbx_EditfacultyID.getItems().clear();
        if (facultiesList != null) {
            for (int i = 0; i < facultiesList.length(); i++) {
                try {
                    JSONObject faculty = facultiesList.getJSONObject(i);
                    String facultyName = faculty.getString("faculty_name");
                    cbx_facultyID.getItems().add(facultyName);
                    cbx_EditfacultyID.getItems().add(facultyName);
                } catch (JSONException e) {
                    System.out.println("Error al procesar JSON: " + e.getMessage());
                }
            }
        } else {
            System.out.println("La lista de Facultades está vacía o hubo un error.");
        }
        //////////////////////Cargar el de los departamentos//////////////////////////////
        RemoteConnection.GetInstance().getDepartments();
        departmentsList = RemoteConnection.GetInstance().getDepartmentsList();
        cbx_departmentID.getItems().clear();
        cbx_EditdepartmentID.getItems().clear();
        if (departmentsList != null) {
            for (int i = 0; i < departmentsList.length(); i++) {
                try {
                    JSONObject department = departmentsList.getJSONObject(i);
                    String departmentName = department.getString("department_name");
                    cbx_departmentID.getItems().add(departmentName);
                    cbx_EditdepartmentID.getItems().add(departmentName);
                } catch (JSONException e) {
                    System.out.println("Error al procesar JSON: " + e.getMessage());
                }
            }
        } else {
            System.out.println("La lista de Facultades está vacía o hubo un error.");
        }
        //////////////////////Cargar el de los profesores//////////////////////////////
        RemoteConnection.GetInstance().getUsers();
        usersList = RemoteConnection.GetInstance().getUsersList();
        cbx_professorID.getItems().clear();
        cbx_EditProfessorID.getItems().clear();
        if (usersList != null) {
            for (int i = 0; i < usersList.length(); i++) {
                try {
                    JSONObject professor = usersList.getJSONObject(i);
                    if ("Profesor".equals(professor.getString("user_type"))) {
                        String professorName = professor.getString("user_name");
                        cbx_professorID.getItems().add(professorName);
                        cbx_EditProfessorID.getItems().add(professorName);
                    }

                } catch (JSONException e) {
                    System.out.println("Error al procesar JSON: " + e.getMessage());
                }
            }
        } else {
            System.out.println("La lista de Facultades está vacía o hubo un error.");
        }
        //////////////////////Cargar el de los profesores//////////////////////////////
        cbx_type.getItems().addAll("Estudiante", "Profesor", "Administrador");
        cbx_typeToEdit.getItems().addAll("Estudiante", "Profesor", "Administrador");

    }

    public Integer getUniversityIdByName(String universityName) {
        for (int i = 0; i < universitiesList.length(); i++) {
            JSONObject university = universitiesList.getJSONObject(i);
            if (university.getString("university_name").equals(universityName)) {
                return university.getInt("university_id");
            }
        }
        return null;
    }

    public String getUniversityNameById(int universityID) {
        for (int i = 0; i < universitiesList.length(); i++) {
            JSONObject university = universitiesList.getJSONObject(i);
            if (university.getInt("university_id") == universityID) {
                return university.getString("university_name");
            }
        }
        return null;
    }

    public Integer getFacultyIdByName(String facultyName) {
        for (int i = 0; i < facultiesList.length(); i++) {
            JSONObject faculty = facultiesList.getJSONObject(i);
            if (faculty.getString("faculty_name").equals(facultyName)) {
                return faculty.getInt("faculty_id");
            }
        }
        return null;
    }

    public String getFacultyNameById(int facultyId) {
        for (int i = 0; i < facultiesList.length(); i++) {
            JSONObject faculty = facultiesList.getJSONObject(i);
            if (faculty.getInt("faculty_id") == facultyId) {
                return faculty.getString("faculty_name");
            }
        }
        return null;
    }

    public Integer getDepartmentsIdByName(String departmentName) {
        for (int i = 0; i < departmentsList.length(); i++) {
            JSONObject department = departmentsList.getJSONObject(i);
            if (department.getString("department_name").equals(departmentName)) {
                return department.getInt("department_id");
            }
        }
        return null;
    }

    public String getDepartmentsNameById(int departmentId) {
        for (int i = 0; i < departmentsList.length(); i++) {
            JSONObject department = departmentsList.getJSONObject(i);
            if (department.getInt("department_id") == departmentId) {
                return department.getString("department_name");
            }
        }
        return null;
    }

    public Integer getProfessorsIdByName(String professorName) {
        for (int i = 0; i < usersList.length(); i++) {
            JSONObject professor = usersList.getJSONObject(i);
            if (professor.getString("user_name").equals(professorName) && professor.getString("user_type").equals("Profesor")) {
                return professor.getInt("user_id");
            }
        }
        return null;
    }

    public String getProfessorsNameById(int professorId) {
        for (int i = 0; i < usersList.length(); i++) {
            JSONObject professor = usersList.getJSONObject(i);
            if (professor.getInt("user_id") == professorId) {
                return professor.getString("user_name");
            }
        }
        return null;
    }

    public String getUserCed(Integer userId) {
        for (int i = 0; i < usersList.length(); i++) {
            JSONObject user = usersList.getJSONObject(i);
            if (user.getInt("user_id") == userId) {
                return user.getString("user_identification");
            }
        }
        return null;
    }

    private void loadTables() {
        //////////////////////////////User Table//////////////////////////////
        user_id.setCellValueFactory(new PropertyValueFactory<>("userID"));
        university_id.setCellValueFactory(new PropertyValueFactory<>("Uid"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));

        RemoteConnection.GetInstance().getUsers();
        usersList = RemoteConnection.GetInstance().getUsersList();

        ObservableList<User> usersObservableList = FXCollections.observableArrayList();

        if (usersList != null) {
            for (int i = 0; i < usersList.length(); i++) {
                try {
                    JSONObject userJson = usersList.getJSONObject(i);
                    int userId = userJson.getInt("user_id");
                    int universityId = userJson.getInt("university_id");
                    String userName = userJson.getString("user_name");
                    String userType = userJson.getString("user_type");
                    String userpass = userJson.getString("user_password");
                    String userced = userJson.getString("user_identification");
                    User user = new User(
                            userpass,
                            userName,
                            userType,
                            userName,
                            null,
                            universityId,
                            userced,
                            userId
                    );
                    usersObservableList.add(user);
                } catch (JSONException e) {
                    System.out.println("Error al procesar JSON: " + e.getMessage());
                }
            }
        } else {
            System.out.println("La lista de usuarios esta vacia o hubo un error.");
        }
        users_tv.setItems(usersObservableList);
        //////////////////////////////University Table//////////////////////////////
        tab_UniversityID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tab_UniversityName.setCellValueFactory(new PropertyValueFactory<>("name"));
        RemoteConnection.GetInstance().getUniversities();
        universitiesList = RemoteConnection.GetInstance().getUniversitiesList();
        ObservableList<University> universitiesObservableList = FXCollections.observableArrayList();
        if (universitiesList != null) {
            for (int i = 0; i < universitiesList.length(); i++) {
                try {
                    JSONObject universityJson = universitiesList.getJSONObject(i);
                    University university = new University(
                            universityJson.getString("university_name"),
                            universityJson.getInt("university_id")
                    );
                    universitiesObservableList.add(university);
                } catch (JSONException e) {
                    System.out.println("Error al procesar JSON: " + e.getMessage());
                }
            }
        } else {
            System.out.println("La lista de universidades esta vacia o hubo un error.");
        }
        university_tv.setItems(universitiesObservableList);
        //////////////////////////////Faculty Table//////////////////////////////
        tab_facultyID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tab_facultyName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tab_facultyInfo.setCellValueFactory(new PropertyValueFactory<>("description"));
        tab_facultyUniverID.setCellValueFactory(new PropertyValueFactory<>("UniverID"));
        RemoteConnection.GetInstance().getFaculties();
        facultiesList = RemoteConnection.GetInstance().getFacultiesList();
        ObservableList<Faculty> facultiesObservableList = FXCollections.observableArrayList();
        if (facultiesList != null) {
            for (int i = 0; i < facultiesList.length(); i++) {
                try {
                    JSONObject facultyJson = facultiesList.getJSONObject(i);
                    Faculty faculty = new Faculty(
                            facultyJson.getInt("faculty_id"),
                            facultyJson.getString("faculty_name"),
                            facultyJson.getString("faculty_information"),
                            facultyJson.getInt("university_id")
                    );
                    facultiesObservableList.add(faculty);
                } catch (JSONException e) {
                    System.out.println("Error al procesar JSON: " + e.getMessage());
                }
            }
        } else {
            System.out.println("La lista de facultades esta vacia o hubo un error.");
        }
        faculties_tv.setItems(facultiesObservableList);
        //////////////////////////////Department Table//////////////////////////////
        tab_departmentID.setCellValueFactory(new PropertyValueFactory<>("departmentId"));
        tab_departmentName.setCellValueFactory(new PropertyValueFactory<>("departmentName"));
        tab_departmentInfo.setCellValueFactory(new PropertyValueFactory<>("departmentInformation"));
        tab_departmentFacultyID.setCellValueFactory(new PropertyValueFactory<>("facultyId"));
        RemoteConnection.GetInstance().getDepartments();
        departmentsList = RemoteConnection.GetInstance().getDepartmentsList();
        ObservableList<Department> departmentObservableList = FXCollections.observableArrayList();
        if (departmentsList != null) {
            for (int i = 0; i < departmentsList.length(); i++) {
                try {
                    JSONObject departmentJson = departmentsList.getJSONObject(i);
                    Department department = new Department(
                            departmentJson.getInt("department_id"),
                            departmentJson.getString("department_name"),
                            departmentJson.getString("department_information"),
                            departmentJson.getInt("faculty_id")
                    );
                    departmentObservableList.add(department);
                } catch (JSONException e) {
                    System.out.println("Error al procesar JSON: " + e.getMessage());
                }
            }
        } else {
            System.out.println("La lista de departamentos esta vacia o hubo un error.");
        }
        departtments_tv.setItems(departmentObservableList);
        //////////////////////////////Course Table//////////////////////////////
        tab_courseID.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        tab_courseProfID.setCellValueFactory(new PropertyValueFactory<>("professorId"));
        tab_courseDepartID.setCellValueFactory(new PropertyValueFactory<>("departmentId"));
        tab_courseName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        tab_courseInfo.setCellValueFactory(new PropertyValueFactory<>("courseInformation"));

        //Tabla de cursos de la pestania de matriculas
        tab_subCourseID.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        tab_subCourseProfID.setCellValueFactory(new PropertyValueFactory<>("professorId"));
        tab_subCourseDepartID.setCellValueFactory(new PropertyValueFactory<>("departmentId"));
        tab_subCourseName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        tab_subCourseInfo.setCellValueFactory(new PropertyValueFactory<>("courseInformation"));
        RemoteConnection.GetInstance().getCourses();
        coursesList = RemoteConnection.GetInstance().getCoursesList();
        ObservableList<Course> courseObservableList = FXCollections.observableArrayList();
        if (coursesList != null) {
            for (int i = 0; i < coursesList.length(); i++) {
                try {
                    JSONObject courseJson = coursesList.getJSONObject(i);
                    Course course = new Course(
                            courseJson.getInt("course_id"),
                            courseJson.getInt("professor_id"),
                            courseJson.getInt("department_id"),
                            courseJson.getString("course_name"),
                            courseJson.getString("course_information")
                    );
                    courseObservableList.add(course);
                } catch (JSONException e) {
                    System.out.println("Error al procesar JSON: " + e.getMessage());
                }
            }
        } else {
            System.out.println("La lista de departamentos esta vacia o hubo un error.");
        }
        courses_tv.setItems(courseObservableList);
        coursesToSubscribe_tv.setItems(courseObservableList);
        //////////////////////////////Answere Table//////////////////////////////
        tab_answereID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tab_contentAnswere.setCellValueFactory(new PropertyValueFactory<>("answere"));
        RemoteConnection.GetInstance().getAnswers();
        answersList = RemoteConnection.GetInstance().getAnswersList();
        ObservableList<Answer> answereObservableList = FXCollections.observableArrayList();
        if (answersList != null) {
            for (int i = 0; i < answersList.length(); i++) {
                try {
                    JSONObject answereJson = answersList.getJSONObject(i);
                    Answer answere = new Answer(
                            answereJson.getInt("answer_id"),
                            answereJson.getString("answer")
                    );
                    answereObservableList.add(answere);
                } catch (JSONException e) {
                    System.out.println("Error al procesar JSON: " + e.getMessage());
                }
            }
        } else {
            System.out.println("La lista de respuestas esta vacia o hubo un error.");
        }
        iaAnswere_tv.setItems(answereObservableList);
        //////////////////////////////Students Table//////////////////////////////
        tab_studentID.setCellValueFactory(new PropertyValueFactory<>("userID"));
        tab_subUniversityID.setCellValueFactory(new PropertyValueFactory<>("Uid"));
        tab_studentName.setCellValueFactory(new PropertyValueFactory<>("name"));
        ObservableList<User> studentsObservableList = FXCollections.observableArrayList();
        if (usersList != null) {
            for (int i = 0; i < usersList.length(); i++) {
                try {
                    JSONObject userJson = usersList.getJSONObject(i);
                    User student = new User(
                            null,
                            null,
                            userJson.getString("user_type"),
                            userJson.getString("user_name"),
                            null,
                            userJson.getInt("university_id"),
                            null,
                            userJson.getInt("user_id")
                    );
                    if (student.getType().equals("Estudiante")) {
                        studentsObservableList.add(student);
                    }
                } catch (JSONException e) {
                    System.out.println("Error al procesar JSON: " + e.getMessage());
                }
            }
        } else {
            System.out.println("La lista de estudiantes esta vacia o hubo un error.");
        }
        students_tv.setItems(studentsObservableList);
        //////////////////////////////Registrations Table//////////////////////////////
        tab_registrationID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tab_SubCourseID.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        tab_SubStudentID.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        RemoteConnection.GetInstance().getRegistrations();
        registrationsList = RemoteConnection.GetInstance().getRegistrationsList();
        ObservableList<Registration> registrationObservableList = FXCollections.observableArrayList();
        if (registrationsList != null) {
            for (int i = 0; i < registrationsList.length(); i++) {
                try {
                    JSONObject registrationJson = registrationsList.getJSONObject(i);
                    Registration registration = new Registration(
                            registrationJson.getInt("registration_id"),
                            registrationJson.getInt("course_id"),
                            registrationJson.getInt("student_id")
                    );
                    registrationObservableList.add(registration);
                } catch (JSONException e) {
                    System.out.println("Error al procesar JSON: " + e.getMessage());
                }
            }
        } else {
            System.out.println("La lista de matriculas esta vacia o hubo un error.");
        }
        registration_tv.setItems(registrationObservableList);
    }

    @FXML
    private void MinimizeWindow(MouseEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void GoBack(MouseEvent event) throws IOException {
        App.setRoot("lobby");
    }

    @FXML
    private void AddNewUniver(ActionEvent event) {
        if (RemoteConnection.GetInstance().addUniversity(tf_UniverName.getText())) {
            lbl_info.setText("Universidad creada con exito.");
            tf_UniverName.clear();
            SoundPlayer player = new SoundPlayer("/gread.wav");
            player.play();
            LoadCBX();
        } else {
            lbl_info.setText("Error al crear la Universidad.");
            SoundPlayer player = new SoundPlayer("/error.wav");
            player.play();
        }
        clearInfo();
    }

    @FXML
    private void AddNewFaculty(ActionEvent event) {
        if (getUniversityIdByName(cbx_UniverID.getValue()) != null) {
            if (RemoteConnection.GetInstance().addFaculty(tf_FacultyName.getText(), ta_FacultyDescription.getText(), getUniversityIdByName(cbx_UniverID.getValue()))) {
                lbl_info.setText("Facultad Agregada");
                SoundPlayer player = new SoundPlayer("/gread.wav");
                player.play();
                tf_FacultyName.clear();
                ta_FacultyDescription.clear();
            } else {
                lbl_info.setText("No se agrego la Facultad");
                SoundPlayer player = new SoundPlayer("/error.wav");
                player.play();
            }
            clearInfo();
        }
    }

    @FXML
    private void AddNewDepartment(ActionEvent event) {
        if (getFacultyIdByName(cbx_facultyID.getValue()) != null) {
            if (RemoteConnection.GetInstance().addDepartment(tf_DepartName.getText(), ta_DepartmentDescription.getText(), getFacultyIdByName(cbx_facultyID.getValue()))) {
                lbl_info.setText("Departamento Agregado");
                LoadCBX();
                SoundPlayer player = new SoundPlayer("/gread.wav");
                player.play();
                tf_DepartName.clear();
                ta_DepartmentDescription.clear();
            }
        } else {
            lbl_info.setText("No se agrego el Departamento");
            SoundPlayer player = new SoundPlayer("/error.wav");
            player.play();
        }
        clearInfo();
    }

    @FXML
    private void AddNewAnswereIA(ActionEvent event) {
        if (RemoteConnection.GetInstance().addAnswer(ta_answereForIA.getText())) {
            lbl_info.setText("Respuesta Guardada.");
            SoundPlayer player = new SoundPlayer("/gread.wav");
            player.play();
            ta_answereForIA.clear();
        } else {
            lbl_info.setText("Error al crear la nueva respuesta.");
            SoundPlayer player = new SoundPlayer("/error.wav");
            player.play();
        }
        clearInfo();
    }

    @FXML
    private void AddNewCourse(ActionEvent event) {
        if (getDepartmentsIdByName(cbx_departmentID.getValue()) != null && getProfessorsIdByName(cbx_professorID.getValue()) != null) {
            if (RemoteConnection.GetInstance().addCourse(getProfessorsIdByName(cbx_professorID.getValue()), getDepartmentsIdByName(cbx_departmentID.getValue()), tf_CourseName.getText(), ta_CourseDescription.getText())) {
                lbl_info.setText("Curso Agregado");
                SoundPlayer player = new SoundPlayer("/gread.wav");
                player.play();
                tf_CourseName.clear();
                ta_CourseDescription.clear();
            } else {
                lbl_info.setText("No se agrego el Curso");
                SoundPlayer player = new SoundPlayer("/error.wav");
                player.play();
            }
        }
        clearInfo();
    }

    @FXML
    private void AddNewUser(ActionEvent event) {
        if (getUniversityIdByName(cbx_UniverIDtoAddUser.getValue()) != null && cbx_type.getValue() != null) {
            if (RemoteConnection.GetInstance().registerUser(getUniversityIdByName(cbx_UniverIDtoAddUser.getValue()), tf_UserName.getText(), tf_user.getText(), cbx_type.getValue(), tf_pass.getText())) {
                lbl_info.setText("Usuario agregado");
                SoundPlayer player = new SoundPlayer("/gread.wav");
                player.play();
                loadTables();
                tf_UserName.clear();
                tf_user.clear();
                tf_pass.clear();
            } else {
                lbl_info.setText("No se agrego el usuario");
                SoundPlayer player = new SoundPlayer("/error.wav");
                player.play();
            }
            clearInfo();
        }
    }

    @FXML
    private void GetUserData(MouseEvent event) {
        selectedUser = users_tv.getSelectionModel().getSelectedItem();
        if (selectedUser.getName() != null) {
            btn_deleteUser.setDisable(false);
            btn_editUserInfo.setDisable(false);
            tf_userEditCed.setText(getUserCed(selectedUser.getUserID()));
            tf_userEditName.setText(selectedUser.getName());
            cbx_UniverIDtoEdit.setValue(getUniversityNameById(selectedUser.getUid()));
            cbx_typeToEdit.setValue(selectedUser.getType());
        }

    }

    @FXML
    private void DeleteUser(ActionEvent event) {
        RemoteConnection.GetInstance().deleteUser(selectedUser.getName());
        loadTables();
        btn_deleteUser.setDisable(true);
        btn_editUserInfo.setDisable(true);
        SoundPlayer player = new SoundPlayer("/gread.wav");
        player.play();
        tf_userEditName.clear();
        tf_userEditCed.clear();
    }

    @FXML
    private void DoUserEdit(ActionEvent event) {
        if (RemoteConnection.GetInstance().updateUser(selectedUser.getUserID(), getUniversityIdByName(cbx_UniverIDtoEdit.getValue()), tf_userEditName.getText(), cbx_typeToEdit.getValue(), selectedUser.getPass(), tf_userEditCed.getText())) {
            lbl_info.setText("Usuario Editado");
            SoundPlayer player = new SoundPlayer("/edit.wav");
            player.play();
            tf_userEditName.clear();
            tf_userEditCed.clear();
        } else {
            lbl_info.setText("Usuario NO editado");
            SoundPlayer player = new SoundPlayer("/error.wav");
            player.play();
        }
        clearInfo();
        loadTables();
    }

    @FXML
    private void DeleteSelection(ActionEvent event) {
        switch (selectedTable) {
            case 1:
                if (RemoteConnection.GetInstance().deleteUniversity(selectedUniversity.getName())) {
                    lbl_info.setText("Universidad eliminada");
                    SoundPlayer player = new SoundPlayer("/gread.wav");
                    player.play();
                } else {
                    lbl_info.setText("No se pudo eliminar la universidad");
                    SoundPlayer player = new SoundPlayer("/error.wav");
                    player.play();
                }
                selectedUniversity = new University();
                selectedTable = 0;

                break;
            case 2:
                if (RemoteConnection.GetInstance().deleteFaculty(selectedFaculty.getName())) {
                    lbl_info.setText("Facultad eliminada");
                    SoundPlayer player = new SoundPlayer("/gread.wav");
                    player.play();
                } else {
                    lbl_info.setText("No se pudo eliminar la Facultad");
                    SoundPlayer player = new SoundPlayer("/error.wav");
                    player.play();
                }
                selectedFaculty = new Faculty();
                selectedTable = 0;

                break;
            case 3:
                if (RemoteConnection.GetInstance().deleteDepartment(selectedDepartment.getDepartmentName())) {
                    lbl_info.setText("Departamento eliminado");
                    SoundPlayer player = new SoundPlayer("/gread.wav");
                    player.play();
                } else {
                    lbl_info.setText("No se pudo eliminar la Departamento");
                    SoundPlayer player = new SoundPlayer("/error.wav");
                    player.play();
                }
                selectedDepartment = new Department();
                selectedTable = 0;

                break;
            case 4:
                if (RemoteConnection.GetInstance().deleteAnswer(selectedAnswere.getId())) {
                    lbl_info.setText("Respuesta eliminada");
                    SoundPlayer player = new SoundPlayer("/gread.wav");
                    player.play();
                } else {
                    lbl_info.setText("No se pudo eliminar la Respuesta");
                    SoundPlayer player = new SoundPlayer("/error.wav");
                    player.play();
                }
                selectedAnswere = new Answer();
                selectedTable = 0;

                break;
            case 5:
                if (RemoteConnection.GetInstance().deleteCourse(selectedCourse.getCourseName())) {
                    lbl_info.setText("Curso eliminado");
                    SoundPlayer player = new SoundPlayer("/gread.wav");
                    player.play();
                } else {
                    lbl_info.setText("No se pudo eliminar el Curso");
                    SoundPlayer player = new SoundPlayer("/error.wav");
                    player.play();
                }
                selectedCourse = new Course();
                selectedTable = 0;

                break;
        }
        loadTables();
        btn_deleteSelection.setDisable(true);
        btn_ChangeTab.setDisable(true);
        clearInfo();
    }

    @FXML
    private void GoToEditTab(ActionEvent event) {
        tp_sections.getSelectionModel().select(2);
        if (selectedTable == 1) {
            tf_EditUniverName.setText(selectedUniversity.getName());
            tf_UniversalID.setText(selectedUniversity.getId().toString());
            EditUniversidad_ap.setDisable(false);
            DropShadowToAnchorPane(EditUniversidad_ap, "#ffd700");
        } else if (selectedTable == 2) {
            tf_EditFacultyName.setText(selectedFaculty.getName());
            cbx_EditUniverID.setValue(getUniversityNameById(selectedFaculty.getUniverID()));
            ta_EditFacultyDescription.setText(selectedFaculty.getDescription());
            tf_UniversalID.setText(selectedFaculty.getId().toString());
            DropShadowToAnchorPane(EditFacultad_ap, "#ffd700");
            EditFacultad_ap.setDisable(false);
        } else if (selectedTable == 3) {
            tf_EditDepartName.setText(selectedDepartment.getDepartmentName());
            cbx_EditfacultyID.setValue(getFacultyNameById(selectedDepartment.getFacultyId()));
            ta_EditDepartmentDescription.setText(selectedDepartment.getDepartmentInformation());
            tf_UniversalID.setText(selectedDepartment.getDepartmentId().toString());
            DropShadowToAnchorPane(EditDepartment_ap, "#ffd700");
            EditDepartment_ap.setDisable(false);
        } else if (selectedTable == 4) {
            tf_UniversalID.setText(selectedAnswere.getId().toString());
            ta_EditAnswereForIA.setText(selectedAnswere.getAnswere());
            DropShadowToAnchorPane(EditAnswere_ap, "#ffd700");
            EditAnswere_ap.setDisable(false);
        } else if (selectedTable == 5) {
            tf_EditCourseName.setText(selectedCourse.getCourseName());
            cbx_EditProfessorID.setValue(getProfessorsNameById(selectedCourse.getProfessorId()));
            cbx_EditdepartmentID.setValue(getDepartmentsNameById(selectedCourse.getDepartmentId()));
            ta_EditCourseDescription.setText(selectedCourse.getCourseInformation());
            tf_UniversalID.setText(selectedCourse.getCourseId().toString());
            DropShadowToAnchorPane(EditCurso_ap, "#ffd700");
            EditCurso_ap.setDisable(false);
        }
        btn_deleteSelection.setDisable(true);
        btn_ChangeTab.setDisable(true);
        btn_saveChanges.setDisable(false);
        SoundPlayer player = new SoundPlayer("/gread.wav");
        player.play();
    }

    @FXML
    private void GetUniversityData(MouseEvent event) {
        try {
            selectedUniversity = university_tv.getSelectionModel().getSelectedItem();
            selectedTable = 1;
            if (selectedUniversity.getName() != null) {
                btn_deleteSelection.setDisable(false);
                btn_ChangeTab.setDisable(false);
                btn_deleteSelection.setText("Eliminar Universidad");
            } else {
                btn_deleteSelection.setDisable(true);
                btn_ChangeTab.setDisable(true);
            }
        } catch (Exception e) {
            System.out.println("ERROR \"GetUniversityData\": " + e);
        }

    }

    @FXML
    private void GetFacultyData(MouseEvent event) {
        try {
            selectedFaculty = faculties_tv.getSelectionModel().getSelectedItem();
            selectedTable = 2;
            if (selectedFaculty.getName() != null) {
                btn_deleteSelection.setDisable(false);
                btn_ChangeTab.setDisable(false);
                btn_deleteSelection.setText("Eliminar Facultad");
            } else {
                btn_deleteSelection.setDisable(true);
                btn_ChangeTab.setDisable(true);
            }
        } catch (Exception e) {
            System.out.println("ERROR \"GetFacultyData\": " + e);
        }
    }

    @FXML
    private void GetDepartmentData(MouseEvent event) {
        try {
            selectedDepartment = departtments_tv.getSelectionModel().getSelectedItem();
            selectedTable = 3;
            if (selectedDepartment.getDepartmentName() != null) {
                btn_deleteSelection.setDisable(false);
                btn_ChangeTab.setDisable(false);
                btn_deleteSelection.setText("Eliminar Departamento");
            } else {
                btn_deleteSelection.setDisable(true);
                btn_ChangeTab.setDisable(true);
            }
        } catch (Exception e) {
            System.out.println("ERROR \"GetDepartmentData\": " + e);
        }
    }

    @FXML
    private void GetAnswereData(MouseEvent event) {
        try {
            selectedAnswere = iaAnswere_tv.getSelectionModel().getSelectedItem();
            selectedTable = 4;
            if (selectedAnswere.getId() != null) {
                btn_deleteSelection.setDisable(false);
                btn_ChangeTab.setDisable(false);
                btn_deleteSelection.setText("Eliminar Respuesta");
            } else {
                btn_deleteSelection.setDisable(true);
                btn_ChangeTab.setDisable(true);
            }
        } catch (Exception e) {
            System.out.println("ERROR \"GetAnswereData\": " + e);
        }
    }

    @FXML
    private void GetCourseData(MouseEvent event) {
        try {
            if (tp_sections.getSelectionModel().getSelectedIndex() == 1) {
                selectedCourse = courses_tv.getSelectionModel().getSelectedItem();
                selectedTable = 5;
                if (selectedCourse.getCourseName() != null) {
                    btn_deleteSelection.setDisable(false);
                    btn_ChangeTab.setDisable(false);
                    btn_deleteSelection.setText("Eliminar Curso");
                } else {
                    btn_deleteSelection.setDisable(true);
                    btn_ChangeTab.setDisable(true);
                }
            } else {
                selectedCourse = coursesToSubscribe_tv.getSelectionModel().getSelectedItem();
                tf_subCourseID.setText(selectedCourse.getCourseId().toString());
            }

        } catch (Exception e) {
            System.out.println("ERROR \"GetCourseData\": " + e);
        }
    }

    @FXML
    private void ReloadTables(MouseEvent event) {
        loadTables();
    }

    @FXML
    private void GetStudentData(MouseEvent event) {
        try {
            selectedUser = students_tv.getSelectionModel().getSelectedItem();
            tf_subStudentID.setText(selectedUser.getUserID().toString());
        } catch (Exception e) {
            System.out.println("ERROR \"GetFacultyData\": " + e);
        }
    }

    @FXML
    private void CreateRegistration(ActionEvent event) {
        if (RemoteConnection.GetInstance().addRegistration(Integer.parseInt(tf_subStudentID.getText()), Integer.parseInt(tf_subCourseID.getText()))) {
            lbl_info.setText("Matricula exitosa.");
            tf_subStudentID.clear();
            tf_subCourseID.clear();
            loadTables();
            SoundPlayer player = new SoundPlayer("/gread.wav");
            player.play();
        } else {
            lbl_info.setText("No se pudo matricular al estudiante.");
        }
        clearInfo();
    }

    @FXML
    private void GetRegistrationData(MouseEvent event) {
        try {
            selectedRegistration = registration_tv.getSelectionModel().getSelectedItem();
            if (selectedRegistration.getId() != null) {
                editRegistration_ap.setDisable(false);
                btn_deleteRegistration.setDisable(false);
                btn_deleteRegistration.setText("Eliminar: " + selectedRegistration.getId());
                tf_subEditCourseID.setText(selectedRegistration.getCourseId().toString());
                tf_subEditStudentID.setText(selectedRegistration.getStudentId().toString());
            }
        } catch (Exception e) {
            System.out.println("ERROR \"GetRegistrationData\": " + e);
        }
    }

    @FXML
    private void EditRegistration(ActionEvent event) {
        if (RemoteConnection.GetInstance().updateRegistration(selectedRegistration.getId(), Integer.parseInt(tf_subEditStudentID.getText()), Integer.parseInt(tf_subEditCourseID.getText()))) {
            lbl_info.setText("Matricula actualizada");
            editRegistration_ap.setDisable(true);
            btn_deleteRegistration.setDisable(true);
            loadTables();
            tf_subEditStudentID.clear();
            tf_subEditCourseID.clear();
            SoundPlayer player = new SoundPlayer("/edit.wav");
            player.play();
        } else {
            lbl_info.setText("Error al actualizar la matricula");
        }
        clearInfo();
    }

    @FXML
    private void DeleteRegistration(ActionEvent event) {
        if (RemoteConnection.GetInstance().deleteRegistration(selectedRegistration.getId())) {
            lbl_info.setText("Matricula eliminada correctamente.");
            loadTables();
            btn_deleteRegistration.setDisable(true);
            btn_deleteRegistration.setText("Eliminar: ?");
            editRegistration_ap.setDisable(true);
            tf_subEditCourseID.clear();
            tf_subEditStudentID.clear();
            SoundPlayer player = new SoundPlayer("/gread.wav");
            player.play();
        } else {
            lbl_info.setText("Error al eliminar la matricula.");
            SoundPlayer player = new SoundPlayer("/error.wav");
            player.play();
        }
        clearInfo();
    }

    @FXML
    private void FX(MouseEvent event) {
        SoundPlayer player = new SoundPlayer("/fx.wav");
        player.setVolume(-30.0f);
        player.play();
    }

    @FXML
    private void SaveChanges(ActionEvent event) {
        boolean flag = false;
        if (selectedTable == 1) {
            if (RemoteConnection.GetInstance().updateUniversity(Integer.parseInt(tf_UniversalID.getText()), tf_EditUniverName.getText())) {
                lbl_info.setText("Universidad Editada con exito.");
                btn_saveChanges.setDisable(true);
                EditUniversidad_ap.setDisable(true);
                DropShadowToAnchorPane(EditUniversidad_ap, "#000000");
                flag = true;
                tf_UniversalID.clear();
                tf_EditUniverName.clear();
            } else {
                lbl_info.setText("Error al editar la Universidad.");
                flag = false;
            }

        } else if (selectedTable == 2) {
            if (RemoteConnection.GetInstance().updateFaculty(Integer.parseInt(tf_UniversalID.getText()), tf_EditFacultyName.getText(), ta_EditFacultyDescription.getText(), getUniversityIdByName(cbx_EditUniverID.getValue()))) {
                lbl_info.setText("Facultad Actualizada");
                btn_saveChanges.setDisable(true);
                EditFacultad_ap.setDisable(true);              
                DropShadowToAnchorPane(EditFacultad_ap, "#000000");
                flag = true;
                tf_UniversalID.clear();
                tf_EditFacultyName.clear();
                ta_EditFacultyDescription.clear();
            } else {
                lbl_info.setText("Error al actualizar la facultad");
                flag = false;
            }

        } else if (selectedTable == 3) {
            if (RemoteConnection.GetInstance().updateDepartment(Integer.parseInt(tf_UniversalID.getText()), tf_EditDepartName.getText(), ta_EditDepartmentDescription.getText(), getFacultyIdByName(cbx_EditfacultyID.getValue()))) {
                lbl_info.setText("Departamento editado con exito");
                btn_saveChanges.setDisable(true);
                EditDepartment_ap.setDisable(true);
                DropShadowToAnchorPane(EditDepartment_ap, "#000000");
                flag = true;
                tf_UniversalID.clear();
                tf_EditDepartName.clear();
                ta_EditDepartmentDescription.clear();
            } else {
                lbl_info.setText("Error al editar el departamento");
                flag = false;
            }
        } else if (selectedTable == 4) {
            if (RemoteConnection.GetInstance().updateAnswer(Integer.parseInt(tf_UniversalID.getText()), ta_EditAnswereForIA.getText())) {
                lbl_info.setText("Respuesta de IA editada con exito");
                btn_saveChanges.setDisable(true);
                EditAnswere_ap.setDisable(true);             
                DropShadowToAnchorPane(EditAnswere_ap, "#000000");
                flag = true;
                tf_UniversalID.clear();
                ta_EditAnswereForIA.clear();
            } else {
                lbl_info.setText("No se puto editar las respuesta de IA");
                flag = false;
            }
        } else if (selectedTable == 5) {
            if (RemoteConnection.GetInstance().updateCourse(Integer.parseInt(tf_UniversalID.getText()), getProfessorsIdByName(cbx_EditProfessorID.getValue()), getDepartmentsIdByName(cbx_EditdepartmentID.getValue()), tf_EditCourseName.getText(), ta_EditCourseDescription.getText())) {
                lbl_info.setText("Curso editado con exito");
                btn_saveChanges.setDisable(true);
                EditCurso_ap.setDisable(true);             
                DropShadowToAnchorPane(EditCurso_ap, "#000000");
                flag = true;
                tf_UniversalID.clear();
                tf_EditCourseName.clear();
                ta_EditCourseDescription.clear();
            } else {
                lbl_info.setText("Error al actualizar el curso");
                flag = false;
            }
        }
        clearInfo();
        if(flag == true){
            SoundPlayer player = new SoundPlayer("/gread.wav");
            player.play();
        }else{
            SoundPlayer player = new SoundPlayer("/error.wav");
            player.play();
        }
    }
}
