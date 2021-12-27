package com.xiaohe66.web.application.task.result;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * @author xiaohe
 * @since 2021.11.17 15:33
 */
@Data
public class TaskListResult {

    private LocalDate date;
    private List<Item> list;

    @Data
    public static class Item {

        private Long id;
        private String name;

    }

}
