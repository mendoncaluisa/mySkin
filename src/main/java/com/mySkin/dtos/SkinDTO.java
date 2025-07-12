package com.mySkin.dtos;

import com.mySkin.entities.Skin;
import lombok.Getter;
import lombok.Setter;


public class SkinDTO {

    @Getter @Setter
    private Long id;

    @Getter @Setter
    private int tipo;

    @Getter @Setter
    private boolean melasma;

    @Getter @Setter
    private boolean rosacea;

    @Getter @Setter
    private boolean sensivel;


    public SkinDTO() {
    }

    public SkinDTO(Long id, int tipo, boolean melasma, boolean rosacea, boolean sensivel) {
        this.id = id;
        this.tipo = tipo;
        this.melasma = melasma;
        this.rosacea = rosacea;
        this.sensivel = sensivel;
    }

    public SkinDTO (Skin entity) {
        this.id = entity.getId();
        this.tipo = entity.getTipo();
        this.melasma = entity.isMelasma();
        this.rosacea = entity.isRosacea();
        this.sensivel = entity.isSensivel();
    }

    public SkinDTO(SkinDTO skin) {
        this.id = skin.id;
        this.tipo = skin.tipo;
        this.melasma = skin.melasma;
        this.rosacea = skin.rosacea;
        this.sensivel = skin.sensivel;
    }
}
