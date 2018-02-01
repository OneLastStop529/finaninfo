package cn.edu.lixin.financialinfomation.user;

import cn.edu.lixin.financialinfomation.commands.UserCommand;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;


@RepositoryRestResource
public interface UserRepository extends CrudRepository<User,Long> {
    Optional<User> findByUsername(String username);
    UserCommand findCommandById(Long id);

}
