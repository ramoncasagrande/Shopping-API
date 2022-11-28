package com.shopping.api.Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.api.DTO.UserDTO;

import jakarta.annotation.PostConstruct;

@RestController
public class UserController {

    @GetMapping("/")
    public String getMessage(){
        return "Spring Boot is Working";
    }

    public static List<UserDTO> userList = new ArrayList<UserDTO>();

    @PostConstruct
    public void initialList(){
        UserDTO userDTO = new UserDTO();
            userDTO.setNome("Ramon Casagrande");
            userDTO.setCpf("04191679945");
            userDTO.setEndereco("Rua Felipe Schmidt, 249");
            userDTO.setEmail("ramon@email.com");
            userDTO.setTelefone("3413-8848");
            userDTO.setDataCadastro(new Date());

            userList.add(userDTO);

        UserDTO userDTO2 = new UserDTO();
            userDTO2.setNome("Josimara Vagner");
            userDTO2.setCpf("05539299922");
            userDTO2.setEndereco("Rua Cel. Pedro Benedet, 333");
            userDTO2.setEmail("josy@email.com");
            userDTO2.setTelefone("3413-8848");
            userDTO2.setDataCadastro(new Date());

            userList.add(userDTO2);
    }

    @GetMapping("/users")
    public List<UserDTO> getUser(){
        return userList;
    }

    @GetMapping("/users/{cpf}")
    public UserDTO getUserCpf(@PathVariable String cpf){

        for(UserDTO user:userList){
            if(user.getCpf().equals(cpf)){
                return user;
            }
        }
        return null;
    }

    @PostMapping("/newUser")
    public UserDTO insertUser(@RequestBody UserDTO userModel){
        userModel.setDataCadastro(new Date());
        userList.add(userModel);
        return userModel;
    }

    @DeleteMapping("/delUser/{cpf}")
    public boolean deleteUser(@PathVariable String cpf){
        for(UserDTO user: userList){
            if(user.getCpf().equals(cpf)){
                userList.remove(user);
                return true;
            }
        }
        return false;
    }
}
