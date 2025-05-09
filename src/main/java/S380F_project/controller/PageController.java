package S380F_project.controller;

import S380F_project.dao.LectureService;
import S380F_project.dao.PollService;
import S380F_project.dao.UserService;
import S380F_project.model.LectureComment;
import S380F_project.model.PollAnswer;
import S380F_project.model.PollComment;
import S380F_project.model.Users;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PageController {

    @Resource
    private LectureService lectureService;

    @Resource
    private PollService pollService;

    @Resource
    private UserService userService;

    @GetMapping({"","/","/index"})
    public String index(Model model) {
        model.addAttribute("lectureDatabase", lectureService.findAllLecture());
        model.addAttribute("pollDatabase", pollService.getAllPolls());
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }


    //commentHistory
    @GetMapping("/commentHistory")
    public String commentHistory(Principal principal, Model model) throws IOException {
        String username=principal.getName();
        Users users = userService.getUser(username) ;
        long userId = users.getUserId();
        List<LectureComment> lectureComments = (lectureService.findLectureCommentByUser(userId));
        List<List<String>> lectures = new ArrayList<List<String>>();
        for (LectureComment comment : lectureComments) {
            List<String> temp = new ArrayList<String>();
            long lectureId = comment.getLecture();
            String lectureName = lectureService.findLectureById(lectureId).getLectureName();
            String commentTime = comment.getCommentTime().toString();
            temp.add(lectureName);
            temp.add(comment.getComment());
            temp.add(commentTime);
            lectures.add(temp);
        }
        model.addAttribute("lectures", lectures);
        model.addAttribute("username", username);
        List<PollComment> pollComments = (pollService.getAllPollCommentsByID(userId));
        List<List<String>> polls = new ArrayList<List<String>>();
        for (PollComment comment : pollComments) {
            List<String> temp = new ArrayList();
            long pollId = comment.getPoll();
            String pollName = pollService.getPoll(pollId).getQuestion();
            String commentTime = comment.getCommentTime().toString();
            temp.add(pollName);
            temp.add(comment.getComment());
            temp.add(commentTime);
            polls.add(temp);
        }
        model.addAttribute("polls", polls);
        return "commentHistory";
    }

    @GetMapping("/pollHistory")
    public String pollHistory(Principal principal, Model model) {
        String username=principal.getName();
        Users users = userService.getUser(username);
        long userId = users.getUserId();
        List<PollAnswer> pollAnswers = pollService.getPollAnswersByUser(userId);
        List<List<String>> polls = new ArrayList<List<String>>();
        for (PollAnswer answer : pollAnswers) {
            List<String> temp = new ArrayList();
            long pollId = answer.getPoll();
            String pollName = pollService.getPoll(pollId).getQuestion();
            temp.add(pollName);
            temp.add(answer.getAnswer());
            polls.add(temp);
        }
        model.addAttribute("polls", polls);
        model.addAttribute("username", username);
        return "pollHistory";
    }



}