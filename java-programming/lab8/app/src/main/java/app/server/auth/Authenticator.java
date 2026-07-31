package app.server.auth;

import core.dao.UserDao;
import core.objects.User;
import core.utils.MD5Util;

/**
 * Регистрация и авторизация через UserDao и MD5.
 */
public class Authenticator {
    /** Регистрация нового пользователя. Возвращает true, если создан. */
    public static boolean register(UserDao userDao, String login, String password) {
        String hash = MD5Util.hash(password);
        return userDao.create(new User(login, hash));
    }

    /** Проверка логина/пароля. */
    public static boolean login(UserDao userDao, String login, String password) {
        return userDao.findByLogin(login)
                .filter(u -> u.getPasswordHash().equals(MD5Util.hash(password)))
                .isPresent();
    }
}
