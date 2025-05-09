package S380F_project.model;

import jakarta.persistence.*;

@Entity
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ATTACHMENTID", nullable = false, unique = true)
    private long attachmentId;

    @Column(name = "filename")
    private String filename;

    @Column(name = "content_type")
    private String mimeContentType;

    @Column(name = "content")
    @Basic(fetch = FetchType.LAZY)
    @Lob
    private byte[] contents;

    @Column(name = "NOTEID", insertable=false, updatable=false)
    private long noteId;

    @ManyToOne
    @JoinColumn(name = "NOTEID", nullable = false)
    private LectureNote note;


    public Attachment() {}

    public Attachment(String filename, String mimeContentType, byte[] contents, long noteId) {
        this.filename = filename;
        this.mimeContentType = mimeContentType;
        this.contents = contents;
        this.noteId = noteId;
    }

    public long getAttachmentId() {return attachmentId;}

    public void setAttachmentId(long attachmentId) {this.attachmentId = attachmentId;}

    public String getFilename() {return filename;}

    public void setFilename(String filename) {this.filename = filename;}

    public String getMimeContentType() {return mimeContentType;}

    public void setMimeContentType(String mimeContentType) {this.mimeContentType = mimeContentType;}

    public byte[] getContents() {return contents;}

    public void setContents(byte[] contents) {this.contents = contents;}

    public long getNoteId() {return noteId;}

    public void setNoteId(long noteId) {this.noteId = noteId;}

    public LectureNote getNote() {return note;}

    public void setNote(LectureNote note) {this.note = note;}


}