package pl.fekeni.PekeN.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.fekeni.PekeN.entity.Item;


@Repository
public interface ItemRespository extends CrudRepository<Item, Long> {
}
