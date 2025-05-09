package S380F_project.exception;

public class LectureNoteNotFound extends RuntimeException {
    public LectureNoteNotFound(long lectureId) {
        super("LectureNoteNotFound: " + lectureId);
    }
}
