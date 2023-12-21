package com.server.SpringTests.Controller;

import com.server.SpringTests.model.UserEntity;
import com.server.SpringTests.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
            // А вот и функция, которая делает всю грязную работу
//            return ResponseEntity.ok("Пользователь успешно сохранен");
            return ResponseEntity.ok(userService.registration(user));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping
    public ResponseEntity<?> getOneUser(@RequestParam Long id) {
        // Тут используется квери параметр, т.е. в id прилетает то, что идет после ?
        // Пример site.ru/api?id=1 то же самое, что присвоить id единицу
        return ResponseEntity.ok(userService.getOne(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id,
                                        @RequestBody UserEntity user){
        return ResponseEntity.ok(userService.updateUser(user, id));
    }

    @DeleteMapping("/{id}")
    // чтобы получить часть от запроса, указываем это в аннотации выше
    // и в функции указываем какой параметр принемаем (названия должны совпадать)
    // пример: из site.ru/api/1 получим 1
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.delete(id));
    }
}

