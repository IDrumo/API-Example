package com.server.SpringTests.Service;

import com.server.SpringTests.Constants.Code;
import com.server.SpringTests.DTO.UserPasswordlessDTO;
import com.server.SpringTests.Entity.UserEntity;
import com.server.SpringTests.Response.Error.Error;
import com.server.SpringTests.Response.Exceptions.CommonException;
import com.server.SpringTests.Response.Exceptions.UserAlreadyExistException;
import com.server.SpringTests.Response.Exceptions.UserNotFoundException;
import com.server.SpringTests.Repository.UserRepos;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepos userRepos;

    @Autowired
    private ModelMapper modelMapper;


    public UserEntity registration(UserEntity user) {
        if (userRepos.findByLogin(user.getLogin()) != null) {
            throw new UserAlreadyExistException("Пользователь уже существует");
        }
        return userRepos.save(user);
    }

    public List<UserPasswordlessDTO> getAll(){
            List<UserPasswordlessDTO> userEntities =
                    Streamable.of(userRepos.findAll()).stream()
                    .map(user -> modelMapper.map(user, UserPasswordlessDTO.class)).toList();
            if (userEntities.isEmpty())
                throw new CommonException(Error.builder().code(Code.CUSTOM_ERROR_CODE_3).message("В базе данных еще нет пользователей").build());
        return userEntities;
    }

    public UserPasswordlessDTO getOne(Long id) {
        // Централизованная система ошибок может обрабатывать и такие ошибки
//        int x = 1/0;
        Optional<UserEntity> user = userRepos.findById(id);

        //почему-то не пробрасывает ошибку
        if (user.isEmpty()) {
            throw new UserNotFoundException("Пользователь не найден");
        }
        return modelMapper.map(user, UserPasswordlessDTO.class);
        //Конвертируем в ДТО, у которого нет пароля, чтобы при запросе не возвращать этот самый пароль
        //так сказать обрезаем информацию
    }

    public UserEntity updateUser(UserEntity user, Long id) {
        UserEntity new_user = userRepos.findById(id).orElseThrow(() -> new UserNotFoundException("Пользователь для обновления не найден"));
        if (user.getLogin() != null) new_user.setLogin(user.getLogin());
        if (user.getPassword() != null) new_user.setPassword(user.getPassword());
        return modelMapper.map(userRepos.save(new_user), UserEntity.class);
    }

    public Long delete(Long id) {
        UserEntity user = userRepos.findById(id).orElseThrow(() ->
                new UserNotFoundException("Пользователь для удаления не найден"));
        userRepos.deleteById(id);
        // Если хотим удалять по логину, то в репозитории добавляем эту функцию
        return id;
    }
}
