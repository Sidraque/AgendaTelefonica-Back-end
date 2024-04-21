package com.example.agendatelefonica.repository;

import com.example.agendatelefonica.model.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContatoRepository extends JpaRepository<Contato, Long> {
    Optional<Contato> findByContatoCelular(String celular);
}
