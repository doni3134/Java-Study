package org.zerock.api01.service;

import org.zerock.api01.domain.Todo;
import org.zerock.api01.dto.PageRequestDTO;
import org.zerock.api01.dto.PageResponseDTO;
import org.zerock.api01.dto.TodoDTO;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
public interface TodoService {

    Long register(TodoDTO todoRegisterDTO);

    TodoDTO read(Long tno);

    Optional<List<String>> getFileLinks(Long tno);

    void remove(Long tno);

    PageResponseDTO<TodoDTO> getList(PageRequestDTO pageRequestDTO);

    void modify(TodoDTO todoDTO);


    default TodoDTO entityToDTO(Todo todo){
        TodoDTO todoDTO = TodoDTO.builder()
                .tno(todo.getTno())
                .title(todo.getTitle())
                .writer(todo.getWriter())
                .s3FilePath(
                        todo.getImgSet().stream()
                                .map(todoFile -> todoFile.getFileLink())
                                .collect(Collectors.toList())
                )
                .build();

        return todoDTO;
    }


    default Todo dtoToEntity(TodoDTO registerDTO){

        Todo todo = Todo.builder()
                .tno(registerDTO.getTno())
                .title(registerDTO.getTitle())
                .writer(registerDTO.getWriter())
                .dueDate(registerDTO.getDueDate())
                .build();

        if(registerDTO.getS3FilePath() != null) {

            registerDTO.getS3FilePath().forEach(s3FileLink -> todo.addFile(s3FileLink));

        }

        return todo;
    }
}
