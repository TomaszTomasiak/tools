package com.repository;

import com.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findUserById(long id);
    User findUserByEmail(String mail);
    //User addUser(User user);

    List<User> findAll();

}
