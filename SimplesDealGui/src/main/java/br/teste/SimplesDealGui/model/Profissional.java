package br.teste.SimplesDealGui.model;

import br.teste.SimplesDealGui.model.enumerator.Cargo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "profissionais")
public class Profissional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private Cargo cargo;

    private Date nascimento;

    private LocalDateTime dataCriacao;

    @OneToMany
    @JoinColumn(name = "contato_id")
    private List<Contato> contato;

}
