package S380F_project.exception;

public class LectureNotFound extends RuntimeException {
    public LectureNotFound(String message) {
        super("LectureNotFound: " + message);
    }
}
