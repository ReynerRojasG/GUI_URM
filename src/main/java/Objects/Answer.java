/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objects;

/**
 *
 * @author Makin Artavia
 */
public class Answer {
    private Integer id;
    private String answere;
    
    public Answer(){
        this.id = null;
        this.answere = null;
    }

    public Answer(Integer id, String answere) {
        this.id = id;
        this.answere = answere;
    }

    public Integer getId() {
        return id;
    }

    public String getAnswere() {
        return answere;
    }
}
