package pl.sda.project.coursemanager;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class SignForm {
    @NotEmpty
    private String name = "";

    @NotEmpty
    private String lastName = "";

    @NotEmpty
    @Size(min = 7, max = 15)
    private String login ="";

    @NotEmpty
    @Size (min = 7, max = 30)
    private String password = "";

    @NotEmpty
    private String type = "USER";

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
