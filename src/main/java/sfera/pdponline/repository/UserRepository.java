package sfera.pdponline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sfera.pdponline.entity.Users;
import sfera.pdponline.entity.enums.Role;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByEmail(String email);

    boolean existsByEmailAndRole_Role(String email, Role role);

    boolean existsByEmailAndRole_RoleAndIdNot(String email, Role role, Long id);

    Optional<Users> findByCode(Long code);

}
