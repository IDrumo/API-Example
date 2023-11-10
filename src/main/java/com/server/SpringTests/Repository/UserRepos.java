package com.server.SpringTests.Repository;

import com.server.SpringTests.Entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepos extends CrudRepository<UserEntity, Long> {
    UserEntity findByLogin(String login);
}
