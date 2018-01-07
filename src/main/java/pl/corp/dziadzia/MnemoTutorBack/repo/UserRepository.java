package pl.corp.dziadzia.MnemoTutorBack.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.corp.dziadzia.MnemoTutorBack.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

    User findUserByUsername(String username);

    User findUserById(Integer id);
}
