package com.example.demo.service.Users;

import com.example.demo.model.Role;
//import com.example.demo.model.UserPrinciple;
import com.example.demo.model.Users;
import com.example.demo.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersRepository usersRepository;

//    @Override
//    @Transactional
//    public UserPrinciple loadUserByUsername(String username) throws UsernameNotFoundException {
//        Users users = usersRepository.findByUsername(username);
//        return new UserPrinciple(users);
//    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = usersRepository.findByUsername(username);
        List<Role> roles = new ArrayList<>();
        roles.add(users.getRole());
        User user = new User(users.getUsername(), users.getPassword(), roles);
        return user;
    }

    @Override
    public Iterable<Users> findAll() {
        return usersRepository.findAll();
    }

    @Override
    public Users findUsersById(Long id) {
        return usersRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Users users) {
        usersRepository.save(users);
    }

    @Override
    public void remove(Long id) {
        usersRepository.deleteById(id);
    }
}
