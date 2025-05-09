package S380F_project.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "LECTURE")
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LECTUREID", nullable = false, unique = true)
    private long lectureId;

    @Column(name = "LECTURENAME", nullable = false)
    private String lectureName;


    @OneToMany(mappedBy = "lecture", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LectureNote> lectureNotes;

    @OneToMany(mappedBy = "lecture", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LectureComment> lectureComments;

    public Lecture() {}
    public Lecture(String lectureName) {
        this.lectureName = lectureName;
    }
    // Getters and Setters

    public long getLectureId() {
        return lectureId;
    }

    public void setLectureId(long lectureId) {
        this.lectureId = lectureId;
    }

    public String getLectureName() {
        return lectureName;
    }

    public void setLectureName(String lectureName) {
        this.lectureName = lectureName;
    }

    public List<LectureNote> getLectureNotes() {
        return lectureNotes;
    }

    public void setLectureNotes(List<LectureNote> lectureNotes) {
        this.lectureNotes = lectureNotes;
    }

    public List<LectureComment> getLectureComments() {
        return lectureComments;
    }

    public void setLectureComments(List<LectureComment> lectureComments) {
        this.lectureComments = lectureComments;
    }
}
