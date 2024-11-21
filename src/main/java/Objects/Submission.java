/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objects;

/**
 *
 * @author Makin Artavia
 */
public class Submission {

    private int submissionID;
    private Float submissionScore;
    private int assignmentID;
    private int studentID;
    private String submissionDate;
    private String submissionFile;
    private String submissionStatus;
    private String commentAI;
    private String commentProfessor;

    public Submission(int submissionID, Float submissionScore, int assignmentID, int studentID, String submissionDate, String submissionFile, String submissionStatus, String commentAI, String commentProfessor) {
        this.submissionID = submissionID;
        this.submissionScore = submissionScore;
        this.assignmentID = assignmentID;
        this.studentID = studentID;
        this.submissionDate = submissionDate;
        this.submissionFile = submissionFile;
        this.submissionStatus = submissionStatus;
        this.commentAI = commentAI;
        this.commentProfessor = commentProfessor;
    }

    public Submission() {
        this.submissionID = 0;
        this.submissionScore = null;
        this.assignmentID = 0;
        this.studentID = 0;
        this.submissionDate = null;
        this.submissionFile = null;
        this.submissionStatus = null;
        this.commentAI = null;
        this.commentProfessor = null;
    }

    public int getSubmissionID() {
        return submissionID;
    }

    public Float getSubmissionScore() {
        return submissionScore;
    }

    public int getAssignmentID() {
        return assignmentID;
    }

    public int getStudentID() {
        return studentID;
    }

    public String getSubmissionDate() {
        return submissionDate;
    }

    public String getSubmissionFile() {
        return submissionFile;
    }

    public String getSubmissionStatus() {
        return submissionStatus;
    }

    public String getCommentAI() {
        return commentAI;
    }

    public String getCommentProfessor() {
        return commentProfessor;
    }
}
