package ru.diploma_work.demo.dto;

import lombok.Data;

import java.util.List;

@Data
public class CommentsDTO {
    /**
     * Общее количество комментариев
     */
    private int count;
    /**
     * Лист комментариев
     */
    private List<CommentDTO> results;
}
