package edu.miu.cs.cs425.eregistrar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TranscriptResponseDto {
    private Long transcriptId;
    private String degreeTitle;
}
