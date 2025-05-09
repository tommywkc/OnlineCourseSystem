package S380F_project.dao;


import S380F_project.model.Poll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PollRepository extends JpaRepository<Poll, Long> {
    Poll findByquestion(String question);

    @Query("SELECT p.pollAnswer FROM Poll p WHERE p.pollId = :pollId")
    List<String> findPollAnswersByPollId(@Param("pollId") Long pollId);


}
