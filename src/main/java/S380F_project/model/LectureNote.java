package S380F_project.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "LECTURE_NOTE")
public class LectureNote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NOTEID", nullable = false, unique = true)
    private long noteId;

    @Column(name = "NOTE_NAME", nullable = false)
    private String noteName;


    // Relationships
    @Column(name = "LECTUREID", nullable = false)
    private long lecture;

    public LectureNote() {}

    public LectureNote(String noteName, long lecture) {
        this.noteName = noteName;
        this.lecture = lecture;
    }
    @OneToMany(mappedBy = "note", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(FetchMode.SUBSELECT)
    private List<Attachment> attachments = new ArrayList<>();
    // Getters and Setters

    public long getNoteId() {
        return noteId;
    }

    public void setNoteId(long noteId) {
        this.noteId = noteId;
    }

    public String getNoteName() {
        return noteName;
    }

    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }


    public long getLecture() {
        return lecture;
    }

    public void setLecture(long lecture) {
        this.lecture = lecture;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public void deleteAttachment(Attachment attachment) {
        attachment.setNote(null);
        this.attachments.remove(attachment);
    }

}

