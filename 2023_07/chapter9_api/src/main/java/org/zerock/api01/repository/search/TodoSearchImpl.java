package org.zerock.api01.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.api01.domain.QTodo;
import org.zerock.api01.domain.QTodoFile;
import org.zerock.api01.domain.Todo;
import org.zerock.api01.dto.TodoDTO;

import java.util.List;

@Log4j2
public class TodoSearchImpl extends QuerydslRepositorySupport implements TodoSearch {

    public TodoSearchImpl() {
        super(Todo.class);
    }

    @Override
    public Page<TodoDTO> getList(Pageable pageable) {

        QTodo todo = QTodo.todo;
        QTodoFile todoFile = QTodoFile.todoFile;

        JPQLQuery<Todo> query = from(todo);
        query.leftJoin(todo.imgSet, todoFile);

        // (bi.ord is null or bi.ord = 0)
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.or(todoFile.ord.isNull());
        booleanBuilder.or(todoFile.ord.eq(0));

        query.where(booleanBuilder);
        query.groupBy(todo);

        JPQLQuery<TodoDTO> dtojpqlQuery =
                query.select(Projections.bean(TodoDTO.class,
                        todo.tno,
                        todo.title,
                        todo.dueDate,
                        todo.regDate,
                        todoFile.fileLink.as("frontImage")
                ));



        getQuerydsl().applyPagination(pageable, dtojpqlQuery);

        List<TodoDTO> dtoList = dtojpqlQuery.fetch();

        log.info("---------------------------------");
        log.info(dtoList);

        long count = dtojpqlQuery.fetchCount();

        return new PageImpl<>(dtoList, pageable, count);
    }
}
