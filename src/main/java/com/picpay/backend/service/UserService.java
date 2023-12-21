package com.picpay.backend.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.picpay.backend.domain.user.User;
import com.picpay.backend.domain.user.UserType;
import com.picpay.backend.dto.UserDTO;
import com.picpay.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void validateTransaction(User sender, BigDecimal amount) throws Exception{
        if(sender.getUserType() == UserType.MERCHANT){
            throw new Exception("Usuario do tipo lojista não está autorizado a realizar transações");
        }

        if(sender.getBalance().compareTo(amount) < 0){
            throw new Exception("Saldo insuficiente");
        }
    }

    public User findUserById(Long id) throws Exception{
        return this.userRepository.findUserById(id).orElseThrow(() -> new Exception("Usuário não encontrado"));
    }

    public User createUser(UserDTO data){
        var passwordHashed = BCrypt.withDefaults().hashToString(6, data.password().toCharArray());
        UserDTO newUserDTO = new UserDTO(data, passwordHashed);
        System.out.println(newUserDTO);
        User newUser = new User(newUserDTO);
        this.saveUser(newUser);

        return newUser;
    }

    public List<User> getAllUsers(){
        return this.userRepository.findAll();
    }

    public void saveUser(User user){
        this.userRepository.save(user);
    }

}
