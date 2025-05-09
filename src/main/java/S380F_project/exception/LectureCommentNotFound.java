package S380F_project.exception;

public class LectureCommentNotFound extends RuntimeException {
    public LectureCommentNotFound(long commentId) {
        super("LectureCommentNotFound: " + commentId);
    }
}
