package ru.diploma_work.demo.utils;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import ru.diploma_work.demo.dto.Role;
import ru.diploma_work.demo.model.AdModel;
import ru.diploma_work.demo.model.CommentModel;
import ru.diploma_work.demo.model.UserModel;
import ru.diploma_work.demo.service.AdService;
import ru.diploma_work.demo.service.CommentService;
import ru.diploma_work.demo.service.UserService;

/**
 * Вспомогательный класс, содержащий методы для проверки отсутствия у зарегистрированного пользователя прав доступа к функциям
 * удаления и редактирования объявлений и комментариев
 */
@Component
public class AuthUtils {

    private final UserService userService;
    private final AdService adService;
    private final CommentService commentService;

    public AuthUtils(UserService userService, AdService adService, CommentService commentService) {
        this.userService = userService;
        this.adService = adService;
        this.commentService = commentService;
    }

    /**
     * Проверяет, запрещен ли зарегистрированному пользователю доступ к удалению и редактированию объявления по идентификатору
     * объявления
     * @param adId - идентификатор объявления, целое положительное число
     * @param authentication - часть контекста приложения, содержащая данные об аутентификации пользователя, такие как
     *                       логин и пароль
     * @return - true, если доступ запрещен false, если разрешен
     */
    public boolean isAccessToAdForbidden(int adId, Authentication authentication) {
        UserModel user = userService.findUserByUserName(authentication.getName());
        if (user.getRole().equals(Role.ADMIN)) {
            return false;
        } else {
            AdModel adModel = adService.findAdById(adId);
            return !adModel.getUser().getEmail().equals(authentication.getName());
        }
    }
    /**
     * Проверяет, запрещен ли зарегистрированному пользователю доступ к удалению и редактированию комментария по идентификатору
     * комментария
     * @param commentId - идентификатор комментария, целое положительное число
     * @param authentication - часть контекста приложения, содержащая данные об аутентификации пользователя, такие как
     *                       логин и пароль
     * @return - true, если доступ запрещен false, если разрешен
     */
    public boolean isAccessToCommentForbidden(int commentId, Authentication authentication) {
        UserModel user = userService.findUserByUserName(authentication.getName());
        if (user.getRole().equals(Role.ADMIN)){
            return false;
        } else {
            CommentModel commentModel = commentService.findById(commentId);
            return !commentModel.getUser().getEmail().equals(authentication.getName());
        }
    }
}

