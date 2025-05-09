package S380F_project.dao;

import S380F_project.exception.PollNotFound;
import S380F_project.exception.UserNameUsed;
import S380F_project.model.Poll;
import S380F_project.model.PollAnswer;
import S380F_project.model.PollComment;
import S380F_project.model.Users;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PollService {

    @Resource
    private PollRepository pollRepository;

    @Resource
    private UsersRepository usersRepository;

    @Resource
    private PollAnswerRepository pollAnswerRepository;

    @Resource
    private PollCommentRepository pollCommentRepository;

    @Transactional
    public List<Poll> getAllPolls() { return pollRepository.findAll(); }

    @Transactional
    public Poll getPoll(long pollID) throws PollNotFound {
        Poll poll = pollRepository.findById(pollID).orElse(null);
        if (poll == null) {
            throw new PollNotFound(pollID);
        }
        return poll;
    }



    @Transactional
    public List<PollComment> getAllPollCommentsByID(long userID) {
        return pollCommentRepository.findAllByUser(userID);
    }

    @Transactional
    public List<PollComment> getAllPollCommentsByPollID(long pollId) {
        return pollCommentRepository.findPollCommentBypoll(pollId);
    }


    @Transactional
    public ArrayList<String> getPollAnswers(long pollId) throws PollNotFound {
        List<String> answers = pollRepository.findPollAnswersByPollId(pollId);
        return new ArrayList<>(answers);
    }

    @Transactional
    public List<PollAnswer> getPollAnswersByPoll(long pollId) throws PollNotFound {
        List<PollAnswer> answers= pollAnswerRepository.findByPoll(pollId);
        return answers;
    }

    @Transactional
    public List<PollAnswer> getPollAnswersByUser(long userID) throws PollNotFound {
        List<PollAnswer> answers= pollAnswerRepository.findByUser(userID);
        return answers;
    }

    @Transactional
    public String getPollAnswersByID(Poll poll, Users users) throws PollNotFound {
        PollAnswer pollAnswer = pollAnswerRepository.findByUserAndPoll(users,poll);
        if (pollAnswer == null) {
            return null ;
        }
        return pollAnswer.getAnswer();
    }

    @Transactional
    public void deletePoll(long pollID) throws PollNotFound {
        Poll poll = pollRepository.findById(pollID).orElse(null);
        if (poll == null) {
            throw new PollNotFound(pollID);
        }
        pollRepository.delete(poll);
    }

    @Transactional
    public void deletePollComment(long commentID) throws PollNotFound {
        PollComment pollComment = pollCommentRepository.findById(commentID).orElse(null);
        if (pollComment == null) {
            throw new PollNotFound(commentID);
        }
        pollCommentRepository.delete(pollComment);
    }

    @Transactional
    public String CreatePollComment(String comment, long user, long poll, String username) throws PollNotFound {
        pollCommentRepository.save(new PollComment(comment, user, poll, username));
        return comment;
    }

    @Transactional
    public void CreatePoll(String question, String choice1,  String choice2, String choice3,String choice4) throws UserNameUsed {
        ArrayList<String> choiceList = new ArrayList<String>();
        choiceList.add(choice1);
        choiceList.add(choice2);
        choiceList.add(choice3);
        choiceList.add(choice4);
        Poll poll = new Poll(question, choiceList);
        pollRepository.save(poll);
    }


    @Transactional
    public void CreatePollAnswer(String answer, long poll, long user) {
        pollAnswerRepository.save(new PollAnswer(answer, poll, user));
    }


//    @Transactional(rollbackFor = PollNotFound.class)
//    public void UpdatePoll(Poll poll, Users user,String pollName, ArrayList<String> answers) throws IOException,PollNotFound, PollAnswerSizeError {
//        if(answers.size() != 4){
//            throw new PollAnswerSizeError();
//        }
//        Poll updatedPoll = pollRepository.findById(user.getUserId()).orElse(null);
//        if (updatedPoll == null) {
//            throw new PollNotFound(poll.getPollId());
//        }
//        updatedPoll.setQuestion(pollName);
//        updatedPoll.setPollAnswer(answers);
//        pollRepository.save(updatedPoll);
//    }

    @Transactional
    public void UpdatePollAnswer(long pollAnswerID, String newAnswer) throws IOException,PollNotFound, PollNotFound{
        PollAnswer pollAnswer = pollAnswerRepository.findById(pollAnswerID).orElse(null);
        if (pollAnswer == null) {
            throw new PollNotFound(pollAnswerID);
        }
        pollAnswer.setAnswer(newAnswer);
        pollAnswerRepository.save(pollAnswer);
    }
}

