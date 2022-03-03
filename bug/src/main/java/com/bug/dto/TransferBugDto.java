package com.bug.dto;

import lombok.Data;

@Data
public class TransferBugDto {

    private Long bugId;
    private Long staffId;
    private String accessToken;
}
