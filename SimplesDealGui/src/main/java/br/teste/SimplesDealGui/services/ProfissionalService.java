package br.teste.SimplesDealGui.services;

import br.teste.SimplesDealGui.model.Profissional;
import br.teste.SimplesDealGui.model.dto.ProfissionalDTO;

import java.util.List;

public interface ProfissionalService {

    List<Profissional> findByAtributo(String atributo);

    Profissional findById(Integer id);

    Profissional create(ProfissionalDTO obj);

    Profissional update(ProfissionalDTO obj);

    void delete(Integer id);

}
