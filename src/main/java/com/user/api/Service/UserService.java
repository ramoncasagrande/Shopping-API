package com.user.api.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.api.DTO.UserDTO;
import com.user.api.Model.User;
import com.user.api.Repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> getAll(){
        List<User> users = userRepository.findAll();
        return users
                .stream()
                .map(UserDTO::convert)
                .collect(Collectors.toList());
    }

    public UserDTO findById(Long userId){
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            return UserDTO.convert(user.get());
        }
        return null;
    }

    public UserDTO save(UserDTO userDTO){
        User user = userRepository.save(User.convert(userDTO));
        return UserDTO.convert(user);
    }

    public UserDTO delete(Long userId){
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            userRepository.delete(user.get());
        }
        return null;
    }

    public UserDTO findByCpf(String userCpf){
        User user = userRepository.findByCpf(userCpf);
        if(user != null){
            return UserDTO.convert(user);
        }
        return null;
    }

    public List<UserDTO> queryByName(String nome){
        List<User> users = userRepository.queryByNomeLike(nome);
        return users
                .stream()
                .map(UserDTO::convert)
                .collect(Collectors.toList());
    }
}
