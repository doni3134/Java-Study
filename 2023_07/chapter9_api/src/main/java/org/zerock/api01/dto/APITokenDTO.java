package org.zerock.api01.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class APITokenDTO {

    private String owner;
    private String access;
    private String refresh;

}
