package pl.agh.kis.soa.catering.utils;

import pl.agh.kis.soa.catering.dao.UserDao;


public class LoginService {

    public static String hashPassword(String password) {

        String passwordHash = null;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < password.length(); i++) {
            sb.append((char) ((int) password.charAt(i) + 3));
        }

        passwordHash = String.valueOf(sb);

        return passwordHash;
    }

    public static Boolean checkIfExistPasswordOrLogin(String password, String login) {
        return UserDao.getInstance().getItems().stream()
                .anyMatch(elem -> elem.getPassword().equals(password) || elem.getLogin().equals(login));
    }

}
