package pl.fekeni.PekeN.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.fekeni.PekeN.entity.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    public Role findByRole(String role);
}
