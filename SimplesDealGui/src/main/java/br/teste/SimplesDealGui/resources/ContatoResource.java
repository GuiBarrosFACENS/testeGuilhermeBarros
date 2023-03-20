package br.teste.SimplesDealGui.resources;

import br.teste.SimplesDealGui.model.Contato;
import br.teste.SimplesDealGui.model.dto.ContatoDTO;
import br.teste.SimplesDealGui.services.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/contato")
public class ContatoResource {

    public static  final String ID = "/{id}";

    @Autowired
    ModelMapper mapper;

    @Autowired
    ContatoService contatoService;

    @GetMapping("/atributo")
    public ResponseEntity<List<Contato>> searchByAttribute(@RequestParam String atributo) {
        List<Contato> results = contatoService.findByAtributo(atributo);

        if (results.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(results);
        }

    }

    @GetMapping(value = ID)
    public ResponseEntity<ContatoDTO> findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(mapper.map(contatoService.findById(id), ContatoDTO.class));
    }

    @PostMapping
    public ResponseEntity<ContatoDTO> create(@RequestBody ContatoDTO contatoDTO){
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(ID)
                .buildAndExpand(contatoService.create(contatoDTO).getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = ID)
    public ResponseEntity<ContatoDTO> update(@PathVariable Integer id, @RequestBody ContatoDTO obj) {
        obj.setId(id);
        return ResponseEntity.ok().body(mapper.map(contatoService.update(obj), ContatoDTO.class));
    }

    @DeleteMapping(value = ID)
    public ResponseEntity<ContatoDTO> delete(@PathVariable Integer id) {
        contatoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
