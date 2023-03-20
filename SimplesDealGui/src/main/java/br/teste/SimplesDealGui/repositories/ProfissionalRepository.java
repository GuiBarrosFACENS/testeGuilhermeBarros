package br.teste.SimplesDealGui.repositories;

import br.teste.SimplesDealGui.model.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfissionalRepository extends JpaRepository<Profissional, Integer> {
}
