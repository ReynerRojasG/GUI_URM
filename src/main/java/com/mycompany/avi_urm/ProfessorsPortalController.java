/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.avi_urm;

import Objects.Assignment;
import Objects.Course;
import Objects.Registration;
import Objects.Submission;
import Objects.User;
import Player.SoundPlayer;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author Makin Artavia
 */
public class ProfessorsPortalController implements Initializable {

    private JSONArray departmentsList = new JSONArray();
    private JSONArray coursesList = new JSONArray();
    private JSONArray usersList = new JSONArray();
    private JSONArray assignmentsList = new JSONArray();
    private JSONArray SubmissionsList = new JSONArray();
    private User selectedUser = new User();
    private Submission selectedSubmission = new Submission();
    private JSONArray registrationsList = new JSONArray();
    private Registration selectedRegistration = new Registration();
    private Course selectedCourse = new Course();

    @FXML
    private Label lbl_minimize;
    @FXML
    private Label lbl_exit;
    @FXML
    private TextField tf_courseName;
    @FXML
    private Button btn_createCourse;
    @FXML
    private TextArea ta_courseDescription;
    @FXML
    private ChoiceBox<String> cbx_departmentID;
    @FXML
    private TableView<Course> courses_tv;
    @FXML
    private TableColumn<?, ?> tab_courseID;
    @FXML
    private TableColumn<?, ?> tab_courseDepartID;
    @FXML
    private TableColumn<?, ?> tab_courseName;
    @FXML
    private TableColumn<?, ?> tab_courseInfo;
    @FXML
    private TextField tf_editCourseName;
    @FXML
    private Button btn_editCourse;
    @FXML
    private TextArea ta_editCourseDescription;
    @FXML
    private ChoiceBox<String> cbx_editDepartmentID;
    @FXML
    private Button btn_deleteCourse;
    @FXML
    private Label lbl_info;
    @FXML
    private AnchorPane editCourse_ap;
    @FXML
    private ScrollPane sp_professorCourses;
    @FXML
    private TextField tf_courseNameForAssig;
    @FXML
    private ChoiceBox<String> cbx_typeOfAssig;
    @FXML
    private TextField tf_InitialAssig;
    @FXML
    private TextField tf_FinalAssig;
    @FXML
    private TextArea ta_assignmentDescription;
    @FXML
    private Button btn_AddAssig;
    @FXML
    private ChoiceBox<String> cbx_typeOfAssigEdit;
    @FXML
    private TextField tf_InitialAssigEdit;
    @FXML
    private TextField tf_FinalAssigEdit;
    @FXML
    private TextArea ta_AssignamentDescriptionEdit;
    @FXML
    private Button btn_editAssig;
    @FXML
    private TableView<Assignment> Assignment_tv;
    @FXML
    private Button btn_deleteAssig;
    @FXML
    private Button btn_enableEdit;
    @FXML
    private TableView<User> students_tv;
    @FXML
    private TableColumn<?, ?> tab_studentID;
    @FXML
    private TableColumn<?, ?> tab_subUniversityID;
    @FXML
    private TableColumn<?, ?> tab_studentName;
    @FXML
    private TableColumn<?, ?> tab_RegStudentID;
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
    private AnchorPane editAssig_ap;
    @FXML
    private Label lbl_assigError;
    @FXML
    private TableColumn<?, ?> tab_assignmentID;
    @FXML
    private TableColumn<?, ?> tab_assignmentDescription;
    @FXML
    private TableColumn<?, ?> tab_assignmentCourse;
    @FXML
    private TableColumn<?, ?> tab_assignmentSDate;
    @FXML
    private TableColumn<?, ?> tab_assignmentFDate;
    private Assignment selectedAssignment;
    @FXML
    private Label lbl_courseofAssig;
    @FXML
    private Label lbl_assigError1;
    @FXML
    private Label lbl_selectedCourse;
    @FXML
    private ChoiceBox<Integer> cbx_AssigID;
    @FXML
    private TableView<Submission> Submission_tv;
    @FXML
    private Button btn_filter;
    @FXML
    private Button btn_checkSub;
    @FXML
    private TableColumn<?, ?> tab_SubmissionID;
    @FXML
    private TableColumn<?, ?> tab_SubAssigID;
    @FXML
    private TableColumn<?, ?> tab_score;
    @FXML
    private TableColumn<?, ?> tab_sendDate;
    @FXML
    private TableColumn<?, ?> tab_fileRoute;
    @FXML
    private TableColumn<?, ?> tab_iaAnswere;
    @FXML
    private TableColumn<?, ?> tab_ProfAnswere;
    @FXML
    private TableColumn<?, ?> tab_status;
    @FXML
    private TabPane Professors_tp;
    @FXML
    private TextField tf_submissionURL;
    @FXML
    private TextArea ta_submissionObservation;
    @FXML
    private TextField tf_submissionScore;
    @FXML
    private Button btn_saveRating;
    @FXML
    private Button btn_autoRating;
    @FXML
    private TextField tf_submissionID;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        LoadCBX();
        LoadTables();
        LoadScrolPane();
        SetFormatForDates(tf_InitialAssig);
        SetFormatForDates(tf_FinalAssig);
        SetFormatForDates(tf_InitialAssigEdit);
        SetFormatForDates(tf_FinalAssigEdit);
        cbx_typeOfAssig.getItems().addAll("Tarea", "Proyecto", "Laboratorio", "Foro", "Examen");
        cbx_typeOfAssigEdit.getItems().addAll("Tarea", "Proyecto", "Laboratorio", "Foro", "Examen");
    }

    private void SetFormatForDates(TextField dateField) {
        dateField.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();

            if (newText.length() > 10) {
                return null;
            }

            if (change.isAdded()) {
                int length = change.getControlText().length();
                if (length == 1 || length == 4) {
                    change.setText(change.getText() + "/");
                    int newPosition = length + 2;
                    if (newPosition <= change.getControlNewText().length()) {
                        change.selectRange(newPosition, newPosition);
                    }
                }
            }

            if (!newText.matches("\\d{0,2}(/\\d{0,2})?(/\\d{0,4})?")) {
                return null;
            }

            return change;
        }));
    }

    private void LoadScrolPane() {
        HBox hBox = new HBox(10);
        sp_professorCourses.setContent(hBox);
        for (int i = 0; i < coursesList.length(); i++) {
            try {
                JSONObject courseJson = coursesList.getJSONObject(i);
                if (courseJson.getInt("professor_id") == App.getUser().getUserID()) {

                    Button button = new Button(courseJson.getString("course_name"));
                    style(button);
                    button.setOnMouseClicked(event -> {
                        tf_courseNameForAssig.setText(button.getText());
                        lbl_courseofAssig.setText(button.getText());
                        lbl_selectedCourse.setText(button.getText());
                        FilterTable(button.getText());
                        getIdsOfAssig();
                        SoundPlayer player = new SoundPlayer("/click.wav");
                        player.play();
                    });
                    button.setOnMouseEntered(event -> {
                        FX(event);
                    });
                    hBox.getChildren().add(button);
                }
            } catch (JSONException e) {
                System.out.println("Error al procesar JSON: " + e.getMessage());
            }
        }
    }

    private void getIdsOfAssig() {
        cbx_AssigID.getItems().clear();
        TableColumn<Assignment, Integer> firstColumn = (TableColumn<Assignment, Integer>) Assignment_tv.getColumns().get(0);

        for (Assignment assignment : Assignment_tv.getItems()) {

            Integer assigID = firstColumn.getCellObservableValue(assignment).getValue();
            if (assigID != null) {
                cbx_AssigID.getItems().add(assigID);
            }
        }
    }

    private void style(Button button) {
        button.setStyle(
                "-fx-background-color: #0d2c5b; "
                + "-fx-text-fill: white; "
                + "-fx-border-color: #ffbc21; "
                + "-fx-border-radius: 10; "
                + "-fx-background-radius: 10; "
                + "-fx-font-family: 'Times New Roman'; "
                + "-fx-font-size: 27px;");

    }

    private void FilterTable(String course) {
        ObservableList<Assignment> assignmentObservableList = FXCollections.observableArrayList();
        for (int i = 0; i < assignmentsList.length(); i++) {
            JSONObject assignament = assignmentsList.getJSONObject(i);
            if (assignament.getInt("course_id") == getCourseIdByName(course) && assignament.getInt("professor_id") == App.getUser().getUserID()) {
                Assignment assig = new Assignment(
                        assignament.getString("course_statement"),
                        assignament.getString("initial_date"),
                        assignament.getString("final_date"),
                        assignament.getInt("assignment_id"),
                        assignament.getInt("professor_id"),
                        assignament.getInt("course_id"),
                        assignament.getString("assignment_type")
                );
                assignmentObservableList.add(assig);
            }
        }

        Assignment_tv.setItems(assignmentObservableList);
    }

    private int getCourseIdByName(String name) {
        for (int i = 0; i < coursesList.length(); i++) {
            JSONObject couser = coursesList.getJSONObject(i);
            if (couser.getString("course_name").equals(name)) {
                return couser.getInt("course_id");
            }
        }
        return 0;
    }

    private void LoadCBX() {
        //////////////////////Cargar el de los departamentos//////////////////////////////
        RemoteConnection.GetInstance().getDepartments();
        departmentsList = RemoteConnection.GetInstance().getDepartmentsList();
        cbx_departmentID.getItems().clear();
        if (departmentsList != null) {
            for (int i = 0; i < departmentsList.length(); i++) {
                try {
                    JSONObject department = departmentsList.getJSONObject(i);
                    String departmentName = department.getString("department_name");
                    cbx_departmentID.getItems().add(departmentName);
                    cbx_editDepartmentID.getItems().add(departmentName);
                } catch (JSONException e) {
                    System.out.println("Error al procesar JSON: " + e.getMessage());
                }
            }
        } else {
            System.out.println("La lista de Facultades está vacía o hubo un error.");
        }
    }

    private void LoadTables() {
        //////////////////////////////Course Table//////////////////////////////
        tab_courseID.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        tab_courseDepartID.setCellValueFactory(new PropertyValueFactory<>("departmentId"));
        tab_courseName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        tab_courseInfo.setCellValueFactory(new PropertyValueFactory<>("courseInformation"));
        //courses to subcribe
        tab_subCourseID.setCellValueFactory(new PropertyValueFactory<>("courseId"));
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
                    if (courseJson.getInt("professor_id") == App.getUser().getUserID()) {
                        Course course = new Course(
                                courseJson.getInt("course_id"),
                                courseJson.getInt("professor_id"),
                                courseJson.getInt("department_id"),
                                courseJson.getString("course_name"),
                                courseJson.getString("course_information")
                        );
                        courseObservableList.add(course);
                    }

                } catch (JSONException e) {
                    System.out.println("Error al procesar JSON: " + e.getMessage());
                }
            }
        } else {
            System.out.println("La lista de departamentos esta vacia o hubo un error.");
        }
        courses_tv.setItems(courseObservableList);
        coursesToSubscribe_tv.setItems(courseObservableList);

        //////////////////////////////Students Table//////////////////////////////
        tab_studentID.setCellValueFactory(new PropertyValueFactory<>("userID"));
        tab_subUniversityID.setCellValueFactory(new PropertyValueFactory<>("Uid"));
        tab_studentName.setCellValueFactory(new PropertyValueFactory<>("name"));
        ObservableList<User> studentsObservableList = FXCollections.observableArrayList();

        RemoteConnection.GetInstance().getUsers();
        usersList = RemoteConnection.GetInstance().getUsersList();
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
        tab_RegStudentID.setCellValueFactory(new PropertyValueFactory<>("studentId"));
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
        ////////////////////////////// Assignment Table //////////////////////////////
        tab_assignmentDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        tab_assignmentSDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        tab_assignmentFDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        tab_assignmentID.setCellValueFactory(new PropertyValueFactory<>("assigID"));
        tab_assignmentCourse.setCellValueFactory(new PropertyValueFactory<>("courseID"));

        RemoteConnection.GetInstance().getAssignments();
        assignmentsList = RemoteConnection.GetInstance().getAssignmentsList();

        ObservableList<Assignment> assignmentObservableList = FXCollections.observableArrayList();

        int currentUserId = App.getUser().getUserID();

        if (assignmentsList != null) {
            for (int i = 0; i < assignmentsList.length(); i++) {
                try {
                    JSONObject assignmentJson = assignmentsList.getJSONObject(i);
                    if (assignmentJson.getInt("professor_id") == currentUserId) {
                        Assignment assignment = new Assignment(
                                assignmentJson.getString("course_statement"),
                                assignmentJson.getString("initial_date"),
                                assignmentJson.getString("final_date"),
                                assignmentJson.getInt("assignment_id"),
                                assignmentJson.getInt("professor_id"),
                                assignmentJson.getInt("course_id"),
                                assignmentJson.getString("assignment_type")
                        );
                        assignmentObservableList.add(assignment);
                    }
                } catch (JSONException e) {
                    System.out.println("Error al procesar JSON de asignación: " + e.getMessage());
                }
            }
        } else {
            System.out.println("No se encontraron asignaciones disponibles.");
        }
        Assignment_tv.setItems(assignmentObservableList);
    }

    private void clearInfo() {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        lbl_info.setDisable(false);
        executorService.schedule(() -> {
            Platform.runLater(() -> {
                lbl_info.setText("");
                lbl_assigError.setText("");
                lbl_info.setDisable(true);
            });
            executorService.shutdown();
        }, 2, TimeUnit.SECONDS);
    }

    @FXML
    private void FX(MouseEvent event) {
        SoundPlayer player = new SoundPlayer("/fx.wav");
        player.setVolume(-30.0f);
        player.play();
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

    private Integer getDepartmentsIdByName(String departmentName) {
        for (int i = 0; i < departmentsList.length(); i++) {
            JSONObject department = departmentsList.getJSONObject(i);
            if (department.getString("department_name").equals(departmentName)) {
                return department.getInt("department_id");
            }
        }
        return null;
    }

    private String getDepartmentsNameById(int departmentId) {
        for (int i = 0; i < departmentsList.length(); i++) {
            JSONObject department = departmentsList.getJSONObject(i);
            if (department.getInt("department_id") == departmentId) {
                return department.getString("department_name");
            }
        }
        return null;
    }

    private Integer getCouserIdByName(String courseName) {
        for (int i = 0; i < coursesList.length(); i++) {
            JSONObject course = coursesList.getJSONObject(i);
            if (course.getString("course_name").equals(courseName)) {
                return course.getInt("course_id");
            }
        }
        return null;
    }

    @FXML
    private void AddNewCourse(ActionEvent event) {
        if (getDepartmentsIdByName(cbx_departmentID.getValue()) != null) {
            if (RemoteConnection.GetInstance().addCourse(App.getUser().getUserID(), getDepartmentsIdByName(cbx_departmentID.getValue()), tf_courseName.getText(), ta_courseDescription.getText())) {
                lbl_info.setText("Curso Agregado");
                tf_courseName.clear();
                ta_courseDescription.clear();
                LoadTables();
                LoadScrolPane();
                SoundPlayer player = new SoundPlayer("/gread.wav");
                player.play();
            } else {
                lbl_info.setText("No se agrego el Curso");
                SoundPlayer player = new SoundPlayer("/error.wav");
                player.play();
            }
            clearInfo();
        }
    }

    @FXML
    private void GetCourseData(MouseEvent event) {
        try {
            selectedCourse = courses_tv.getSelectionModel().getSelectedItem();
            if (selectedCourse.getCourseName() != null) {
                btn_deleteCourse.setDisable(false);
                editCourse_ap.setDisable(false);
                cbx_editDepartmentID.setValue(getDepartmentsNameById(selectedCourse.getDepartmentId()));
                tf_editCourseName.setText(selectedCourse.getCourseName());
                ta_editCourseDescription.setText(selectedCourse.getCourseInformation());
            }
        } catch (Exception e) {
            System.out.println("ERROR \"GetCourseData\": " + e);
        }
    }

    @FXML
    private void EditCourse(ActionEvent event) {
        if (RemoteConnection.GetInstance().updateCourse(selectedCourse.getCourseId(), selectedCourse.getProfessorId(), getDepartmentsIdByName(selectedCourse.getCourseName()), tf_editCourseName.getText(), ta_editCourseDescription.getText())) {
            lbl_info.setText("Curso actualizado");
            editCourse_ap.setDisable(true);
            btn_deleteCourse.setDisable(true);
            LoadTables();
            tf_editCourseName.clear();
            ta_editCourseDescription.clear();
            SoundPlayer player = new SoundPlayer("/edit.wav");
            player.play();
        } else {
            lbl_info.setText("Error al actualizar el curso");
            SoundPlayer player = new SoundPlayer("/error.wav");
            player.play();
        }
        clearInfo();
    }

    @FXML
    private void DeleteCourse(ActionEvent event) {
        if (RemoteConnection.GetInstance().deleteCourse(selectedCourse.getCourseName())) {
            lbl_info.setText("Curso eliminado");
            LoadTables();
            editCourse_ap.setDisable(true);
            tf_editCourseName.clear();
            ta_editCourseDescription.clear();
            btn_editCourse.setDisable(true);
            btn_deleteCourse.setDisable(true);
            LoadScrolPane();
            SoundPlayer player = new SoundPlayer("/gread.wav");
            player.play();
        } else {
            lbl_info.setText("No se pudo eliminar el Curso");
            SoundPlayer player = new SoundPlayer("/error.wav");
            player.play();
        }
        selectedCourse = new Course();
        clearInfo();
    }

    @FXML
    private void CreateNewAssig(ActionEvent event) {
        try {
            RemoteConnection.GetInstance().addAssignment(getCouserIdByName(tf_courseNameForAssig.getText()), App.getUser().getUserID(), ta_assignmentDescription.getText(), cbx_typeOfAssig.getValue(), tf_InitialAssig.getText(), tf_FinalAssig.getText());
            LoadTables();
            tf_courseNameForAssig.clear();
            ta_assignmentDescription.clear();
            tf_InitialAssig.clear();
            tf_FinalAssig.clear();
            lbl_courseofAssig.setText("Todas");
            SoundPlayer player = new SoundPlayer("/gread.wav");
            player.play();
        } catch (Exception e) {
            lbl_assigError.setText("Error");
            SoundPlayer player = new SoundPlayer("/error.wav");
            player.play();
        }
        clearInfo();
    }

    @FXML
    private void SaveAssigChanges(ActionEvent event) {
        if (RemoteConnection.GetInstance().updateAssignment(selectedAssignment.getAssigID(), selectedAssignment.getCourseID(), App.getUser().getUserID(), ta_AssignamentDescriptionEdit.getText(), cbx_typeOfAssigEdit.getValue(), tf_InitialAssigEdit.getText(), tf_FinalAssigEdit.getText())) {
            lbl_info.setText("Cambio efectuado.");
            editAssig_ap.setDisable(true);
            LoadTables();
            ta_AssignamentDescriptionEdit.clear();
            tf_InitialAssigEdit.clear();
            tf_FinalAssigEdit.clear();
            btn_checkSub.setDisable(true);
            lbl_courseofAssig.setText("Todas");
            SoundPlayer player = new SoundPlayer("/edit.wav");
            player.play();
        } else {
            lbl_info.setText("Error.");
            SoundPlayer player = new SoundPlayer("/error.wav");
            player.play();
        }
        clearInfo();
    }

    @FXML
    private void DeleteAssignment(ActionEvent event) {
        if (selectedAssignment != null) {
            if (RemoteConnection.GetInstance().deleteAssignment(selectedAssignment.getAssigID())) {
                LoadTables();
                btn_deleteAssig.setDisable(true);
                btn_enableEdit.setDisable(true);
                btn_checkSub.setDisable(true);
                SoundPlayer player = new SoundPlayer("/gread.wav");
                player.play();
            } else {
                lbl_info.setText("Algo salio mal.");
                lbl_info.setDisable(false);
                SoundPlayer player = new SoundPlayer("/error.wav");
                player.play();
                clearInfo();
            }
        }

    }

    @FXML
    private void EnableEdit(ActionEvent event) {
        btn_deleteAssig.setDisable(true);
        editAssig_ap.setDisable(false);
        cbx_typeOfAssigEdit.setValue(selectedAssignment.getType());
        tf_InitialAssigEdit.setText(selectedAssignment.getStartDate());
        tf_FinalAssigEdit.setText(selectedAssignment.getEndDate());
        ta_AssignamentDescriptionEdit.setText(selectedAssignment.getDescription());
        btn_enableEdit.setDisable(true);
        btn_checkSub.setDisable(true);
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
            LoadTables();
            clearInfo();
            tf_subStudentID.clear();
            tf_subCourseID.clear();
            SoundPlayer player = new SoundPlayer("/gread.wav");
            player.play();
        } else {
            lbl_info.setText("No se pudo matricular al estudiante.");
            SoundPlayer player = new SoundPlayer("/error.wav");
            player.play();
        }
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
            LoadTables();
            tf_subEditStudentID.clear();
            tf_subEditCourseID.clear();
            SoundPlayer player = new SoundPlayer("/edit.wav");
            player.play();
        } else {
            lbl_info.setText("Error al actualizar la matricula");
            SoundPlayer player = new SoundPlayer("/error.wav");
            player.play();
        }
        clearInfo();
    }

    @FXML
    private void DeleteRegistration(ActionEvent event) {
        if (RemoteConnection.GetInstance().deleteRegistration(selectedRegistration.getId())) {
            lbl_info.setText("Matricula eliminada correctamente.");
            LoadTables();
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
    private void GetCourseDataToSuscribe(MouseEvent event) {
        try {
            selectedCourse = coursesToSubscribe_tv.getSelectionModel().getSelectedItem();
            tf_subCourseID.setText(selectedCourse.getCourseId().toString());
        } catch (Exception e) {
            System.out.println("ERROR \"GetCourseData\": " + e);
        }
    }

    @FXML
    private void GetAssignmentData(MouseEvent event) {
        selectedAssignment = Assignment_tv.getSelectionModel().getSelectedItem();

        if (selectedAssignment != null) {
            System.out.println("Asignacion seleccionada: " + selectedAssignment.getAssigID());
            btn_deleteAssig.setDisable(false);
            btn_enableEdit.setDisable(false);
            btn_checkSub.setDisable(false);
        } else {
            System.out.println("No se ha seleccionado ninguna asignacion.");
        }
    }

    @FXML
    private void FilterData(ActionEvent event) {
        filter(cbx_AssigID.getValue());
    }

    @FXML
    private void CheckSubmissions(ActionEvent event) {
        cbx_AssigID.setValue(selectedAssignment.getAssigID());
        filter(cbx_AssigID.getValue());
        Professors_tp.getSelectionModel().select(3);
        btn_deleteAssig.setDisable(true);
        btn_enableEdit.setDisable(true);
        btn_checkSub.setDisable(true);

    }

    private void filter(Integer AssigID) {
        tab_SubmissionID.setCellValueFactory(new PropertyValueFactory<>("submissionID"));
        tab_SubAssigID.setCellValueFactory(new PropertyValueFactory<>("assignmentID"));
        tab_score.setCellValueFactory(new PropertyValueFactory<>("submissionScore"));
        tab_sendDate.setCellValueFactory(new PropertyValueFactory<>("submissionDate"));
        tab_fileRoute.setCellValueFactory(new PropertyValueFactory<>("submissionFile"));
        tab_iaAnswere.setCellValueFactory(new PropertyValueFactory<>("commentAI"));
        tab_ProfAnswere.setCellValueFactory(new PropertyValueFactory<>("commentProfessor"));
        tab_status.setCellValueFactory(new PropertyValueFactory<>("submissionStatus"));
        tab_SubStudentID.setCellValueFactory(new PropertyValueFactory<>("studentID"));

        ObservableList<Submission> submissionObservableList = FXCollections.observableArrayList();
        RemoteConnection.GetInstance().getSubmissions();
        SubmissionsList = RemoteConnection.GetInstance().getSubmissionsList();

        if (SubmissionsList != null) {
            for (int i = 0; i < SubmissionsList.length(); i++) {
                try {
                    JSONObject submissionJson = SubmissionsList.getJSONObject(i);
                    if (submissionJson.getInt("assignment_id") == AssigID) {
                        Submission sub = new Submission(
                                submissionJson.optInt("submission_id", -1),
                                (float) submissionJson.optDouble("submission_score", 0.0f),
                                submissionJson.optInt("assignment_id", -1),
                                submissionJson.optInt("student_id", -1),
                                submissionJson.optString("submission_date", "Null"),
                                submissionJson.optString("submission_file", "Null"),
                                submissionJson.optString("submission_status", "Null"),
                                submissionJson.optString("comment_ai", "None"),
                                submissionJson.optString("comment_professor", "Null")
                        );
                        submissionObservableList.add(sub);
                    }

                } catch (JSONException e) {
                    System.out.println("Error al procesar JSON: " + e.getMessage());
                }
            }
            Submission_tv.setItems(submissionObservableList);
        } else {
            System.out.println("La lista de Entregas esta vacia o hubo un error.");
        }
    }

    @FXML
    private void GetSubmissionData(MouseEvent event) {
        selectedSubmission = Submission_tv.getSelectionModel().getSelectedItem();
        tf_submissionID.setText(Integer.toString(selectedSubmission.getSubmissionID()));
        tf_submissionURL.setText(selectedSubmission.getSubmissionFile());
        if (selectedSubmission.getCommentAI() != null && !"None".equals(selectedSubmission.getCommentAI())) {
            ta_submissionObservation.setText(selectedSubmission.getCommentAI());
        } else {
            ta_submissionObservation.setText(selectedSubmission.getCommentProfessor());
        }
        tf_submissionScore.setText(selectedSubmission.getSubmissionScore().toString());
        btn_saveRating.setDisable(false);
        btn_autoRating.setDisable(false);
    }

    @FXML
    private void SaveRating(ActionEvent event) {
        if (RemoteConnection.GetInstance().updateSubmission(selectedSubmission.getSubmissionID(), Float.parseFloat(tf_submissionScore.getText()), selectedSubmission.getAssignmentID(), selectedSubmission.getStudentID(), selectedSubmission.getSubmissionDate(), selectedSubmission.getSubmissionFile(), "Revisado", "None", ta_submissionObservation.getText())) {
            lbl_info.setText("Calificado");
            filter(cbx_AssigID.getValue());
            tf_submissionScore.clear();
            ta_submissionObservation.clear();
            tf_submissionURL.clear();
            SoundPlayer player = new SoundPlayer("/edit.wav");
            player.play();
        } else {
            lbl_info.setText("Error");
            SoundPlayer player = new SoundPlayer("/error.wav");
            player.play();
        }
        clearInfo();
    }

    @FXML
    private void AutoRating(ActionEvent event) {
        IaScore();
        if (RemoteConnection.GetInstance().updateSubmission(selectedSubmission.getSubmissionID(), Float.parseFloat(tf_submissionScore.getText()), selectedSubmission.getAssignmentID(), selectedSubmission.getStudentID(), selectedSubmission.getSubmissionDate(), selectedSubmission.getSubmissionFile(), "Revisado", ta_submissionObservation.getText(), "Null")) {
            lbl_info.setText("Calificado");
            filter(cbx_AssigID.getValue());
            btn_saveRating.setDisable(true);
            tf_submissionScore.clear();
            ta_submissionObservation.clear();
            tf_submissionURL.clear();
            SoundPlayer player = new SoundPlayer("/edit.wav");
            player.play();

        } else {
            lbl_info.setText("Error");
            SoundPlayer player = new SoundPlayer("/error.wav");
            player.play();
        }
        clearInfo();
    }

    private void IaScore() {
        RemoteConnection.GetInstance().getAnswers();
        JSONArray answerList = RemoteConnection.GetInstance().getAnswersList();
        if (answerList != null && !answerList.isEmpty()) {
            Random random = new Random();
            int randomIndex = random.nextInt(answerList.length());
            JSONObject selectedAnswer = answerList.getJSONObject(randomIndex);
            ta_submissionObservation.setText(selectedAnswer.getString("answer"));
            Score(ta_submissionObservation.getText());
        } else {
            lbl_info.setText("No hay respuestas en la base de datos");
        }
        clearInfo();
    }

    private void Score(String observation) {

        List<String> Good = Arrays.asList(
                "excelente", "correcto", "bien", "muy bien", "fantástico", "perfecto", "sobresaliente",
                "impecable", "genial", "maravilloso", "exitoso", "notable", "increíble", "brillante",
                "superior", "positivo", "reconocido", "recomendable", "eficaz", "asombroso", "sobresale",
                "destacado", "entendido", "acertado", "competente", "habilidoso", "es perfecto"
        );

        List<String> Bad = Arrays.asList(
                "incorrecto", "malo", "necesita mejorar", "fallo", "deficiente", "insuficiente",
                "error", "incompleto", "desaprobado", "pobre", "perdido", "horrible", "inadecuado",
                "desorganizado", "caótico", "incorrecto", "reprobado", "defecto", "negligente",
                "subpar", "equivocado", "limitado", "fracasado", "decepcionante", "no alcanzado",
                "triste", "insatisfactorio", "inútil", "mal"
        );

        List<String> Regular = Arrays.asList(
                "regular", "aceptable", "promedio", "normal", "satisfactorio", "adecuado",
                "pasable", "mediocre", "ordinario", "común", "estándar", "necesita mejorar",
                "bajo", "intermedio", "equilibrado", "no está mal", "más o menos", "funcional",
                "razonable", "promedial", "tolerable", "vago", "pasable", "entendible", "simple"
        );
        Integer realScore = 60;
        String AnswerLower = observation.toLowerCase();
        for (String palabra : Good) {
            if (AnswerLower.contains(palabra)) {
                if (palabra.equals("perfecto") || palabra.equals("asombroso")) {
                    realScore += 30;
                } else {
                    realScore += 10;
                }
            }
        }

        for (String palabra : Bad) {
            if (AnswerLower.contains(palabra)) {
                realScore -= 15;
            }
        }

        for (String palabra : Regular) {
            if (AnswerLower.contains(palabra)) {
                realScore += 7;
            }
        }
        if (realScore > 100) {
            realScore = 100;
        } else if (realScore < 0) {
            realScore = 0;
        }
        tf_submissionScore.setText(realScore.toString());
    }
}
