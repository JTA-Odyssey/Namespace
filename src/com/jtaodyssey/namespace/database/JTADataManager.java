package com.jtaodyssey.namespace.database;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class JTADataManager {
    private static volatile JTADataManager manager = null;
    private static String userStoragePath;
    private HashMap<String, JTADataWriter> writers;

    private void initialize() {
        Properties appProp = new Properties();
        String location = "config.properties";
        try {
            appProp.load(new BufferedInputStream(new FileInputStream(location)));
            JTADataManager.userStoragePath = appProp.getProperty("userStoragePath");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private JTADataManager() {
        initialize();
        File root = new File(userStoragePath);
        if (!root.isDirectory()) {
            root.mkdir();
        }
        writers = new HashMap<>();
    }

    public static JTADataManager getInstance() {
        if (manager == null) {
            synchronized (JTADataManager.class) {
                if (manager == null) {
                    manager = new JTADataManager();
                }
            }
        }
        return manager;
    }
}
