package com.mySkin.services;

import com.mySkin.dtos.SkinDTO;
import com.mySkin.entities.Skin;
import com.mySkin.repository.SkinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class SkinService {

    @Autowired
    private SkinRepository skinRepository;

    @Transactional(readOnly = true)
    public Page<SkinDTO> findAll(Pageable pageable) {
        Page<Skin> list = skinRepository.findAll(pageable);
        return list.map(s -> new SkinDTO(s));
    }

    @Transactional(readOnly = true)
    public SkinDTO findById(Long id) {
        Optional<Skin> skin = skinRepository.findById(id);

        return new SkinDTO(skin.get());
    }

    @Transactional
    public SkinDTO insert(SkinDTO skinDTO) {
        Skin entity = new Skin();

        entity.setTipo(skinDTO.getTipo());
        entity.setMelasma(skinDTO.isMelasma());
        entity.setRosacea(skinDTO.isRosacea());
        entity.setSensivel(skinDTO.isSensivel());
        skinRepository.save(entity);

        return new SkinDTO(entity);

    }

    @Transactional
    public SkinDTO update(SkinDTO skinDTO, Long id) {
        Skin entity = skinRepository.getReferenceById(id);

        entity.setTipo(skinDTO.getTipo());
        entity.setMelasma(skinDTO.isMelasma());
        entity.setRosacea(skinDTO.isRosacea());
        entity.setSensivel(skinDTO.isSensivel());
        entity = skinRepository.save(entity);

        return new SkinDTO(entity);
    }

    @Transactional
    public void delete(Long id) {
        skinRepository.deleteById(id);
    }

    @Transactional
    public void deleteAllSkins() {
        skinRepository.deleteAll();
    }
}
