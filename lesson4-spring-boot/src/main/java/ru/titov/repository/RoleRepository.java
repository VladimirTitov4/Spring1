package ru.titov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.titov.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
