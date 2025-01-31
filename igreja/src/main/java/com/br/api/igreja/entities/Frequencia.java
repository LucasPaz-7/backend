package com.br.api.igreja.entities;

import jakarta.persistence.*;


import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "frequencias")
public class Frequencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate data; // Data da EBD

    @ManyToOne
    @JoinColumn(name = "classe_id", nullable = false)
    private Classe classe;

    @OneToMany(mappedBy = "frequencia", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RegistroFrequencia> registros;

    @Column(nullable = false)
    private int visitantes;

    @Column(nullable = false)
    private int biblias;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public List<RegistroFrequencia> getRegistros() {
        return registros;
    }

    public void setRegistros(List<RegistroFrequencia> registros) {
        this.registros = registros;
    }

    public int getVisitantes() {
        return visitantes;
    }

    public void setVisitantes(int visitantes) {
        this.visitantes = visitantes;
    }

    public int getBiblias() {
        return biblias;
    }

    public void setBiblias(int biblias) {
        this.biblias = biblias;
    }
}

