package com.server.SpringTests.Service;

import com.server.SpringTests.DTO.TaskNoUserDTO;
import com.server.SpringTests.model.TaskEntity;
import com.server.SpringTests.model.UserEntity;
import com.server.SpringTests.Repository.TaskRepos;
import com.server.SpringTests.Repository.UserRepos;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    private TaskRepos taskRepos;
    @Autowired
    private UserRepos userRepos;
    @Autowired
    private ModelMapper modelMapper;

    public TaskNoUserDTO createTask(TaskEntity task, Long userId){
        UserEntity user = userRepos.findById(userId).get();
        task.setUser(user);
        return modelMapper.map(taskRepos.save(task), TaskNoUserDTO.class);
    }

    public TaskNoUserDTO updateStatus(Long id){
        TaskEntity task = taskRepos.findById(id).get();
        task.setStatus(!task.getStatus());
        return modelMapper.map(taskRepos.save(task), TaskNoUserDTO.class);
    }
}
