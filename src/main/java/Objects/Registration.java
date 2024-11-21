/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objects;

/**
 *
 * @author Makin Artavia
 */
public class Registration {
    private Integer id;
    private Integer courseId;
    private Integer studentId;

    public Registration() {
        this.id = null;
        this.courseId = null;
        this.studentId = null;
    }

    public Registration(Integer id, Integer courseId, Integer studentId) {
        this.id = id;
        this.courseId = courseId;
        this.studentId = studentId;
    }

    public Integer getId() {
        return id;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public Integer getStudentId() {
        return studentId;
    }
}
