package S380F_project.exception;

public class PollAnswerSizeError extends RuntimeException {
    public PollAnswerSizeError() {
        super("Answer size not equal to four");
    }
}
