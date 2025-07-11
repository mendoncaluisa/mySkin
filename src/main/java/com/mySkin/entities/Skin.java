package com.mySkin.entities;

import com.mySkin.dtos.SkinDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_skin")
public class Skin {

    @Getter @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter @Setter
    private int tipo;

    @Getter @Setter
    private boolean melasma;

    @Getter @Setter
    private boolean rosacea;

    @Getter @Setter
    private boolean sensivel;

    public Skin(Long id, int tipo, boolean melasma, boolean rosacea, boolean sensivel) {
        this.id = id;
        this.tipo = tipo;
        this.melasma = melasma;
        this.rosacea = rosacea;
        this.sensivel = sensivel;
    }

    public Skin(SkinDTO skinDTO) {
        this.id = skinDTO.getId();
        this.tipo = skinDTO.getTipo();
        this.melasma = skinDTO.isMelasma();
        this.rosacea = skinDTO.isRosacea();
        this.sensivel = skinDTO.isSensivel();
    }

    public Skin() {
    }

    @Override
    public String toString() {
        return "Skin{" +
                "id=" + id +
                ", tipo=" + tipo +
                ", melasma=" + melasma +
                ", rosacea=" + rosacea +
                ", sensivel=" + sensivel +
                '}';
    }
}
