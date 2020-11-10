package no.kristiania.repository;

import no.kristiania.model.Ninja;

import org.springframework.data.repository.CrudRepository;
import java.util.List;
import org.springframework.stereotype.Repository;





@Repository
public interface NinjaRepository extends CrudRepository<Ninja, Long> {

    public List<Ninja> findByName(String name);
}
