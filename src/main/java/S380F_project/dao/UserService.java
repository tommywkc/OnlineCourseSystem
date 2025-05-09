package S380F_project.dao;

import S380F_project.exception.UserNameUsed;
import S380F_project.exception.UserNotFound;
import S380F_project.model.Users;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Resource
    private UsersRepository usersRepository;

    @Transactional
    public List<Users> getUsers() { return usersRepository.findAll(); }

    @Transactional
    public Users getUser(String username) throws UserNotFound {
        Users users = usersRepository.findByusername(username);
        if (users == null) {
            throw new UserNotFound("User not found");
        }
        return users;
    }

    @Transactional
    public void deleteUser(String loginName) throws UserNotFound {
        Users users = usersRepository.findByusername(loginName);
        if (users == null) {
            throw new UserNotFound(loginName);
        }
        usersRepository.delete(users);
    }
    @Transactional
    public void createUser(String username, String loginPassword,  String phone, String email,String userRole,String fullName) throws UserNameUsed {
        if(usersRepository.findByusername(username) == null) {
            Users users = new Users(username, passwordEncoder.encode(loginPassword), phone, email, userRole,fullName);
            usersRepository.save(users);
        }
        else {
            throw new UserNameUsed(username);
        }
    }

    @Transactional
    public void editUser(String username, String loginPassword, String phone, String email, String userRole,String fullName) throws UserNotFound {
        Users users = usersRepository.findByusername(username);

        if (users == null) {
            throw new UserNotFound(username);
        }
        users.setLoginPassword( passwordEncoder.encode(loginPassword));
        users.setPhone(phone);
        users.setEmail(email);
        users.setUserRole(userRole);
        users.setFullName(fullName);
        usersRepository.save(users);
    }

    @Transactional
    public void editUserWithoutPassword(String username, String phone, String email, String userRole,String fullName) throws UserNotFound {
        Users users = usersRepository.findByusername(username);

        if (users == null) {
            throw new UserNotFound(username);
        }
        users.setPhone(phone);
        users.setEmail(email);
        users.setUserRole(userRole);
        users.setFullName(fullName);
        usersRepository.save(users);
    }

    @Transactional
    @PostConstruct
    public void createFirstUser() {
        if (usersRepository.count() == 0) {
            Users users = new Users("keith",  passwordEncoder.encode("keithpw"),
                    "00000000","test@test.com","ROLE_TEACHER","Keith Lee");
            Users user2 = new Users("smith",  passwordEncoder.encode("smithpw"),
                    "1111111","smith@test.com","ROLE_STUDENT","John Smith");
            usersRepository.save(users);
            usersRepository.save(user2);
        }
    }

}
