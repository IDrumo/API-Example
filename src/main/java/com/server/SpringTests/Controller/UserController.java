package com.server.SpringTests.Controller;

import com.server.SpringTests.DTO.ErrorDTO;
import com.server.SpringTests.Entity.UserEntity;
import com.server.SpringTests.Exceptions.UserAlreadyExistException;
import com.server.SpringTests.Exceptions.UserNotFoundException;
import com.server.SpringTests.Service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//указываем, что это контроллер и привязываем его к домену через /api
@RestController
@RequestMapping("/api/user")
public class UserController {


    // как бы внедряем userService в Controller
    @Autowired
    private UserService userService;
    // Service нужен, чтобы отделить логику обработки запросов от логики сообщения бека с сайтом
    // т.е. Запросы прилетают сюда, а обрабатываются в Service

    // Указываем, что слушаем post запрос (запрос на создание)
    @PostMapping
    public ResponseEntity<?> registration(@RequestBody UserEntity user){
        try {
            // А вот и функция, которая делает всю грязную работу
//            userService.registration(user);
//            return ResponseEntity.ok("Пользователь успешно сохранен");
            return ResponseEntity.ok(userService.registration(user));
        } catch (UserAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers() {
        try {
            return ResponseEntity.ok(userService.getAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping
    public ResponseEntity<?> getOneUser(@RequestParam Long id) {
        // Тут используется квери параметр, т.е. в id прилетает то, что идет после ?
        // Пример site.ru/api?id=1 то же самое, что присвоить id единицу
        try {
            return ResponseEntity.ok(userService.getOne(id));

        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id,
                                        @RequestBody UserEntity user){
        try {
            return ResponseEntity.ok(userService.updateUser(user, id));
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @DeleteMapping("/{id}")
    // чтобы получить часть от запроса, указываем это в аннотации выше
    // и в функции указываем какой параметр принемаем (названия должны совпадать)
    // пример: из site.ru/api/1 получим 1
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        try {
            return ResponseEntity.ok(userService.delete(id));
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        }catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }


    @GetMapping("/error/get")
    public ResponseEntity<?> errorGetMethod() {
        // Эмуляция ошибки на запрос GET
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDTO("Ошибка на запрос GET"));
    }

    @PostMapping("/error/post")
    public ResponseEntity<?> errorPostMethod() {
        // Эмуляция ошибки на запрос POST
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO("Ошибка на запрос POST"));
    }

    @PutMapping("/error/put")
    public ResponseEntity<?> errorPutMethod() {
        // Эмуляция ошибки на запрос PUT
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorDTO("Ошибка на запрос PUT"));
    }

    @DeleteMapping("/error/delete")
    public ResponseEntity<?> errorDeleteMethod() {
        // Эмуляция ошибки на запрос DELETE
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO("Ошибка на запрос DELETE"));
    }
}

