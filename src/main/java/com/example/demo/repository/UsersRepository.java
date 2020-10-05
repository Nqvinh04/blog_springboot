package com.example.demo.repository;

import com.example.demo.model.Users;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends PagingAndSortingRepository<Users, Long> {
    Users findByUsername(String username);
}
