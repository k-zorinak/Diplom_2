package resources;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UserCard {
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String email;
    private String password;
    private String name;

    public UserCard(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public UserCard(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public UserCard() {
    }
}
