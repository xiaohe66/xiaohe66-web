package com.xiaohe66.web.application.todo.result;

import lombok.Data;

import java.util.List;

/**
 * @author xiaohe
 * @since 2021.11.17 15:33
 */
@Data
public class TodoListResult {

    private List<Pool> pools;

    @Data
    public static class Pool {

        private Integer id;
        private String name;

        private List<TodoResult> todos;

    }

    @Data
    public static class TodoResult {

        private Long id;
        private String title;
    }

}
