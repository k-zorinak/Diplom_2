package actions;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;

@Getter
@Setter
public class GenerateUserData {
    private String userEmail;
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
