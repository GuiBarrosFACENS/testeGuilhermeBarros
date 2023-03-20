package br.teste.SimplesDealGui.repositories;

import br.teste.SimplesDealGui.model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Integer> {
    Optional<Contato> findByVariavelContato(String variavelContato);

}
