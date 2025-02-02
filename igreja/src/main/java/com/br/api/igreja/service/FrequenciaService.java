package com.br.api.igreja.service;

import com.br.api.igreja.dto.AttendanceDetailDTO;
import com.br.api.igreja.entities.Aluno;
import com.br.api.igreja.entities.Classe;
import com.br.api.igreja.entities.Frequencia;
import com.br.api.igreja.enums.Presenca;
import com.br.api.igreja.entities.RegistroFrequencia;
import com.br.api.igreja.repositories.AlunoRepository;
import com.br.api.igreja.repositories.ClasseRepository;
import com.br.api.igreja.repositories.FrequenciaRepository;
import com.br.api.igreja.repositories.FrequenciaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class FrequenciaService {

    private final FrequenciaRepository frequenciaRepository;
    private final AlunoRepository alunoRepository;
    private final FrequenciaRepository registroFrequenciaRepository;
    private final ClasseRepository classeRepository;

    public FrequenciaService(FrequenciaRepository frequenciaRepository,
                             AlunoRepository alunoRepository,
                             FrequenciaRepository registroFrequenciaRepository,
                             ClasseRepository classeRepository) {
        this.frequenciaRepository = frequenciaRepository;
        this.alunoRepository = alunoRepository;
        this.registroFrequenciaRepository = registroFrequenciaRepository;
        this.classeRepository = classeRepository;
    }

    /**
     * Recupera a frequência para uma classe e data. Se não existir, cria um novo registro.
     */
    public Frequencia createOrGetFrequencia(Long classeId, LocalDate data) {
        Optional<Frequencia> optional = frequenciaRepository.findByClasseIdAndDate(classeId, data);
        if (optional.isPresent()) {
            return optional.get();
        }
        // Recupera a classe
        Classe classe = classeRepository.findById(classeId)
                .orElseThrow(() -> new RuntimeException("Classe não encontrada"));

        Frequencia frequencia = new Frequencia();
        frequencia.setClasse(classe);
        frequencia.setDate(data);
        frequencia.setTotalBibles(0);
        frequencia.setTotalPresent(0);
        frequencia.setTotalAbsent(0);
        frequencia.setTotalVisitors(0);
        frequencia.setTotalGeneral(0);
        return frequenciaRepository.save(frequencia);
    }

    /**
     * Registra a frequência: recebe a lista de detalhes (para cada aluno),
     * total de bíblias e visitantes.
     */
    public void registerAttendance(Long frequenciaId, List<AttendanceDetailDTO> detalhes, int totalBibles, int totalVisitors) {
        Frequencia frequencia = frequenciaRepository.findById(frequenciaId)
                .orElseThrow(() -> new RuntimeException("Frequência não encontrada"));

        int countPresent = 0;
        int countAbsent = 0;

        // Para cada detalhe, registre a presença ou ausência
        for (AttendanceDetailDTO dto : detalhes) {
            RegistroFrequencia registro = new RegistroFrequencia();
            registro.setFrequencia(frequencia);

            Aluno aluno = alunoRepository.findById(dto.getAlunoId())
                    .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
            registro.setAluno(aluno);
            registro.setPresenca(dto.getPresenca());
            FrequenciaRepository.save(registro);

            if (dto.getPresenca() == Presenca.PRESENTE) {
                countPresent++;
            } else {
                countAbsent++;
            }
        }
        frequencia.setTotalBibles(totalBibles);
        frequencia.setTotalVisitors(totalVisitors);
        frequencia.setTotalPresent(countPresent);
        frequencia.setTotalAbsent(countAbsent);
        // Total Geral: (matriculados presentes + visitantes) - ausentes matriculados.
        frequencia.setTotalGeneral(countPresent + totalVisitors - countAbsent);

        frequenciaRepository.save(frequencia);
    }
}
