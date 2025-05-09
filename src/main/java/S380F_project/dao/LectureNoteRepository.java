package S380F_project.dao;

import S380F_project.model.LectureNote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LectureNoteRepository extends JpaRepository<LectureNote, Long> {

    LectureNote findLectureNoteByNoteId(long noteId);

    List<LectureNote> findLectureNotesByLecture(long lectureId);
}
