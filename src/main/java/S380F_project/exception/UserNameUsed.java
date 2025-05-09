package S380F_project.exception;

public class UserNameUsed extends RuntimeException {
    public UserNameUsed(String userName) {
        super("userName used: " + userName);
    }
}
