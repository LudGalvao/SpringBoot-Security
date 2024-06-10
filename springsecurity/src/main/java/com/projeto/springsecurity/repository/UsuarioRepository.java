package com.projeto.springsecurity.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.springsecurity.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID>{

}
