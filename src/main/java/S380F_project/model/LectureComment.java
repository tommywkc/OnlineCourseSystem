package S380F_project.model;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "LECTURE_COMMENT")
public class LectureComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LCID", nullable = false, unique = true)
    private long lcId;

    @Column(name = "COMMENT", nullable = false)
    private String comment;

    // Relationships
    @Column(name = "USERID", nullable = false)
    private long user;

    @Column(name = "LECTUREID", nullable = false)
    private long lecture;

    @Column(name = "USERNAME", nullable = false)
    private String username;


    @Column(name = "COMMENTTIME", nullable = false, updatable = false)
    private Timestamp commentTime;

    public LectureComment() {}

    public LectureComment(String comment, long user , long lecture, String username) {
        this.comment = comment;
        this.user = user;
        this.lecture = lecture;
        this.username = username;
        this.commentTime = new Timestamp(System.currentTimeMillis());
    }
    // Getters and Setters

    public long getLcId() {
        return lcId;
    }

    public void setLcId(long lcId) {
        this.lcId = lcId;
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

    public long getLecture() {
        return lecture;
    }

    public void setLecture(long lecture) {
        this.lecture = lecture;
    }

    public String getUsername() {return username;}

    public void setUsername(String username) {this.username = username;}

    public Timestamp getCommentTime() {return commentTime;}

    public void setCommentTime(Timestamp commentTime) {this.commentTime = commentTime;}

}
