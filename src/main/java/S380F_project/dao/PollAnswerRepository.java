package S380F_project.dao;

import S380F_project.model.Poll;
import S380F_project.model.PollAnswer;
import S380F_project.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PollAnswerRepository extends JpaRepository<PollAnswer, Long> {

    PollAnswer findPollAnswerByAnswerId(long answerId);
    PollAnswer findByUserAndPoll(Users users, Poll poll);
    List<PollAnswer> findByPoll(long poll);
    List<PollAnswer> findByUser(long users);
}
