package S380F_project.dao;

import S380F_project.model.PollComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PollCommentRepository extends JpaRepository<PollComment, Long> {

    PollComment findPollCommentBypcId(long pcId);
    List<PollComment> findPollCommentBypoll(long pollId);
    List<PollComment> findAllByUser(long userID);
}
