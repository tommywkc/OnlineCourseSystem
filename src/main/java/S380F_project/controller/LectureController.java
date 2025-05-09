package S380F_project.controller;

import S380F_project.dao.LectureService;
import S380F_project.dao.UserService;
import S380F_project.exception.LectureNoteNotFound;
import S380F_project.model.Attachment;
import S380F_project.model.Users;
import S380F_project.view.DownloadingView;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/lecture")
public class LectureController {

    @Resource
    private LectureService lectureService;

    @Resource
    private UserService userService;

    //render add note page
    @GetMapping("/createNote/{id}")
    public ModelAndView createLectureNote(@PathVariable("id") Long id) { return new ModelAndView("addNote","noteForm", new Form());}

    public static class Form {
        private String noteName;
        private List<MultipartFile> attachments;

        public String getNoteName() {
            return noteName;
        }

        public void setNoteName(String noteName) {
            this.noteName = noteName;
        }

        public List<MultipartFile> getAttachments() {
            return attachments;
        }

        public void setAttachments(List<MultipartFile> attachments) {
            this.attachments = attachments;
        }

    }


    //render lecture page's notes and comments
    @GetMapping({"/{id}"})
    public String lecture(@PathVariable("id") Long id, Model model) {
        model.addAttribute("lectureName", lectureService.findLectureById(id).getLectureName());
        model.addAttribute("lectureId", id);
        model.addAttribute("notelist", lectureService.findNoteByLectureId(id));
        model.addAttribute("commentlist", lectureService.findCommentByLectureId(id));
        return "lecture";
    }

    @PostMapping("/createNote/{id}")
    public String createLectureNote(@PathVariable("id") Long id, LectureController.Form form) throws IOException {
        long noteId = lectureService.createLectureNote(form.getNoteName(), form.getAttachments(), id);
        return "redirect:/lecture/" + id;
    }

    @GetMapping("/{lectureId}/note/delete/{noteId}")
    public String deleteLectureNoteById(@PathVariable long lectureId, @PathVariable long noteId) throws LectureNoteNotFound {
        lectureService.deleteLectureNoteById(noteId);
        return "redirect:/lecture/" + lectureId;
    }

    //render lectureNote page's notes and attachments
    @GetMapping("/{lectureId}/note/{noteId}")
    public String lectureNote(@PathVariable("lectureId") long lectureId, @PathVariable long noteId, Model model) throws LectureNoteNotFound {
        model.addAttribute("lectureId", lectureId);
        model.addAttribute("lectureName", lectureService.findLectureById(lectureId).getLectureName());
        model.addAttribute("noteId", noteId);
        model.addAttribute("note", lectureService.findLectureNoteById(noteId));
        return "lectureNote";
    }

    //delete and download attachment
    @GetMapping("/{lectureId}/note/{noteId}/deleteAttachments/{attachmentId:.+}")
    public String deleteAttachmentById(@PathVariable("lectureId") long lectureId,@PathVariable("noteId") long noteId, @PathVariable("attachmentId") long attachmentId) throws LectureNoteNotFound {
        lectureService.deleteAttachment(noteId, attachmentId);
        return "redirect:/lecture/{lectureId}/note/{noteId}";
    }

    @GetMapping("/{lectureId}/note/{noteId}/downloadAttachments/{attachmentId:.+}")
    public View downloadAttachmentById(@PathVariable("lectureId") long lectureId,@PathVariable("noteId") long noteId, @PathVariable("attachmentId") long attachmentId) throws LectureNoteNotFound {
        Attachment attachment = lectureService.getAttachment(noteId, attachmentId);
        return new DownloadingView(attachment.getFilename(),attachment.getMimeContentType(), attachment.getContents());
    }


    //create and delete lecture in index
    @GetMapping("/create")
    public String createLecture(@RequestParam("lectureName") String lectureName) throws IOException {
        lectureService.createLecture(lectureName);
        return "redirect:/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteLecture(@PathVariable("id") Long id) {
        lectureService.deleteLectureById(id);
        return "redirect:/index";
    }


    //create and delete comment
    @GetMapping("/{lectureId}/addComment")
    public String createLectureComment(@PathVariable long lectureId,@RequestParam("comment") String commentText, Principal principal) throws IOException {
        String username=principal.getName();
        Users users = userService.getUser(username) ;
        lectureService.createLectureComment(lectureId, users.getUserId(), commentText, username);
        return "redirect:/lecture/" + lectureId;
    }

    @GetMapping("/{lectureId}/deleteComment/{commentId}")
    public String deleteLectureComment(@PathVariable("lectureId") long lectureId,@PathVariable("commentId") long commentId) throws IOException {
        lectureService.deleteLectureCommentById(commentId);
        return "redirect:/lecture/" + lectureId;
    }



}


