package com.example.agendatelefonica.repository;

import com.example.agendatelefonica.model.Contato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ContatoRepositoryTests {

    @Autowired
    private ContatoRepository contatoRepository;

    @Test
    public void quandoFindByContatoCelular_entaoRetornarContato() {
        String celular = "12345678901";
        Contato contato = contatoRepository.findByContatoCelular(celular).orElse(null);
        assertNotNull(contato);
        assertEquals(celular, contato.getContatoCelular());
    }
}
