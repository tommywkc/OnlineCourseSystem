package S380F_project.dao;

import S380F_project.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Users findByusername(String username);
}
