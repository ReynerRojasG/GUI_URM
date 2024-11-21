    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objects;

/**
 *
 * @author Makin Artavia
 */
public class User {
    private String pass;
    private String user;
    private String type;
    private String name;
    private Integer Uid;
    private String jwt;
    private String userCed;
    private Integer userID;
    
    public User() {
        this.pass = null;
        this.user = null;
        this.name = null;
        this.type = null;
        this.jwt = null;
        this.Uid = 0;
        this.userCed = null;
        this.userID = null;
    }
    
    public User(String pass, String user, String type, String name, String jwt, Integer Uid, String userCed, Integer userID) {
        this.pass = pass;
        this.user = user;
        this.type = type;
        this.name = name;
        this.jwt = jwt;
        this.Uid = Uid;
        this.userCed = userCed;
        this.userID = userID;
    }

    public String getPass() {
        return pass;
    }

    public String getUser() {
        return user;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getUid() {
        return Uid;
    }

    public String getJwt() {
        return jwt;
    }

    public String getUserCed() {
        return userCed;
    }

    public Integer getUserID() {
        return userID;
    }
}

