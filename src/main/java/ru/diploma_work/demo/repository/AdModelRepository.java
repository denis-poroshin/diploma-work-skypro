package ru.diploma_work.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.diploma_work.demo.model.AdModel;

/**
 * Репозиторий для хранения сущностей "объявление"
 */
public interface AdModelRepository extends JpaRepository<AdModel, Integer> {
}
