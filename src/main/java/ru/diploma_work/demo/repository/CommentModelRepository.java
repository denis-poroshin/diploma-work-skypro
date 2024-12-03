package ru.diploma_work.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.diploma_work.demo.model.CommentModel;

/**
 * Репозиторий для хранения сущностей "комментарий"
 */
public interface CommentModelRepository extends JpaRepository<CommentModel, Integer> {
}
