package ru.diploma_work.demo.dto;

import lombok.Data;

@Data
public class AdDTO {
    /**
     * id автора объявления
     */
    private int author;
    /**
     * Ссылка на картинку объявления
     */
    private String image;
    /**
     * id объявления
     */
    private int pk;
    /**
     * Цена объявления
     */
    private int price;
    /**
     * Заголовок объявления
     */
    private String title;

}
