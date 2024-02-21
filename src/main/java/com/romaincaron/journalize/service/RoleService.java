package com.romaincaron.journalize.service;

import com.romaincaron.journalize.model.Role;

import java.util.List;

public interface RoleService {
    public List<Role> getRoles();
    public Role persist(Role article);
    public Role getRole(Long id);
    public Role update(Role article);
    public void delete(Long id);
}
