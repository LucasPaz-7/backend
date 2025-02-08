package com.br.api.igreja.entities;

import com.br.api.igreja.enums.Presenca;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Frequencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "classe_id", nullable = false)
    private Classe classe;

    private LocalDate date;
    private int totalBibles;
    private int totalVisitors;
    private int totalPresent;
    private int totalAbsent;
    private int totalGeneral;

    @ElementCollection
    @CollectionTable(name = "presencas", joinColumns = @JoinColumn(name = "frequencia_id"))
    @MapKeyColumn(name = "aluno_id")
    @Column(name = "presenca")
    @Enumerated(EnumType.STRING)
    private Map<Long, Presenca> presencas = new HashMap<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getTotalBibles() {
        return totalBibles;
    }

    public void setTotalBibles(int totalBibles) {
        this.totalBibles = totalBibles;
    }

    public int getTotalVisitors() {
        return totalVisitors;
    }

    public void setTotalVisitors(int totalVisitors) {
        this.totalVisitors = totalVisitors;
    }

    public int getTotalPresent() {
        return totalPresent;
    }

    public void setTotalPresent(int totalPresent) {
        this.totalPresent = totalPresent;
    }

    public int getTotalAbsent() {
        return totalAbsent;
    }

    public void setTotalAbsent(int totalAbsent) {
        this.totalAbsent = totalAbsent;
    }

    public int getTotalGeneral() {
        return totalGeneral;
    }

    public void setTotalGeneral(int totalGeneral) {
        this.totalGeneral = totalGeneral;
    }

    public Map<Long, Presenca> getPresencas() {
        return presencas;
    }

    public void setPresencas(Map<Long, Presenca> presencas) {
        this.presencas = presencas;
    }
}
