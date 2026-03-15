package sfera.pdponline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sfera.pdponline.entity.Roles;
import sfera.pdponline.entity.enums.Role;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Integer> {
    Roles findByRole(Role role);
}
