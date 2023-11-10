package com.server.SpringTests.Repository;

import com.server.SpringTests.Entity.TaskEntity;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepos extends CrudRepository<TaskEntity, Long> {
}
