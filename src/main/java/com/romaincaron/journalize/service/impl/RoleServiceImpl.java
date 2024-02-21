package com.romaincaron.journalize.service.impl;

import com.romaincaron.journalize.model.Role;
import com.romaincaron.journalize.repository.RoleRepository;
import com.romaincaron.journalize.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getRoles() {
        return this.roleRepository.findAll();
    }

    @Override
    public Role persist(Role role) {
        return this.roleRepository.save(role);
    }

    @Override
    public Role getRole(Long id) {
        return this.roleRepository.findById(id).orElseThrow();
    }

    @Override
    public Role update(Role role) {
        return this.roleRepository.save(role);
    }

    @Override
    public void delete(Long id) {
        roleRepository.findById(id).orElseThrow();
        roleRepository.deleteById(id);
    }
}
