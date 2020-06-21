package pl.fekeni.PekeN.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.fekeni.PekeN.entity.Arena;

@Repository
public interface ArenaRespository extends CrudRepository<Arena, Long> {
    public Iterable<Arena> findByUserIdFrom(Long id);
}
