package S380F_project.exception;

public class UserNotFound extends RuntimeException {
    public UserNotFound(String userId) {
        super("user" + userId + " not found");
    }
}
