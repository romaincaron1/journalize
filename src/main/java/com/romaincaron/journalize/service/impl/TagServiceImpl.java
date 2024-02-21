package com.romaincaron.journalize.service.impl;

import com.romaincaron.journalize.model.Role;
import com.romaincaron.journalize.repository.RoleRepository;
import com.romaincaron.journalize.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements RoleService {
    private RoleRepository tagRepository;

    @Autowired
    public TagServiceImpl(RoleRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public List<Role> getRoles() {
        return this.tagRepository.findAll();
    }

    @Override
    public Role persist(Role tag) {
        return this.tagRepository.save(tag);
    }

    @Override
    public Role getRole(Long id) {
        return this.tagRepository.findById(id).orElseThrow();
    }

    @Override
    public Role update(Role tag) {
        return this.tagRepository.save(tag);
    }

    @Override
    public void delete(Long id) {
        tagRepository.findById(id).orElseThrow();
        tagRepository.deleteById(id);
    }
}
