package com.example.petshop.repository;

import com.example.petshop.base.*;
import com.example.petshop.dto.UsuarioDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    @Query("SELECT u FROM Usuario u WHERE u.email = ?1")
    Optional<Usuario> findUsuarioByEmail(String email);

    @Query("SELECT u FROM Usuario u WHERE u.cpf = ?1")
    Optional<Usuario> findUsuarioByCpf(String cpf);

}
