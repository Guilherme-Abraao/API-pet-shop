//package com.example.petshop.dto;
//
//import com.example.petshop.base.Usuario;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.stereotype.Service;
//
//import java.util.function.Function;
//import java.util.stream.Collectors;
//
//@Service
//public class UsuarioDTOMapper implements Function<Usuario, UsuarioDTO> {
//
//    @Override
//    public UsuarioDTO apply(Usuario usuario) {
//        return new UsuarioDTO(
//                usuario.getId(),
//                usuario.getNome(),
//                usuario.getEmail(),
//                usuario.getCpf(),
//                usuario.getTelefone(),
//                usuario.getDataNascimento(),
//                usuario.getAuthorities()
//                        .stream()
//                        .map(GrantedAuthority::getAuthority)
//                        .collect(Collectors.toList())
//        );
//    }
//}
