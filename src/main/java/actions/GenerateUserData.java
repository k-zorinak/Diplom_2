package actions;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;

@Getter
@Setter
public class GenerateUserData {
    private String userEmail;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private String userPassword;
    private String userName;

    private String generateRandomString() {
        return RandomStringUtils.randomAlphabetic(10);
    }

    public void generateEmailPassName() {
        userEmail = generateRandomString() + "@mail.com";
        userPassword = generateRandomString();
        userName = generateRandomString();
    }

}
