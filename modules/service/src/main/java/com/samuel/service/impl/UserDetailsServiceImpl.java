package com.samuel.service.impl;

import com.samuel.repository.EmployeeRepository;
import com.samuel.repository.model.Employee;
import com.samuel.service.auth.AdminPrincipal;
import com.samuel.service.auth.EmployeePrincipal;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        if (StringUtils.isNumeric(username)) {
            final Employee employee = Optional
                    .ofNullable(employeeRepository.findByCpf(username))
                    .orElseThrow(() -> new UsernameNotFoundException(username));
            return new EmployeePrincipal(employee);
        }

        return new AdminPrincipal();
    }
}
