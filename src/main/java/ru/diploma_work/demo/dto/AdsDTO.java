package ru.diploma_work.demo.dto;

import lombok.Data;

import java.util.List;

@Data
public class AdsDTO {
    /**
     * Общее количество объявлений
     */
    private int count;
    /**
     * Лист объявлений
     */
    private List<AdDTO> results;
}
