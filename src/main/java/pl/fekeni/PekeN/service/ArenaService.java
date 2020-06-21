package pl.fekeni.PekeN.service;

import pl.fekeni.PekeN.entity.Arena;

public interface ArenaService {
    public Iterable<Arena> getAllArenas();

    public Iterable<Arena> getAllArenaseById(Long id);

    public void challangeUser(Long idToUser, Long idFromUser) throws Exception;

}
