package com.romaincaron.journalize.repository;

import com.romaincaron.journalize.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}