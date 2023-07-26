package org.zerock.api01.domain;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Todo extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tno;

    private String title;

    private String writer;

    private LocalDate dueDate;

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<TodoFile> imgSet = new HashSet<>();

    public void changeTitle(String title){
        this.title = title;
    }

    public void changeDueDate(LocalDate dueDate){
        this.dueDate = dueDate;
    }

    public void addFile(String fileLink){

        TodoFile todoFile = TodoFile.builder()
                .ord(imgSet.size())
                .fileLink(fileLink)
                .build();

        imgSet.add(todoFile);
    }

    public void clearFiles() {
        imgSet.clear();
    }


}
