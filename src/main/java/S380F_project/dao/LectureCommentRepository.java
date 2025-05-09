package S380F_project.dao;

import S380F_project.model.LectureComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LectureCommentRepository extends JpaRepository<LectureComment, Long> {
    LectureComment findLectureCommentBylcId(long lcId);
    List<LectureComment> findCommentByLecture(long lecture);
    List<LectureComment> findAllByUser(long userId);
}
