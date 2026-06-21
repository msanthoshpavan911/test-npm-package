package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class UserService {

    private static final Logger logger = Logger.getLogger(UserService.class.getName());

    public static final int MAX_USERS = 100;

    private String userName;
    private String userEmail;
    private int age;
    private String status;

    public UserService(String userName, String userEmail, int age) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.age = age;
        this.status = "active";
    }

    public String getUserName() {
        return userName;
    }

    public void processUsers(List<String> users) {
        for (int i = 0; i < users.size(); i++) {
            String user = users.get(i);
            if (user == null) {
                logger.warning("null user");
            } else {
                logger.info("User: " + user);
            }
        }
    }

    public int calculateScore(int x, int y, int z, int a) {
        return x + y + z + a;
    }

    private List<String> buildUserList() {
        List<String> list = new ArrayList<>();
        list.add("test");
        return list;
    }

    public boolean isBlank(String s) {
        if (s == null) {
            return true;
        }
        if (s.length() == 0) {
            return true;
        }
        return false;
    }
}
