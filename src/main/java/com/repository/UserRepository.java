package com.repository;

import com.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findUserById(Long id);
    User findUserByEmail(String mail);
    List<User> findAll();
    User findUserByPesel(String id);

}
