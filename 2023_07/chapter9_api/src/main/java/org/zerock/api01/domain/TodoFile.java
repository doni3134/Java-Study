package org.zerock.api01.domain;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class TodoFile {

    private int ord;
    private String fileLink;

}
