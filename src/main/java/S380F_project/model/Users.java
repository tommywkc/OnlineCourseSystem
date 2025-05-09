package S380F_project.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "USERS")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USERID")
    private long userId;

    @Column(name = "LOGINPASSWORD", nullable = false)
    private String loginPassword;

    @Column(name = "USERNAME", nullable = false)
    private String username;

    @Column(name = "USERROLE", nullable = false)
    private String userRole;

    @Column(name = "PHONE", nullable = false)
    private String phone;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "FULLNAME", nullable = false)
    private String fullName;

    // Relationships
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<LectureComment> lectureComments;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PollComment> pollComments;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PollAnswer> pollAnswers;

    public  Users() {}

    public Users( String username, String loginPassword,  String phone, String email,String userRole, String fullName) {
        this.username = username;
        this.loginPassword = loginPassword;
        this.phone = phone;
        this.email = email;
        this.userRole = userRole;
        this.fullName = fullName;
    }
    // Getters and Setters

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<LectureComment> getLectureComments() {
        return lectureComments;
    }

    public void setLectureComments(Set<LectureComment> lectureComments) {
        this.lectureComments = lectureComments;
    }

    public Set<PollComment> getPollComments() {
        return pollComments;
    }

    public void setPollComments(Set<PollComment> pollComments) {
        this.pollComments = pollComments;
    }

    public Set<PollAnswer> getPollAnswers() {
        return pollAnswers;
    }

    public void setPollAnswers(Set<PollAnswer> pollAnswers) {
        this.pollAnswers = pollAnswers;
    }
}