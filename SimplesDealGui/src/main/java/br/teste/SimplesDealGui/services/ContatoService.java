package br.teste.SimplesDealGui.services;

import br.teste.SimplesDealGui.model.Contato;
import br.teste.SimplesDealGui.model.dto.ContatoDTO;

import java.util.List;

public interface ContatoService {

    List<Contato> findByAtributo(String atributo);
    Contato findById(Integer id);

    Contato create(ContatoDTO obj);

    Contato update(ContatoDTO obj);
    void delete(Integer id);
}
