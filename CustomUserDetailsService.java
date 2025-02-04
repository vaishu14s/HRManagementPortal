package com.hr.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hr.entity.Employee;
import com.hr.repository.EmployeeRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Employee> optionalEmployee = employeeRepo.findById(Integer.parseInt(username));

        if (optionalEmployee.isEmpty()) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        Employee employee = optionalEmployee.get();

        return User.builder()
                .username(String.valueOf(employee.getId()))
                .password(employee.getPassword())
                .roles(employee.getRole()) // ROLE_ADMIN or ROLE_USER
                .build();
    }
}
