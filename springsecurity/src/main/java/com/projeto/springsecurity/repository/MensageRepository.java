package com.projeto.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.springsecurity.entities.Mensagem;

public interface MensageRepository extends JpaRepository<Mensagem, Long>{

}
