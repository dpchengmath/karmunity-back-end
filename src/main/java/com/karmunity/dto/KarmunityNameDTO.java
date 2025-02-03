package com.karmunity.dto;

import com.karmunity.models.Karmunity;
import lombok.Getter;

@Getter
public class KarmunityNameDTO {
    private Long id;
    private String karmunityName;

    public KarmunityNameDTO(Karmunity karmunity) {
        this.id = karmunity.getId();
        this.karmunityName = karmunity.getKarmunityName();
    }
}