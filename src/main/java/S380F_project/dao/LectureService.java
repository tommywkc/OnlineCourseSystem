package S380F_project.dao;


import S380F_project.exception.LectureCommentNotFound;
import S380F_project.exception.LectureNotFound;
import S380F_project.exception.LectureNoteNotFound;
import S380F_project.model.*;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
public class LectureService {

    @Resource
    private LectureRepository lectureRepository;

    @Resource
    private LectureNoteRepository lectureNoteRepository;

    @Resource
    private LectureCommentRepository lectureCommentRepository;

    @Resource
    private AttachmentRepository attachmentRepository;

    @Transactional
    public List<Lecture> findAllLecture() { return lectureRepository.findAll() ; }



    @Transactional
    public Lecture findLectureById(long lectureId) throws LectureNotFound {
        Lecture lecture = lectureRepository.findById(lectureId).orElse(null);
        if (lecture == null) {
            throw new LectureNotFound("Lecture not found");
        }
        return lecture;
    }


    @Transactional
    public List<LectureComment> findUserAllLectureComment(long userId){
        return lectureCommentRepository.findAllByUser(userId);
    }

    @Transactional
    public List<LectureNote> findNoteByLectureId(long lectureId) throws LectureNoteNotFound {
        List<LectureNote> note = lectureNoteRepository.findLectureNotesByLecture(lectureId);
        if (note == null) {
            throw new LectureNoteNotFound(lectureId);
        }
        return note;
    }


    @Transactional
    public LectureNote findLectureNoteById(long noteId) throws LectureNoteNotFound {
        LectureNote note = lectureNoteRepository.findById(noteId).orElse(null);
        if (note == null) {
            throw new LectureNoteNotFound(noteId);
        }
        return note;
    }

    @Transactional
    public Attachment getAttachment(long noteId, long attachmentId)
            throws LectureNoteNotFound{
        LectureNote note = lectureNoteRepository.findById(noteId).orElse(null);
        if (note == null) {
            throw new LectureNoteNotFound(noteId);
        }
        Attachment attachment = attachmentRepository.findById(attachmentId).orElse(null);
        if (attachment == null) {
            throw new LectureNoteNotFound(attachmentId);
        }
        return attachment;
    }

    @Transactional(rollbackFor = LectureNoteNotFound.class)
    public void deleteAttachment(long noteId, long attachmentId) throws LectureNoteNotFound {
        LectureNote note = lectureNoteRepository.findById(noteId).orElse(null);
        if (note == null) {
            throw new LectureNoteNotFound(noteId);
        }
        for (Attachment attachment : note.getAttachments()) {
            if (Objects.equals(attachment.getAttachmentId(), attachmentId)){
                note.deleteAttachment(attachment);
                lectureNoteRepository.save(note);
                return;
            }
        }
        throw new LectureNoteNotFound(attachmentId);
    }


    @Transactional
    public List<LectureComment> findCommentByLectureId(long lectureId) throws LectureCommentNotFound {
        List<LectureComment> comment = lectureCommentRepository.findCommentByLecture(lectureId);
        if (comment == null) {
            //throw new LectureCommentNotFound(lectureId);
        }
        return comment;
    }


    @Transactional
    public List<LectureComment> findLectureCommentByUser(long userId) throws LectureCommentNotFound {
        List<LectureComment> comment = lectureCommentRepository.findAllByUser(userId);
        if (comment == null) {
            //throw new LectureCommentNotFound(lectureId);
        }
        return comment;
    }

    @Transactional
    public void deleteLectureById(long lectureId) throws LectureNotFound {
        Lecture lecture = lectureRepository.findById(lectureId).orElse(null);
        if (lecture == null) {
            throw new LectureNotFound("Lecture not found");
        }
        lectureRepository.delete(lecture);
    }

    @Transactional
    public void deleteLectureNoteById(long noteId) throws LectureNoteNotFound {
        LectureNote note = lectureNoteRepository.findLectureNoteByNoteId(noteId);
        if (note == null) {
            throw new LectureNoteNotFound(noteId);
        }
        lectureNoteRepository.delete(note);
    }




    @Transactional
    public void createLecture(String lectureName) throws IOException {
        lectureRepository.save(new Lecture(lectureName));
    }

    @Transactional
    public long createLectureNote(String noteName, List<MultipartFile> attachments, long lecture) throws IOException {
        LectureNote lectureNote = new LectureNote();
        lectureNote.setLecture(lecture);
        lectureNote.setNoteName(noteName);
        for (MultipartFile filePart : attachments) {
            Attachment attachment = new Attachment();
            attachment.setFilename(filePart.getOriginalFilename());
            attachment.setMimeContentType(filePart.getContentType());
            attachment.setContents(filePart.getBytes());
            attachment.setNote(lectureNote);
            if (attachment.getFilename() != null && attachment.getFilename().length() > 0
                    && attachment.getContents() != null
                    && attachment.getContents().length > 0) {
                lectureNote.getAttachments().add(attachment);
            }
        }
        LectureNote savedNote = lectureNoteRepository.save(lectureNote);
        return savedNote.getNoteId();
    }

    @Transactional
    public void createLectureComment(long lecture, long user, String commentText, String username) throws IOException {
        lectureCommentRepository.save(new LectureComment(commentText,user,lecture, username)) ;
    }

    @Transactional
    public void deleteLectureCommentById(long lcId) throws LectureCommentNotFound {
        LectureComment comment = lectureCommentRepository.findById(lcId).orElse(null);
        if (comment == null) {
            throw new LectureCommentNotFound(lcId);
        }
        lectureCommentRepository.delete(comment);
    }

    @Transactional (rollbackFor = LectureNotFound.class)
    public void editLecture(long lectureId , String lectureName) throws IOException {
        Lecture lecture = lectureRepository.findById(lectureId).orElse(null);
        if (lecture == null) {
            throw new LectureNotFound("Lecture not found");
        }
        lecture.setLectureName(lectureName);
        lectureRepository.save(lecture);
    }

    @Transactional (rollbackFor = LectureNoteNotFound.class)
    public void editLectureNote(long lectureId,String link, String noteName) throws IOException {
        LectureNote note = lectureNoteRepository.findById(lectureId).orElse(null);
        if (note == null) {
            throw new LectureNoteNotFound(lectureId);
        }
        note.setNoteName(noteName);
        //note.setLink(link);
        lectureNoteRepository.save(note);
    }

    @Transactional
    @PostConstruct
    public void createFirstLecture() {
        if (lectureRepository.count() == 0) {
            Lecture lecture = new Lecture("Test Lecture");
            lectureRepository.save(lecture);
        }
    }
}
