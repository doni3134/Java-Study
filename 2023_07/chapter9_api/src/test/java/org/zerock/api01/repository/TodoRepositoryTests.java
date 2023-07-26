package org.zerock.api01.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.api01.domain.Todo;

@SpringBootTest
@Log4j2
public class TodoRepositoryTests {

    @Autowired
    private TodoRepository todoRepository;

    @Test
    public void testQuerydsl() {

//        Pageable pageable = PageRequest.of(0,10, Sort.by("tno").descending());
//
//        todoRepository.getList(pageable);

    }


    @Test
    public void testRead() {

//        Todo todo = todoRepository.getWithFiles(2L).get();
//
//        log.info(todo);

    }

}
