package com.repository;

import com.domain.User;
import com.dto.UserRegistrationDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findUserById(Long id);
    Optional<User> findUserByMail(String mail);

    User addUser(UserRegistrationDto user);
    int updateUser(String userlogin, User userUpdate);

    @Override
    <S extends User> S save(S entity);

    @Override
    Optional<User> findById(Long aLong);

    @Override
    List<User> findAll();

    @Override
    void deleteById(Long aLong);
}
