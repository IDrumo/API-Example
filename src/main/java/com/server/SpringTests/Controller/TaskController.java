package com.server.SpringTests.Controller;

import com.server.SpringTests.model.TaskEntity;
import com.server.SpringTests.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody TaskEntity task,
                                     @RequestParam Long userId){
        try {
            return ResponseEntity.ok(taskService.createTask(task, userId));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @PutMapping
    public ResponseEntity<?> updateTaskStatus(@RequestParam Long id){
        try {
            return ResponseEntity.ok(taskService.updateStatus(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }
}
