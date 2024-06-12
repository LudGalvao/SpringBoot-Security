package com.projeto.springsecurity.entities;

import java.util.Set;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.projeto.springsecurity.controller.dto.LoginRequest;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_users")
public class Usuario {

    // Gera automaticamente um valor para a chave primária usando UUID, que é um identificador único global.
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id") // Define o nome da coluna como "user_id" no banco de dados.
    private UUID userId;

    // Garante que o valor desta coluna seja único, ou seja, não pode haver usuários com o mesmo username.
    @Column(unique = true)
    private String username;

    private String password;

    // Define um relacionamento muitos-para-muitos com a entidade Role.
    // cascade = CascadeType.ALL indica que todas as operações (persist, merge, remove, etc.) devem ser aplicadas às entidades relacionadas.
    // fetch = FetchType.EAGER carrega as entidades relacionadas imediatamente, em vez de sob demanda.
    // @JoinTable define a tabela de junção para o relacionamento muitos-para-muitos.
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
        name = "tb_users_roles",
        joinColumns = @JoinColumn(name = "user_id"), // Define a coluna de junção referente ao usuário.
        inverseJoinColumns = @JoinColumn(name = "role_id") // Define a coluna de junção referente ao papel (role).
    )
    private Set<Role> roles;

    public UUID getUserId() {
        return this.userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return this.roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public boolean isLoginCorrect(LoginRequest loginRequest, PasswordEncoder passwordEncoder){
        passwordEncoder.matches(loginRequest.password(), this.password);

        return false;
    }
}
