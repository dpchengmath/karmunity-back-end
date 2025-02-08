package com.karmunity.dto;

import com.karmunity.dto.MemberSummaryDTO;
import com.karmunity.models.KarmaStats;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KarmaEntryResponseDTO {
    private Long id;
    private String karmaAct;
    private String kudos;
    private int karma;
    private MemberSummaryDTO karmaGiver;
    private MemberSummaryDTO karmaReceiver;
    private KarmaStats karmaStats;
}