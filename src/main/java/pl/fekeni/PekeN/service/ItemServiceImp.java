package pl.fekeni.PekeN.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.fekeni.PekeN.entity.Item;
import pl.fekeni.PekeN.repository.ItemRespository;

@Service
public class ItemServiceImp implements ItemService{

    @Autowired
    ItemRespository itemRespository;

    @Override
    public Iterable<Item> getAllItems(){
        return itemRespository.findAll();
    }
}
