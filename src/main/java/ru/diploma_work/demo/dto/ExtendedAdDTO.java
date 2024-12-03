package ru.diploma_work.demo.dto;

import lombok.Data;

@Data
public class ExtendedAdDTO {
    /**
     * id объявления
     */
    private int pk;
    /**
     * Имя автора объявления
     */
    private String authorFirstName;
    /**
     * Фамилия автора объявления
     */
    private String authorLastName;
    /**
     * Описание объявления
     */
    private String description;
    /**
     * Логин автора объявления
     */
    private String email;
    /**
     * Ссылка на картинку объявления
     */
    private String image;
    /**
     * Телефон автора объявления
     */
    private String phone;
    /**
     * Цена объявления
     */
    private int price;
    /**
     * Заголовок объявления
     */
    private String title;

}
