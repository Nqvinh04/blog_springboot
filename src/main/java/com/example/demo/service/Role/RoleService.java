package com.example.demo.service.Role;

import com.example.demo.model.Role;

public interface RoleService {
    Iterable<Role> findAll();

    Role findRoleById(Long id);

    void save(Role role);

    void remove(Long id);
}
