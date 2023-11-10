package com.server.SpringTests.Service;

import com.server.SpringTests.DTO.UserPasswordlessDTO;
import com.server.SpringTests.Entity.UserEntity;
import com.server.SpringTests.Exceptions.UserAlreadyExistException;
import com.server.SpringTests.Exceptions.UserNotFoundException;
import com.server.SpringTests.Repository.UserRepos;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepos userRepos;

    @Autowired
    private ModelMapper modelMapper;


    public UserEntity registration(UserEntity user) throws UserAlreadyExistException {
        if (userRepos.findByLogin(user.getLogin()) != null) {
            throw new UserAlreadyExistException("Пользователь уже существует");
        }
        return userRepos.save(user);
    }

    public List<UserPasswordlessDTO> getAll(){
            List<UserPasswordlessDTO> userEntities =
                    Streamable.of(userRepos.findAll()).stream()
                    .map(user -> modelMapper.map(user, UserPasswordlessDTO.class)).toList();
        return userEntities;
    }

    public UserPasswordlessDTO getOne(Long id) throws UserNotFoundException {
        UserEntity user = userRepos.findById(id).get();

        //почему-то не пробрасывает ошибку
        if (user == null) {
            throw new UserNotFoundException("Пользователь не найден");
        }
        return modelMapper.map(user, UserPasswordlessDTO.class);
        //Конвертируем в ДТО, у которого нет пароля, чтобы при запросе не возвращать этот самый пароль
        //так сказать обрезаем информацию
    }

    public Long delete(Long id){
        userRepos.deleteById(id);
        // Если хотим удалять по логину, то в репозитории добавляем эту функцию
        return id;
    }
}
