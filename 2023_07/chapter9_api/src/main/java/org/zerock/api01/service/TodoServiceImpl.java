package org.zerock.api01.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.zerock.api01.domain.Todo;
import org.zerock.api01.dto.PageRequestDTO;
import org.zerock.api01.dto.PageResponseDTO;
import org.zerock.api01.dto.TodoDTO;
import org.zerock.api01.repository.TodoRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Log4j2
@RequiredArgsConstructor
public class TodoServiceImpl implements  TodoService{

    private final TodoRepository todoRepository;

    @Override
    public Long register(TodoDTO todoRegisterDTO) {
        log.info("TodoService register.......");

        log.info(todoRegisterDTO);

        Todo todo = dtoToEntity(todoRegisterDTO);

        log.info(todo);

        Long tno = todoRepository.save(todo).getTno();

        return tno;
    }

    @Override
    public TodoDTO read(Long tno) {

        Optional<Todo> result = todoRepository.getWithFiles(tno);

        Todo todo = result.orElseThrow();

        TodoDTO todoDTO = entityToDTO(todo);

        return todoDTO;
    }

    @Override
    public Optional<List<String>> getFileLinks(Long tno) {

        Optional<Todo> result = todoRepository.getWithFiles(tno);

        Todo todo = result.get();

        if(todo.getImgSet() == null ) {
            return Optional.empty();
        }

        List<String> fileNames  = todo.getImgSet().stream().map(todoFile -> {
            String fileLink = todoFile.getFileLink();

            return fileLink.substring(fileLink.lastIndexOf("/")+1);
        }).collect(Collectors.toList());

        return Optional.of(fileNames);

    }

    @Override
    public void remove(Long tno) {

        log.info("remove todo........." + tno);

        todoRepository.deleteById(tno);


    }

    @Override
    public PageResponseDTO<TodoDTO> getList(PageRequestDTO pageRequestDTO) {

        Pageable pageable = pageRequestDTO.getPageable("tno");

        Page<TodoDTO> result = todoRepository.getList(pageable);

        return PageResponseDTO.<TodoDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(result.getContent())
                .total((int)result.getTotalElements())
                .build();
    }

    @Override
    public void modify(TodoDTO todoModifyDTO) {

        Optional<Todo> result = todoRepository.getWithFiles(todoModifyDTO.getTno());

        Todo todo = result.orElseThrow();

        todo.changeTitle(todoModifyDTO.getTitle());
        todo.changeDueDate(todoModifyDTO.getDueDate());

        todo.clearFiles();

        List<String> s3FileLinks = todoModifyDTO.getS3FilePath();

        if(s3FileLinks != null && s3FileLinks.size() > 0){

            s3FileLinks.forEach(fileLink -> todo.addFile(fileLink));

        }

        todoRepository.save(todo);
    }
}














