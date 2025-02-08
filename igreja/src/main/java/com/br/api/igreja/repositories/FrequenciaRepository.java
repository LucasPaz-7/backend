package com.br.api.igreja.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface FrequenciaRepository extends JpaRepository<Frequencia, Long> {
    List<Frequencia> findByData(String data);

    Optional<Frequencia> findByClasseIdAndDate(Long classeId, LocalDate data);
}
