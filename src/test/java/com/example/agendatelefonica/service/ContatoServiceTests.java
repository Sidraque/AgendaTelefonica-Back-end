package com.example.agendatelefonica.service;

import com.example.agendatelefonica.model.Contato;
import com.example.agendatelefonica.repository.ContatoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ContatoServiceTests {

    @Mock
    private ContatoRepository contatoRepository;

    @InjectMocks
    private ContatoService contatoService;

    @Test
    public void quandoSalvarContato_entaoRetornarContato() {
        Contato contato = new Contato();
        contato.setContatoNome("Teste");
        contato.setContatoCelular("12345678901");

        when(contatoRepository.save(any(Contato.class))).thenReturn(contato);

        Contato savedContato = contatoService.salvarContato(contato);
        assertNotNull(savedContato);
        assertEquals("Teste", savedContato.getContatoNome());
    }
}
