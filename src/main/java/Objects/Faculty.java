/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objects;

/**
 *
 * @author Makin Artavia
 */
public class Faculty {
    private Integer id;
    private String name;
    private String description;
    private Integer UniverID;
    
    public Faculty(){
        this.id = null;
        this.name = null;
        this.description = null;
        this.UniverID = null;
    }

    public Faculty(Integer id, String name, String description, Integer UniverID) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.UniverID = UniverID;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getUniverID() {
        return UniverID;
    }
}
