package br.teste.SimplesDealGui.config;

import br.teste.SimplesDealGui.model.Contato;
import br.teste.SimplesDealGui.model.Profissional;
import br.teste.SimplesDealGui.model.enumerator.Cargo;
import br.teste.SimplesDealGui.repositories.ContatoRepository;
import br.teste.SimplesDealGui.repositories.ProfissionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Configuration
@Profile("local")
public class LocalConfig {

    @Autowired
    ContatoRepository contatoRepository;

    @Autowired
    ProfissionalRepository profissionalRepository;

    @Bean
    public void startDB() throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date data = formato.parse("16/03/2023");

        Date data2 = formato.parse("16/03/2023");

        Contato c1 = new Contato(null, "telefone", "11972969401");
        Contato c2 = new Contato(null, "email", "guilherme@email.com");

        Contato c3 = new Contato(null, "telefone", "11972969402");
        Contato c4 = new Contato(null, "email", "gabriel@email.com");

        contatoRepository.saveAll(List.of(c1, c2,c3,c4));

        Profissional p1 = new Profissional(null, "Guilherme", Cargo.DESENVOLVEDOR, data, LocalDateTime.now(), List.of(c1, c2));

        Profissional p2 = new Profissional(null, "Gabriel", Cargo.DESIGNER, data2, LocalDateTime.now(), List.of(c3, c4));

        profissionalRepository.saveAll(List.of(p1, p2));
    }

}
