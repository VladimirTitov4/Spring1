package ru.titov.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.titov.model.Role;
import ru.titov.repository.RoleRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public List<Role> findAll() {
        return roleRepository.findAll();
    }
}
