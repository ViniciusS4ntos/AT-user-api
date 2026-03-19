package com.vinicius.user_api.insfrastructure.repository;


import com.vinicius.user_api.insfrastructure.entity.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface    UsuarioRepository extends JpaRepository<Usuario, Long> {

    // verificar se o email ja existe
    boolean existsByEmail(String email);

    Optional<Usuario> findByEmail(String email);

    @Transactional
    Optional<Void> deleteByEmail(String email);

}
