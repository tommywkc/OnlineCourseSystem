package S380F_project.model;

import jakarta.persistence.*;

@Entity
@Table(name = "POLL_ANSWER")
public class PollAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ANSWERID", nullable = false, unique = true, insertable=false, updatable=false)
    private long answerId;

    @Column(name = "ANSWER")
    private String  answer;

    // Relationships
    @Column(name = "USERID", nullable = false)
    private long user;

    @Column(name = "POLLID", nullable = false)
    private long poll;

    public PollAnswer() {}

    public PollAnswer(String answer, long poll, long user) {
        this.answer = answer;
        this.poll = poll;
        this.user = user;
    }
    // Getters and Setters

    public long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(long answerId) {
        this.answerId = answerId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public long getUser() {
        return user;
    }

    public void setUser(long user) {
        this.user = user;
    }

    public long getPoll() {
        return poll;
    }

    public void setPoll(long poll) {
        this.poll = poll;
    }
}
