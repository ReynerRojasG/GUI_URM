/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objects;

/**
 *
 * @author Makin Artavia
 */
public class Course {
    private Integer courseId;
    private Integer professorId;
    private Integer departmentId;
    private String courseName;
    private String courseInformation;

    public Course() {
        this.courseId = null;
        this.professorId = null;
        this.departmentId = null;
        this.courseName = null;
        this.courseInformation = null;
    }

    public Course(Integer courseId, Integer professorId, Integer departmentId, String courseName, String courseInformation) {
        this.courseId = courseId;
        this.professorId = professorId;
        this.departmentId = departmentId;
        this.courseName = courseName;
        this.courseInformation = courseInformation;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public Integer getProfessorId() {
        return professorId;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseInformation() {
        return courseInformation;
    }
}
