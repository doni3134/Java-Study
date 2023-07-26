package org.zerock.api01.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoDTO {

    private Long tno;

    private String title;

    private String writer;

    private LocalDate dueDate;

    private MultipartFile[] files;

    private List<String> s3FilePath;

    private LocalDateTime regDate;

    private String frontImage;


}
