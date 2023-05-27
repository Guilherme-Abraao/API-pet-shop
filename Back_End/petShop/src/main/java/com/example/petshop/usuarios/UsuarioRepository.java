package com.example.petshop.usuarios;

<<<<<<< HEAD:Back_End/petShop/src/main/java/com/example/petshop/usuarios/UsuarioRepository.java
=======
import com.example.petshop.base.Administrador;
import com.example.petshop.base.Cliente;
import com.example.petshop.base.Funcionario;
import com.example.petshop.base.Usuario;
>>>>>>> parent of 67731d8... Merge pull request #48 from gilmarUFG/guilherme:Back_End/petShop/src/main/java/com/example/petshop/repository/UsuarioRepository.java
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    @Query("SELECT u FROM Usuario u WHERE u.email = ?1")
    Optional<Usuario> findUsuarioByEmail(String email);
<<<<<<< HEAD:Back_End/petShop/src/main/java/com/example/petshop/usuarios/UsuarioRepository.java
=======

    @Query("SELECT f FROM Funcionario f WHERE f.email = ?1")
    Optional<Funcionario> findFuncionarioByEmail(String email);

    @Query("SELECT cli FROM Cliente cli WHERE cli.email = ?1")
    Optional<Cliente> findClienteByEmail(String email);

    @Query("SELECT adm FROM Administrador adm WHERE adm.email = ?1")
    Optional<Administrador> findAdministradorByEmail(String email);
>>>>>>> parent of 67731d8... Merge pull request #48 from gilmarUFG/guilherme:Back_End/petShop/src/main/java/com/example/petshop/repository/UsuarioRepository.java
}
