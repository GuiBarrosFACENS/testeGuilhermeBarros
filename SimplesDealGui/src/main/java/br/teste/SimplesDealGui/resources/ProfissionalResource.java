package br.teste.SimplesDealGui.resources;

import br.teste.SimplesDealGui.model.Profissional;
import br.teste.SimplesDealGui.model.dto.ProfissionalDTO;
import br.teste.SimplesDealGui.services.ProfissionalService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/profissional")
public class ProfissionalResource {

    public static  final String ID = "/{id}";

    @Autowired
    ModelMapper mapper;

    @Autowired
    ProfissionalService profissionalService;


    @GetMapping("/atributo")
    public ResponseEntity<List<Profissional>> searchByAttribute(@RequestParam String atributo) {
        List<Profissional> results = profissionalService.findByAtributo(atributo);

        if (results.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(results);
        }

    }

    @GetMapping(value = ID)
    public ResponseEntity<ProfissionalDTO> findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(mapper.map(profissionalService.findById(id), ProfissionalDTO.class));
    }

    @PostMapping
    public ResponseEntity<ProfissionalDTO> create(@RequestBody ProfissionalDTO profissionalDTO){
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(ID)
                .buildAndExpand(profissionalService.create(profissionalDTO).getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = ID)
    public ResponseEntity<ProfissionalDTO> update(@PathVariable Integer id, @RequestBody ProfissionalDTO obj) {
        obj.setId(id);
        return ResponseEntity.ok().body(mapper.map(profissionalService.update(obj), ProfissionalDTO.class));
    }

    @DeleteMapping(value = ID)
    public ResponseEntity<ProfissionalDTO> delete(@PathVariable Integer id) {
        profissionalService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
}
