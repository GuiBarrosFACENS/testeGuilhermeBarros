package br.teste.SimplesDealGui.services.impl;

import br.teste.SimplesDealGui.repositories.ProfissionalRepository;
import br.teste.SimplesDealGui.model.Contato;
import br.teste.SimplesDealGui.model.Profissional;
import br.teste.SimplesDealGui.model.dto.ProfissionalDTO;
import br.teste.SimplesDealGui.services.ProfissionalService;
import br.teste.SimplesDealGui.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfissionalServiceImpl implements ProfissionalService {

    @Autowired
    ProfissionalRepository profissionalRepository;

    @Autowired
    private ModelMapper mapper;


    @Override
    public List<Profissional> findByAtributo(String atributo) {
        List<Profissional> results = new ArrayList<>();
        List<Profissional> allObjects = profissionalRepository.findAll();

        for (Profissional object : allObjects) {

            if (object.getNome().contains(atributo) ||
                    object.getCargo().name().contains(atributo) ||
                    object.getNascimento().toString().contains(atributo) ||
                    object.getDataCriacao().toString().contains(atributo) ) {
                results.add(object);
                continue;
            }

            List<Contato> listAttribute = object.getContato();
            if (listAttribute != null) {
                for (Contato listContato : listAttribute) {
                    if (listContato.toString().contains(atributo)) {
                        results.add(object);
                        break;
                    }
                }
            }

        }

        return results;
    }

    @Override
    public Profissional findById(Integer id) {
        Optional<Profissional> obj = profissionalRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    @Override
    public Profissional create(ProfissionalDTO obj) {
        obj.setDataCriacao(LocalDateTime.now());
        return profissionalRepository.save(mapper.map(obj, Profissional.class));
    }

    @Override
    public Profissional update(ProfissionalDTO obj) {
        return profissionalRepository.save(mapper.map(obj, Profissional.class));
    }

    @Override
    public void delete(Integer id) {
        findById(id);
        profissionalRepository.deleteById(id);
    }


}
