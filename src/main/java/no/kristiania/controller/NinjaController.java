package no.kristiania.controller;


import no.kristiania.model.Ninja;
import no.kristiania.repository.NinjaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    @Autowired
    private NinjaRepository ninjaRepository;

    //Read
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Ninja> index() {
        Iterable<Ninja> ninjaIterable = ninjaRepository.findAll();
        List<Ninja> ninjaList = new ArrayList<>();
        ninjaIterable.forEach(a -> ninjaList.add(a));
        return ninjaList;
    }

    @GetMapping(value = "/ninja/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Ninja> getNinjaById(@PathVariable(value = "id") Long ninjaId) {
        List<Ninja> ninjaList = new ArrayList<>();
        Iterable<Ninja> ninjaIterable = ninjaRepository.findAll();
        ninjaIterable.forEach(a -> ninjaList.add(a));
        return ninjaList;
    }

    // Create
    @PostMapping("/ninja")
    public Ninja createNinja(@RequestBody Ninja ninja){
        return ninjaRepository.save(ninja);
    }

    // Update
    @PutMapping("/ninja/{id}")
    public ResponseEntity <Ninja> updateNinja(@PathVariable(value = "id") Long ninjaId,
                                              @RequestBody Ninja ninjadetails) throws ResourceNotFoundException{
        Ninja ninja = ninjaRepository.findById(ninjaId)
                .orElseThrow(() -> new ResourceNotFoundException("Ninja with this id not found :: " + ninjaId));

        ninja.setName(ninjadetails.getName());
        ninja.setKatana(ninjadetails.getKatana());
        final Ninja updatedNinja = ninjaRepository.save(ninja);
        return ResponseEntity.ok(updatedNinja);
    }

    // Delete
    @DeleteMapping("/ninja/{id}")
    public Map<String, Boolean> deleteNinja(@PathVariable(value = "id") Long ninjaId)
        throws ResourceNotFoundException {
            Ninja ninja = ninjaRepository.findById(ninjaId)
                    .orElseThrow(() -> new ResourceNotFoundException("Ninja with this id not found :: " + ninjaId));

            ninjaRepository.delete(ninja);
            Map <String, Boolean> response = new HashMap<>();
            response.put("deleted", Boolean.TRUE);
            return response;
    }

}
