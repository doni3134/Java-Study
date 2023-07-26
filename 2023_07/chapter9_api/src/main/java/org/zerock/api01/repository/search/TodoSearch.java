package org.zerock.api01.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.zerock.api01.domain.Todo;
import org.zerock.api01.dto.TodoDTO;

public interface TodoSearch {

    Page<TodoDTO> getList(Pageable pageable);
}
