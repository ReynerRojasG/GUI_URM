/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objects;

/**
 *
 * @author Makin Artavia
 */
public class Department {
    private Integer departmentId;
    private String departmentName;
    private String departmentInformation;
    private Integer facultyId;

    public Department() {
        this.departmentId = null;
        this.departmentName = null;
        this.departmentInformation = null;
        this.facultyId = null;
    }

    public Department(Integer departmentId, String departmentName, String departmentInformation, Integer facultyId) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.departmentInformation = departmentInformation;
        this.facultyId = facultyId;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public String getDepartmentInformation() {
        return departmentInformation;
    }

    public Integer getFacultyId() {
        return facultyId;
    }
}

