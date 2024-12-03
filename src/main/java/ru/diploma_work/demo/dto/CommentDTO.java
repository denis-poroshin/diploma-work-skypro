package ru.diploma_work.demo.dto;

import lombok.Data;

@Data
public class CommentDTO {
    /**
     * id автора комментария
     */
    private int author;
    /**
     * Ссылка на аватар автора комментария
     */
    private String authorImage;
    /**
     * Имя создателя комментария
     */
    private String authorFirstName;
    /**
     * Дата и время создания комментария в миллисекундах с 00:00:00 01.01.1970
     */
    private long createdAt;
    /**
     * id комментария
     */
    private int pk;
    /**
     * Текст комментария
     */
    private String text;

}
