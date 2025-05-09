package S380F_project.validator;


import S380F_project.dao.UsersRepository;
import S380F_project.model.Users;
import S380F_project.controller.UserController.Form;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    @Resource
    UsersRepository usersRepository;

    @Override
    public boolean supports(Class<?> type) {
        Class<Form> formClass = Form.class;
        return formClass.equals(type);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Form user = (Form) target;
        if (user.getUsername().equals("")) {
            return;
        }
        Users users = usersRepository.findByusername(user.getUsername());
        if (users != null) {
            errors.rejectValue("username", "", "User already exists.");
        }
    }
}
