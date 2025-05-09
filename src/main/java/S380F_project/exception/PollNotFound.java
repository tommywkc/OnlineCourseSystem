package S380F_project.exception;

public class PollNotFound extends RuntimeException {
    public PollNotFound(long pollId) {
        super("poll" + pollId + " not found");
    }
}
