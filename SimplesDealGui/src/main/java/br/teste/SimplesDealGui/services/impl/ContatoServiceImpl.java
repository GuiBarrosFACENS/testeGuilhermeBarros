package br.teste.SimplesDealGui.services.impl;

import br.teste.SimplesDealGui.model.Contato;
import br.teste.SimplesDealGui.model.dto.ContatoDTO;
import br.teste.SimplesDealGui.repositories.ContatoRepository;
import br.teste.SimplesDealGui.services.ContatoService;
import br.teste.SimplesDealGui.services.exceptions.DataIntegrityViolationException;
import br.teste.SimplesDealGui.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContatoServiceImpl implements ContatoService {

    @Autowired
    ContatoRepository contatoRepository;

    @Autowired
    private ModelMapper mapper;


    @Override
    public List<Contato> findByAtributo(String atributo) {
        List<Contato> results = new ArrayList<>();
        List<Contato> allObjects = contatoRepository.findAll();

        for (Contato object : allObjects) {
            if (object.getNomeContato().contains(atributo) || object.getVariavelContato().contains(atributo)) {
                results.add(object);
            }
        }

        return results;
    }

    @Override
    public Contato findById(Integer id) {
        Optional<Contato> obj = contatoRepository.findById(id);
        return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado"));
    }

    @Override
    public Contato create(ContatoDTO obj) {
        findByVariavelContato(obj);
        return contatoRepository.save(mapper.map(obj, Contato.class));
    }

    @Override
    public Contato update(ContatoDTO obj) {
        findByVariavelContato(obj);
        return contatoRepository.save(mapper.map(obj, Contato.class));
    }

    @Override
    public void delete(Integer id) {
        findById(id);
        contatoRepository.deleteById(id);
    }

    private void findByVariavelContato(ContatoDTO obj){
        Optional<Contato> contato = contatoRepository.findByVariavelContato(obj.getVariavelContato());
        if(contato.isPresent() && !contato.get().getId().equals(obj.getId())){
            throw new DataIntegrityViolationException("Contato já cadastrado no sistema.");
        }
    }
}
