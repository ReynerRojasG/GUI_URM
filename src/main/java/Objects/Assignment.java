/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objects;

/**
 *
 * @author Makin Artavia
 */
public class Assignment {
    private String description;
    private String startDate;
    private String endDate;
    private int assigID;
    private int profID;
    private Integer courseID;
    private String type;

    public Assignment() {
        this.description = null;
        this.startDate = null;
        this.endDate = null;
        this.type = null;
        this.assigID = 0;
        this.profID = 0;
        this.courseID = 0;
    }

    public Assignment(String description, String startDate, String endDate, int assigID, int profID, Integer courseID, String type) {
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.assigID = assigID;
        this.profID = profID;
        this.courseID = courseID;
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public int getAssigID() {
        return assigID;
    }

    public int getProfID() {
        return profID;
    }

    public Integer getCourseID() {
        return courseID;
    }

    public String getType() {
        return type;
    }
    
}