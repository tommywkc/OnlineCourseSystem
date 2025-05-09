package S380F_project.controller;

import S380F_project.dao.PollRepository;
import S380F_project.dao.PollService;
import S380F_project.dao.UserService;
import S380F_project.exception.PollNotFound;
import S380F_project.model.Poll;
import S380F_project.model.PollAnswer;
import S380F_project.model.Users;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/poll")
public class PollController {

    @Resource
    private PollService pollService;
    @Autowired
    private PollRepository pollRepository;
    @Autowired
    private UserService userService;


    @GetMapping(value = {"", "/list"})
    public String listPolls(ModelMap model) {
        model.addAttribute("pollDatabase", pollService.getAllPolls());
        return "pollList";
    }


    public static class PollForm {
        @NotEmpty(message = "Please enter the question.")
        private String question;
        @NotEmpty(message = "Please enter choice1")
        private String choice1;
        @NotEmpty(message = "Please enter choice2")
        private String choice2;
        @NotEmpty(message = "Please enter choice3")
        private String choice3;
        @NotEmpty(message = "Please enter choice4")
        private String choice4;

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getChoice1() {
            return choice1;
        }

        public void setChoice1(String choice1) {
            this.choice1 = choice1;
        }

        public String getChoice2() {
            return choice2;
        }

        public void setChoice2(String choice2) {
            this.choice2 = choice2;
        }

        public String getChoice3() {
            return choice3;
        }

        public void setChoice3(String choice3) {
            this.choice3 = choice3;
        }

        public String getChoice4() {
            return choice4;
        }

        public void setChoice4(String choice4) {
            this.choice4 = choice4;
        }
    }

    @GetMapping("/create")
    public ModelAndView create() {
        return new ModelAndView("addPoll", "pollAdd", new PollForm());
    }

    @PostMapping("/create")
    public String register(@Valid @ModelAttribute("pollAdd") PollForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "addPoll";
        }
        pollService.CreatePoll(form.getQuestion(), form.getChoice1(), form.getChoice2(), form.getChoice3(), form.getChoice4());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            return "redirect:/index";
        }
        return "redirect:/index";
    }

    @GetMapping("/view/{pollId}")
    public String show(@PathVariable("pollId") long pollId,ModelMap model,Principal principal) throws PollNotFound {
        Poll poll = pollService.getPoll(pollId);
        ArrayList<String> pollAnswers = pollService.getPollAnswers(pollId);
        if(pollAnswers.size() == 1){
            String combinedChoices = pollAnswers.get(0);
            pollAnswers = new ArrayList<>(Arrays.asList(combinedChoices.split(",")));
            for (int i = 0; i < pollAnswers.size(); i++) {
                pollAnswers.set(i, pollAnswers.get(i).trim().replace("[", "").replace("]", ""));
            }
        }
        List<PollAnswer> pollAnswersHistory = pollService.getPollAnswersByPoll(pollId);
        ArrayList<Integer> answerCount = new ArrayList<Integer>();
        int counterA = 0;
        int counterB = 0;
        int counterC = 0;
        int counterD = 0;
        for (PollAnswer pollAnswer : pollAnswersHistory) {
            if (pollAnswer.getAnswer().equals(pollAnswers.get(0))){
                counterA++;
            }else if (pollAnswer.getAnswer().equals(pollAnswers.get(1))){
                counterB++;
            }else if (pollAnswer.getAnswer().equals(pollAnswers.get(2))){
                counterC++;
            }else if (pollAnswer.getAnswer().equals(pollAnswers.get(3))){
                counterD++;
            }
        }
        answerCount.add(counterA);
        answerCount.add(counterB);
        answerCount.add(counterC);
        answerCount.add(counterD);

        model.addAttribute("commentlist", pollService.getAllPollCommentsByPollID(pollId));
        model.addAttribute("pollId", pollId);
        model.addAttribute("poll", poll);
        model.addAttribute("pollAnswers", pollAnswers);
        model.addAttribute("answerCount", answerCount);
        return "poll";
    }

    @GetMapping("/submit/{pollId}/")
    public String submitPollAnswer(@PathVariable("pollId") long pollId, @RequestParam("selectedOption") String selectedOption, Principal principal) throws PollNotFound, IOException {
        Users user = userService.getUser(principal.getName());
        long userId = user.getUserId();
        List<PollAnswer> answers = pollService.getPollAnswersByPoll(pollId);
        long answerHistory = 0;
        for (PollAnswer answer : answers) {
            if (answer.getUser() == userId) {
                answerHistory = answer.getAnswerId();
            }
        }
        if (answerHistory == 0) {
            pollService.CreatePollAnswer(selectedOption, pollId, userId);
        }else {
            pollService.UpdatePollAnswer(answerHistory, selectedOption);
        }
        return "redirect:/poll/view/" + pollId;
    }

    @GetMapping("/delete/{pollId}")
    public String deletePoll(@PathVariable("pollId") long pollID) {
        pollService.deletePoll(pollID);
        return "redirect:/index";
    }

    @GetMapping("{pollId}/addComment")
    public String createPollComment(@PathVariable("pollId") long pollId, @RequestParam("comment") String comment, Principal principal) throws PollNotFound {
        String username = principal.getName();
        Users users = userService.getUser(username) ;
        pollService.CreatePollComment(comment, users.getUserId(), pollId, username);
        return "redirect:/poll/view/" + pollId;
    }

    @GetMapping("{pollId}/deleteComment/{pcId}")
    public String deletePollComment(@PathVariable("pollId") long pollId,@PathVariable("pcId") long commentId) {
        pollService.deletePollComment(commentId);
        return "redirect:/poll/view/" + pollId;
    }


}