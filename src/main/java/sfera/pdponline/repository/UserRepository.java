package sfera.pdponline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sfera.pdponline.entity.Users;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByEmail(String email);

    boolean existsByEmail(String email);
    Optional<Users> findByCode(Long code);

}
