package br.teste.SimplesDealGui.model.dto;

import br.teste.SimplesDealGui.model.Contato;
import br.teste.SimplesDealGui.model.enumerator.Cargo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfissionalDTO {

    private Integer id;

    private String nome;

    private Cargo cargo;

    private Date nascimento;

    private LocalDateTime dataCriacao;

    private List<Contato> contato = new ArrayList<>();

}
