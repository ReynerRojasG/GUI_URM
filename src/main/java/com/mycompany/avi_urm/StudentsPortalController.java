/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.avi_urm;

import Objects.Assignment;
import Objects.Course;
import Objects.Submission;
import Player.SoundPlayer;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author Makin Artavia
 */
public class StudentsPortalController implements Initializable {

    private JSONArray registrationsList = new JSONArray();
    private JSONArray coursesList = new JSONArray();
    private JSONArray usersList = new JSONArray();
    private JSONArray assignmentsList = new JSONArray();
    private JSONArray submissionsList = new JSONArray();
    private Assignment selectedAssignment;
    private Submission selectedSubmission;
    @FXML
    private Label lbl_info;
    @FXML
    private Label lbl_minimize;
    @FXML
    private Label lbl_exit;
    @FXML
    private TableView<Course> courses_tv;
    @FXML
    private TableColumn<?, ?> tab_courseName;
    @FXML
    private TableColumn<?, ?> tab_courseInfo;
    @FXML
    private Button btn_AddAssig;
    @FXML
    private TableView<Assignment> Assignment_tv;
    @FXML
    private TableColumn<Course, String> tab_courseProfessor;
    @FXML
    private TableColumn<Assignment, Integer> tab_assignmentID;
    @FXML
    private TableColumn<Assignment, String> tab_assignmentDescription;
    @FXML
    private TableColumn<Assignment, String> tab_assignmentSDate;
    @FXML
    private TableColumn<Assignment, String> tab_assignmentFDate;
    @FXML
    private TableColumn<?, ?> tab_assignmentCourse;
    @FXML
    private TextField tf_fileName;
    @FXML
    private TextField tf_dateSubmission;
    @FXML
    private TableColumn<Submission, Integer> tab_submissionID;
    @FXML
    private TableColumn<Submission, Float> tab_submissionScore;
    @FXML
    private TableColumn<Submission, String> tab_submissionDate;
    @FXML
    private TableColumn<Submission, String> tab_submissionFile;
    @FXML
    private TableColumn<Submission, String> tab_submissionCommentProf;
    @FXML
    private TableColumn<Submission, String> tab_submissionStatus;
    @FXML
    private TableView<Submission> Submission_tv;
    @FXML
    private TextField tf_DateEdit;
    @FXML
    private Button btn_editSubmission;
    @FXML
    private TextField tf_fileNameEdit;
    @FXML
    private Button btn_deleteSubmission1;
    @FXML
    private AnchorPane editSubmission_ap;
    @FXML
    private TextField tf_assigId;
    @FXML
    private TableColumn<?, ?> tab_subAssignmentID;
    @FXML
    private TableColumn<?, ?> tab_courseID;
    @FXML
    private Label lbl_typeOfID;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        LoadTables();
        SetFormatForDates(tf_dateSubmission);
        SetFormatForDates(tf_DateEdit);
    }

    private void LoadTables() {
        //////////////////////////////Course Table//////////////////////////////
        tab_courseName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        tab_courseInfo.setCellValueFactory(new PropertyValueFactory<>("courseInformation"));
        tab_courseID.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        tab_courseProfessor.setCellValueFactory(cellData -> {
            Integer professorId = cellData.getValue().getProfessorId();
            String professorName = getProfessorName(professorId);
            return new SimpleStringProperty(professorName);
        });

        RemoteConnection.GetInstance().getCourses();
        RemoteConnection.GetInstance().getRegistrations();
        RemoteConnection.GetInstance().getUsers();

        coursesList = RemoteConnection.GetInstance().getCoursesList();
        registrationsList = RemoteConnection.GetInstance().getRegistrationsList();
        usersList = RemoteConnection.GetInstance().getUsersList();

        ObservableList<Course> courseObservableList = FXCollections.observableArrayList();

        if (registrationsList != null) {
            for (int i = 0; i < registrationsList.length(); i++) {
                try {
                    JSONObject registrationJson = registrationsList.getJSONObject(i);
                    int studentId = registrationJson.getInt("student_id");
                    int courseId = registrationJson.getInt("course_id");

                    if (studentId == App.getUser().getUserID()) {
                        for (int j = 0; j < coursesList.length(); j++) {
                            JSONObject courseJson = coursesList.getJSONObject(j);

                            if (courseJson.getInt("course_id") == courseId) {
                                Course course = new Course(
                                        courseJson.getInt("course_id"),
                                        courseJson.getInt("professor_id"),
                                        courseJson.getInt("department_id"),
                                        courseJson.getString("course_name"),
                                        courseJson.getString("course_information")
                                );
                                courseObservableList.add(course);
                            }
                        }
                    }
                } catch (JSONException e) {
                    System.out.println("Error al procesar JSON: " + e.getMessage());
                }
            }
        } else {
            System.out.println("No hay matriculaciones disponibles.");
        }
        courses_tv.setItems(courseObservableList);

        //////////////////////////////Assingment Table//////////////////////////////
        tab_assignmentDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        tab_assignmentSDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        tab_assignmentFDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        tab_assignmentID.setCellValueFactory(new PropertyValueFactory<>("assigID"));
        tab_assignmentCourse.setCellValueFactory(new PropertyValueFactory<>("courseID"));

        RemoteConnection.GetInstance().getAssignments();
        assignmentsList = RemoteConnection.GetInstance().getAssignmentsList();

        ObservableList<Assignment> assignmentObservableList = FXCollections.observableArrayList();

        if (assignmentsList != null) {
            for (int i = 0; i < assignmentsList.length(); i++) {
                try {
                    JSONObject assignmentJson = assignmentsList.getJSONObject(i);
                    int courseId = assignmentJson.getInt("course_id");

                    for (int j = 0; j < registrationsList.length(); j++) {
                        JSONObject registrationJson = registrationsList.getJSONObject(j);
                        int studentId = registrationJson.getInt("student_id");

                        if (studentId == App.getUser().getUserID() && registrationJson.getInt("course_id") == courseId) {
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
                            break;
                        }
                    }
                } catch (JSONException e) {
                    System.out.println("Error al procesar JSON de asignación: " + e.getMessage());
                }
            }
        } else {
            System.out.println("No se encontraron asignaciones disponibles.");
        }
        Assignment_tv.setItems(assignmentObservableList);
        //////////////////////////////Submission Table//////////////////////////////
        tab_submissionID.setCellValueFactory(new PropertyValueFactory<>("submissionID"));
        tab_submissionScore.setCellValueFactory(new PropertyValueFactory<>("submissionScore"));
        tab_submissionDate.setCellValueFactory(new PropertyValueFactory<>("submissionDate"));
        tab_submissionFile.setCellValueFactory(new PropertyValueFactory<>("submissionFile"));
        tab_submissionCommentProf.setCellValueFactory(new PropertyValueFactory<>("commentProfessor"));
        tab_submissionStatus.setCellValueFactory(new PropertyValueFactory<>("submissionStatus"));
        tab_subAssignmentID.setCellValueFactory(new PropertyValueFactory<>("assignmentID"));

        RemoteConnection.GetInstance().getSubmissions();
        submissionsList = RemoteConnection.GetInstance().getSubmissionsList();

        ObservableList<Submission> submissionsObservableList = FXCollections.observableArrayList();

        if (submissionsList != null) {
            for (int i = 0; i < submissionsList.length(); i++) {
                try {
                    JSONObject submissionJson = submissionsList.getJSONObject(i);

                    float submissionScore;
                    if (submissionJson.has("submission_score") && !submissionJson.isNull("submission_score")) {
                        submissionScore = (float) submissionJson.getDouble("submission_score");
                    } else {
                        submissionScore = 0.0f;
                    }

                    String commentAi;
                    if (submissionJson.has("comment_ai") && !submissionJson.isNull("comment_ai")) {
                        commentAi = submissionJson.getString("comment_ai");
                    } else {
                        commentAi = "";
                    }

                    String commentProfessor;
                    if (submissionJson.has("comment_professor") && !submissionJson.isNull("comment_professor")) {
                        commentProfessor = submissionJson.getString("comment_professor");
                    } else {
                        commentProfessor = "";
                    }

                    int studentId = submissionJson.getInt("student_id");
                    if (studentId == App.getUser().getUserID()) {
                        Submission submission = new Submission(
                                submissionJson.getInt("submission_id"),
                                submissionScore,
                                submissionJson.getInt("assignment_id"),
                                studentId,
                                submissionJson.getString("submission_date"),
                                submissionJson.getString("submission_file"),
                                submissionJson.getString("submission_status"),
                                commentAi,
                                commentProfessor
                        );
                        submissionsObservableList.add(submission);
                    }
                } catch (JSONException e) {
                    System.out.println("Error al procesar JSON de entrega: " + e.getMessage());
                }
            }
        } else {
            System.out.println("No se encontraron entregas disponibles.");
        }

        Submission_tv.setItems(submissionsObservableList);
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

    private String getProfessorName(int professorId) {
        if (usersList != null) {
            for (int i = 0; i < usersList.length(); i++) {
                try {
                    JSONObject userJson = usersList.getJSONObject(i);
                    if (userJson.getInt("user_id") == professorId) {
                        return userJson.getString("user_name");
                    }
                } catch (JSONException e) {
                    System.out.println("Error al procesar JSON de usuario: " + e.getMessage());
                }
            }
        }
        return "Desconocido";
    }

    private String getCourseName(int courseId) {
        if (coursesList != null) {
            for (int i = 0; i < coursesList.length(); i++) {
                try {
                    JSONObject courseJson = coursesList.getJSONObject(i);
                    if (courseJson.getInt("course_id") == courseId) {
                        return courseJson.getString("course_name");
                    }
                } catch (JSONException e) {
                    System.out.println("Error al procesar JSON de curso: " + e.getMessage());
                }
            }
        }
        return "Desconocido";
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

    @FXML
    private void CreateNewSubmission(ActionEvent event) {
        try {
            if (selectedAssignment != null) {
                int assignmentID = selectedAssignment.getAssigID();
                int student_id = App.getUser().getUserID();
                String submissionDate = tf_dateSubmission.getText();
                String submissionFile = tf_fileName.getText();
                String submissionStatus = "No revisado";

                boolean success = RemoteConnection.GetInstance().addSubmissionStudent(
                        assignmentID, student_id, submissionDate, submissionFile, submissionStatus
                );

                if (success) {
                    lbl_info.setText("Entrega realizada exitosamente");
                    LoadTables();
                    SoundPlayer player = new SoundPlayer("/gread.wav");
                    player.play();
                } else {
                    lbl_info.setText("Error en la entrega");
                    SoundPlayer player = new SoundPlayer("/error.wav");
                    player.play();
                }
            } else {
                lbl_info.setText("Selecciona una asignación");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            SoundPlayer player = new SoundPlayer("/error.wav");
            player.play();
        }
        tf_dateSubmission.clear();
        tf_fileName.clear();
        clearInfo();
    }

    @FXML
    private void GetAssignmentData(MouseEvent event) {
        selectedAssignment = Assignment_tv.getSelectionModel().getSelectedItem();

        if (selectedAssignment != null) {
            tf_assigId.setText(Integer.toString(selectedAssignment.getAssigID()));
            lbl_typeOfID.setText("Asignacion");
        } else {
            lbl_info.setText("Seleccion vacia");
        }
        clearInfo();
    }

    @FXML
    private void GetSubmissionData(MouseEvent event) {
        selectedSubmission = Submission_tv.getSelectionModel().getSelectedItem();

        if (selectedSubmission != null) {
            tf_fileNameEdit.setText(selectedSubmission.getSubmissionFile());
            tf_DateEdit.setText(selectedSubmission.getSubmissionDate());
            editSubmission_ap.setDisable(false);
            tf_assigId.setText(Integer.toString(selectedSubmission.getSubmissionID()));
            lbl_typeOfID.setText("Entrega");
            btn_deleteSubmission1.setDisable(false);
        } else {
            System.out.println("No se ha seleccionado ninguna entrega.");
        }
    }

    @FXML
    private void DeleteSubmission(ActionEvent event) {
        if (selectedSubmission != null) {
            int submissionID = selectedSubmission.getSubmissionID();

            boolean success = RemoteConnection.GetInstance().deleteSubmission(submissionID);

            if (success) {
                System.out.println("Entrega eliminada correctamente.");
                SoundPlayer player = new SoundPlayer("/gread.wav");
                player.play();
                btn_deleteSubmission1.setDisable(true);
                editSubmission_ap.setDisable(true);
                tf_assigId.setText("");
                lbl_typeOfID.setText("N/A");
                tf_DateEdit.clear();
                tf_fileNameEdit.clear();
            } else {
                System.out.println("Error al eliminar la entrega.");
                SoundPlayer player = new SoundPlayer("/error.wav");
                player.play();
            }
        } else {
            lbl_info.setText("Selecciona una entrega");
        }
        LoadTables();
        clearInfo();
    }

    @FXML
    private void EditSubmission(ActionEvent event) {
        if (selectedSubmission != null) {
            try {
                int submissionID = selectedSubmission.getSubmissionID();
                int assignmentID = selectedSubmission.getAssignmentID();
                int studentID = selectedSubmission.getStudentID();
                String currentFile = selectedSubmission.getSubmissionFile();
                String currentDate = selectedSubmission.getSubmissionDate();
                String currentStatus = selectedSubmission.getSubmissionStatus();
                String currentCommentAI = selectedSubmission.getCommentAI();
                String currentCommentProfessor = selectedSubmission.getCommentProfessor();
                float currentScore = selectedSubmission.getSubmissionScore();

                String newFile = tf_fileNameEdit.getText();
                String newDate = tf_DateEdit.getText();
                String submissionFile;
                if (newFile != null && !newFile.isEmpty() && !newFile.equals(currentFile)) {
                    submissionFile = newFile; 
                } else {
                    submissionFile = currentFile; 
                }

                String submissionDate;
                if (newDate != null && !newDate.isEmpty()) {
                    submissionDate = newDate;
                } else {
                    submissionDate = currentDate;
                }

                boolean success = RemoteConnection.GetInstance().updateSubmission(
                        submissionID, currentScore, assignmentID, studentID,
                        submissionDate, submissionFile, currentStatus, currentCommentAI, currentCommentProfessor
                );

                if (success) {
                    lbl_info.setText("Entrega actualizada exitosamente");
                    LoadTables();
                    SoundPlayer player = new SoundPlayer("/edit.wav");
                    player.play();
                    btn_deleteSubmission1.setDisable(true);
                } else {
                    lbl_info.setText("Error al actualizar la entrega");
                    SoundPlayer player = new SoundPlayer("/error.wav");
                    player.play();
                }
            } catch (Exception e) {
                System.out.println("Error al modificar la entrega: " + e.getMessage());
                SoundPlayer player = new SoundPlayer("/error.wav");
                player.play();
            }
        } else {
            lbl_info.setText("Selecciona una entrega para modificar");
        }
        clearInfo();
        tf_fileNameEdit.clear();
        tf_DateEdit.clear();
        editSubmission_ap.setDisable(true);
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
}
