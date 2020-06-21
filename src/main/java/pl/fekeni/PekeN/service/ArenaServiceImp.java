package pl.fekeni.PekeN.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.fekeni.PekeN.entity.Arena;
import pl.fekeni.PekeN.repository.ArenaRespository;

@Service
public class ArenaServiceImp implements ArenaService {

    @Autowired
    ArenaRespository arenaRespository;

    @Override
    public Iterable<Arena> getAllArenas(){
        return arenaRespository.findAll();
    }

    @Override
    public void challangeUser(Long idToUser, Long idFromUser) throws Exception{
        Arena arena = new Arena();
        arena.setUserIdTo(idToUser);
        arena.setUserIdFrom(idFromUser);
        arenaRespository.save(arena);
    }

    @Override
    public Iterable<Arena> getAllArenaseById(Long id){
        return arenaRespository.findByUserIdFrom(id);
    }

    @Override
    public void declineFight(Long fightId) throws Exception{
        arenaRespository.deleteById(fightId);

    }
}
