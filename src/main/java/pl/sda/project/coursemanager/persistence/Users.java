package pl.sda.project.coursemanager.persistence;


import org.springframework.data.annotation.Id;



public class Users {
    @Id

    private Long id;

    private String name;
    private String lastName;
    private String type;
    private String status;
    private String login;
    private String password;

    public Users (){}

    public Users(Long id, String name, String lastName, String type, String status, String login, String password) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.type = type;
        this.status = status;
        this.login = login;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
