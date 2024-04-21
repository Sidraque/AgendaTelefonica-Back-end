package com.example.agendatelefonica.controller;

import com.example.agendatelefonica.model.Contato;
import com.example.agendatelefonica.service.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/contatos")
public class ContatoController {
    private final ContatoService contatoService;

    @Autowired
    public ContatoController(ContatoService contatoService) {
        this.contatoService = contatoService;
    }

    @PostMapping
    public ResponseEntity<Contato> adicionarContato(@RequestBody Contato contato) {
        Contato novoContato = contatoService.salvarContato(contato);
        return new ResponseEntity<>(novoContato, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Contato>> listarTodosContatos() {
        return ResponseEntity.ok(contatoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contato> buscarContatoPorId(@PathVariable Long id) {
        Optional<Contato> contato = contatoService.buscarPorId(id);
        return contato.map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contato> atualizarContato(@PathVariable Long id, @RequestBody Contato contato) {
        return contatoService.buscarPorId(id)
                .map(contatoExistente -> {
                    contato.setContatoId(contatoExistente.getContatoId());
                    Contato contatoAtualizado = contatoService.salvarContato(contato);
                    return new ResponseEntity<>(contatoAtualizado, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarContato(@PathVariable Long id) {
        return contatoService.buscarPorId(id)
                .map(contato -> {
                    contatoService.deletarContato(contato.getContatoId());
                    return new ResponseEntity<>(HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PatchMapping("/{id}/favorito")
    public ResponseEntity<Contato> atualizarFavorito(@PathVariable Long id, @RequestBody Map<String, Boolean> updates) {
        return contatoService.buscarPorId(id)
                .map(contato -> {
                    Boolean favorito = updates.get("favorito");
                    if (favorito != null) {
                        contato.setContatoSnFavorito(favorito ? 'S' : 'N');
                        contatoService.salvarContato(contato);
                    }
                    return new ResponseEntity<>(contato, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PatchMapping("/{id}/ativo")
    public ResponseEntity<Contato> atualizarAtivo(@PathVariable Long id, @RequestBody Map<String, Boolean> updates) {
        return contatoService.buscarPorId(id)
                .map(contato -> {
                    Boolean ativo = updates.get("ativo");
                    if (ativo != null) {
                        contato.setContatoSnAtivo(ativo ? 'S' : 'N');
                        contatoService.salvarContato(contato);
                    }
                    return new ResponseEntity<>(contato, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/exists/{celular}")
    public ResponseEntity<Contato> checkContactExists(@PathVariable String celular) {
        Optional<Contato> contato = contatoService.buscarPorCelular(celular);
        if (contato.isPresent()) {
            return ResponseEntity.ok(contato.get());
        } else {
            return ResponseEntity.ok(null);
        }
    }
}
