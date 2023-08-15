package com.example.petshop.repository;
import com.example.petshop.base.Administrador;
import com.example.petshop.base.Cliente;
import com.example.petshop.base.Funcionario;
import com.example.petshop.base.Usuario;
import com.example.petshop.base.*;
import com.example.petshop.base.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    @Query("SELECT u FROM Usuario u WHERE u.email = ?1")
    Optional<Usuario> findUsuarioByEmail(String email);

    @Query("SELECT u FROM Usuario u WHERE u.cpf = ?1")
    Optional<Usuario> findUsuarioByCpf(String cpf);

    @Query("SELECT cli FROM Cliente cli WHERE cli.email = ?1")
    Optional<Cliente> findClienteByEmail(String email);

    @Query("SELECT adm FROM Administrador adm WHERE adm.email = ?1")
    Optional<Administrador> findAdministradorByEmail(String email);
}
