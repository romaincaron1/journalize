package com.romaincaron.journalize.api;

import com.romaincaron.journalize.model.Role;
import com.romaincaron.journalize.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/role")
public class RoleRestController {
    private final RoleService roleService;

    @Autowired
    public RoleRestController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<List<Role>> getRoles()
    {
        List<Role> roles = roleService.getRoles();
        return new ResponseEntity<List<Role>>(roles, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Role> getRole(@PathVariable("id") long id) {
        Role role = roleService.getRole(id);
        return new ResponseEntity<Role>(role, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Role> add(@RequestBody Role role)
    {
        Role newRole = roleService.persist(role);
        return new ResponseEntity<Role>(newRole, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Role> update(@RequestBody Role role)
    {
        Role updatedRole = roleService.update(role);
        return new ResponseEntity<Role>(updatedRole, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") long id) {
        roleService.delete(id);
        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }
}
