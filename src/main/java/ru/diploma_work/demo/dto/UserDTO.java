package ru.diploma_work.demo.dto;

import lombok.Data;
@Data
public class UserDTO {
    /**
     * id пользователя
     */
    private int id;
    /**
     * Логин пользователя
     */
    private String email;
    /**
     * Имя пользователя
     */
    private String firstName;
    /**
     * Фамилия пользователя
     */
    private String lastName;
    /**
     * Телефон пользователя
     */
    private String phone;
    /**
     * Телефон пользователя
     */
    private Role role;
    /**
     * Ссылка на аватар пользователя
     */
    private String image;

}
