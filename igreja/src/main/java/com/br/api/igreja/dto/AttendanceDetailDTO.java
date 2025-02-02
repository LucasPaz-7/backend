package com.br.api.igreja.dto;

import com.br.api.igreja.enums.Presenca;

public class AttendanceDetailDTO {
    private Long alunoId;
    private Presenca presenca;

    public Long getAlunoId() {
        return alunoId;
    }
    public void setAlunoId(Long alunoId) {
        this.alunoId = alunoId;
    }
    public Presenca getPresenca() {
        return presenca;
    }
    public void setPresenca(Presenca presenca) {
        this.presenca = presenca;
    }
}
