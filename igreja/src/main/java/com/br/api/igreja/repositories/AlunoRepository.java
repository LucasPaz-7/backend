package com.br.api.igreja.repositories;

import com.br.api.igreja.entities.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    List<Aluno> findByClasseId(Long classeId);
}
