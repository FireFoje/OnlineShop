package spring.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.app.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
