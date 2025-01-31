package com.br.api.igreja.repositories;

import com.br.api.igreja.entities.Frequencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FrequenciaRepository extends JpaRepository<Frequencia, Long> {
    List<Frequencia> findByData(String data);
}
