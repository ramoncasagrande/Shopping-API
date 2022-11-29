package com.user.api.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.api.Model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    User findByCpf(String cpf);

    List<User> queryByNomeLike(String nome);

}
