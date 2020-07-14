package com.jtaodyssey.namespace.components;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class JTAAppUsers {
    private static volatile JTAAppUsers manager = null;
    private static String userStoragePath;
    private HashMap<JTAUser, JTACachedUser> cachedUsers; // all users while app
                                                        // has been running

    private void initialize() {
        Properties appProp = new Properties();
        String location = "config.properties";
        try {
            appProp.load(new BufferedInputStream(new FileInputStream(location)));
            JTAAppUsers.userStoragePath = appProp.getProperty("userStoragePath");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private JTAAppUsers() {
        initialize();
        File root = new File(userStoragePath);
        if (!root.isDirectory()) {
            root.mkdir();
        }
        this.cachedUsers = new HashMap<>();
    }

    public static JTAAppUsers getInstance() {
        if (manager == null) {
            synchronized (JTAAppUsers.class) {
                if (manager == null) {
                    manager = new JTAAppUsers();
                }
            }
        }
        return manager;
    }

    /**
     * Users are added to this list upon login
     * @return will return null if that user does not exist or the
     */
    public JTACachedUser getUser(JTAUser user) {
        if (cachedUsers.get(user).equals(user)) {
            return cachedUsers.get(user);
        }
        return null;
    }

    // todo login is what promotes a user from being added to this list

    public static String getStoragePath() { return userStoragePath; }
}
