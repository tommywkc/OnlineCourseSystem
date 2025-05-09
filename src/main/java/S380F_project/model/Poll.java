package S380F_project.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "POLL")
public class Poll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POLLID", nullable = false, unique = true)
    private long pollId;

    @Column(name = "POLLNAME", nullable = false)
    private String question;

    @Lob
    @Column(name = "POLLANSWER")
    private List<String> pollAnswer;

    @OneToMany(mappedBy = "poll", cascade = CascadeType.ALL, orphanRemoval = true) // Enable cascading delete
    private List<PollAnswer> pollAnswers = new ArrayList<>();

    public Poll() {}

    public Poll(String question, List<String> pollAnswer) {
        this.question = question;
        this.pollAnswer = pollAnswer;
    }

    // Getters and setters
    public long getPollId() {return pollId;}

    public void setPollId(long pollId) {this.pollId = pollId;}

    public String getQuestion() {return question;}

    public void setQuestion(String question) {this.question = question;}

    public List<String> getPollAnswer() {return pollAnswer;}

    public void setPollAnswer(List<String> pollAnswer) {this.pollAnswer = pollAnswer;}

    public List<PollAnswer> getPollAnswers() {return pollAnswers;}

    public void setPollAnswers(List<PollAnswer> pollAnswers) {this.pollAnswers = pollAnswers;}
}