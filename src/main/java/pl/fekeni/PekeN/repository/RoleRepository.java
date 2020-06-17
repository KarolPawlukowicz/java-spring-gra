package pl.fekeni.PekeN.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.fekeni.PekeN.entity.Role;

//JpaRepository<Role, Integer>
//CrudRepository<Role, Long>
@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

    public Role findByRole(String role);
}
