package S380F_project.model;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "POLL_COMMENT")
public class PollComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PCID", nullable = false, unique = true)
    private long pcId;

    @Column(name = "COMMENT", nullable = false)
    private String comment;

    // Relationships
    @Column(name = "USERID", nullable = false)
    private long user;

    @Column(name = "POLLID", nullable = false)
    private long poll;

    @Column(name = "USERNAME", nullable = false)
    private String username;


    @Column(name = "COMMENTTIME", nullable = false, updatable = false)
    private Timestamp commentTime;

    public PollComment() {}

    public PollComment(String comment, long user, long poll, String username) {
        this.comment = comment;
        this.user = user;
        this.poll = poll;
        this.username = username;
        this.commentTime = new Timestamp(System.currentTimeMillis());
    }
    // Getters and Setters

    public long getPcId() {
        return pcId;
    }

    public void setPcId(long pcId) {
        this.pcId = pcId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    public String getUsername() {return username;}

    public void setUsername(String username) {this.username = username;}

    public Timestamp getCommentTime() {return commentTime;}

    public void setCommentTime(Timestamp commentTime) {this.commentTime = commentTime;}

}