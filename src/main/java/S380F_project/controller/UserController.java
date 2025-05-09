package S380F_project.controller;


import S380F_project.dao.UserService;
import S380F_project.exception.UserNotFound;
import S380F_project.model.Users;
import S380F_project.validator.UserValidator;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserValidator userValidator;



    @Resource
    UserService userService;

    @RequestMapping({"","/"})
    public String index(Model model) {
        return "redirect:/index";
    }
    @RequestMapping("/list")
    public String list(Model model, HttpServletRequest request) {
        if (!request.isUserInRole("ROLE_TEACHER")) {
            return "redirect:/index";
        }
        model.addAttribute("userlist", userService.getUsers());
        return "userList";
    }

    public static class Form{
        @NotEmpty(message="Please enter your user name.")
        private String username;

        @NotEmpty(message="Please enter your full name.")
        private String fullname;

        @NotEmpty(message="Please enter your password.")
        private String password;

        @NotEmpty(message="Please enter your Phone Numeber.")
        private String phoneNumber;

        @NotEmpty(message="Please enter your email.")
        private String email;

        @NotEmpty(message="Please select one role.")
        private String role;


        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getFullname() {
            return fullname;
        }

        public void setFullname(String fullname) {
            this.fullname = fullname;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }
    }

    @GetMapping("/register")
    public ModelAndView register() { return new ModelAndView("addUser","userAdd", new Form());}

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("userAdd") Form form, BindingResult bindingResult) {
        userValidator.validate(form, bindingResult);
        if (bindingResult.hasErrors()) {
            return "addUser";
        }
        userService.createUser(form.getUsername(), form.getPassword(), form.getPhoneNumber(), form.getEmail(), form.getRole(), form.getFullname());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            return "redirect:/user/list";
        }
        return "redirect:/login";
    }

    @GetMapping("/update")
    public ModelAndView passUpdate(Model model,Principal principal,HttpServletRequest request) throws UserNotFound {
        Users users = userService.getUser(principal.getName());
        if(users == null && (!request.isUserInRole("ROLE_TEACHER") || !principal.getName().equals(users.getUsername()))) {
            throw new UserNotFound("User not found.");
        }
        ModelAndView modelAndView = new ModelAndView("userUpdate");
        modelAndView.addObject("user", users);

        Form form = new Form();
        form.setUsername(users.getUsername());
        form.setFullname(users.getFullName());
        form.setPhoneNumber(users.getPhone());
        form.setEmail(users.getEmail());
        form.setRole(users.getUserRole());
        modelAndView.addObject("userUpdate", form);
        return modelAndView;

    }


    @PostMapping("/update")
    public String editUpdate( Form form,
                              Principal principal, HttpServletRequest request) throws IOException, UserNotFound {

        Users users = userService.getUser(principal.getName()) ;
        if(users == null ) {
            return "redirect:/index";
        }
        userService.editUser(form.getUsername(), form.getPassword(), form.getPhoneNumber(), form.getEmail(), form.getRole(), form.getFullname());
        return "redirect:/index";
    }

    @GetMapping("/edit/{username}")
    public ModelAndView edit(@PathVariable("username") String username ,Principal principal, HttpServletRequest request) throws UserNotFound {
        Users users = userService.getUser(username);
        if(users == null && (!request.isUserInRole("ROLE_TEACHER") || !principal.getName().equals(users.getUsername()))) {
            throw new UserNotFound("User not found.");
        }
        ModelAndView modelAndView = new ModelAndView("userUpdate");
        modelAndView.addObject("user", users);

        Form form = new Form();
        form.setUsername(users.getUsername());
        form.setFullname(users.getFullName());
        form.setPhoneNumber(users.getPhone());
        form.setEmail(users.getEmail());
        form.setRole(users.getUserRole());
        modelAndView.addObject("userUpdate", form);
        return modelAndView;
    }

    @PostMapping("/edit/{username}")
    public String userUpdate(@PathVariable("username") String username, Form form,
                             Principal principal, HttpServletRequest request) throws IOException, UserNotFound {

        Users users = userService.getUser(username) ;
        if(users == null &&(!request.isUserInRole("ROLE_TEACHER") )) {
            return "redirect:/index";
        }
        if (form.getPassword() == null || form.getPassword().isEmpty()) {
            userService.editUserWithoutPassword(form.getUsername(), form.getPhoneNumber(), form.getEmail(), form.getRole(), form.getFullname());
        }else {
            userService.editUser(form.getUsername(), form.getPassword(), form.getPhoneNumber(), form.getEmail(), form.getRole(), form.getFullname());
        }
        return "redirect:/user/list";
    }

    @GetMapping("/delete/{username}")
    public String deleteTicket(@PathVariable("username") String username) {
        userService.deleteUser(username);

        return "redirect:/user/list";
    }
}
