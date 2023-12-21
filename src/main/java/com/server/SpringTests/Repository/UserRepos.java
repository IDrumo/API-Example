package com.server.SpringTests.Repository;

import com.server.SpringTests.model.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepos extends CrudRepository<UserEntity, Long> {
    UserEntity findByLogin(String login);
}
