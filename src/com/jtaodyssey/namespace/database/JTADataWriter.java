package com.jtaodyssey.namespace.database;

import com.jtaodyssey.namespace.components.BasicUser;
import com.jtaodyssey.namespace.components.JTAUser;

import java.io.*;
import java.util.Properties;
import java.util.UUID;

/**
 * This class will save data to the appropriate location
 */
public class JTADataWriter {
//    private static String userStoragePath;
//
//    private void initialize() {
//        Properties appProp = new Properties();
//        String location = "config.properties";
//        try {
//            appProp.load(new BufferedInputStream(new FileInputStream(location)));
//            JTADataWriter.userStoragePath = appProp.getProperty("userStoragePath");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private JTADataWriter() {
//        initialize();
//        File root = new File(userStoragePath);
//        if (!root.isDirectory()) {
//            root.mkdir();
//        }
//    }
//
//    public void save(JTAUser user) {
//        if (!(user instanceof BasicUser)) {
//            throw new IllegalArgumentException("Cannot save " + user.getClass()
//                    + " user type");
//        }
//
//        String loc = userStoragePath + user.getFirstName() + "/";
//        File userRoot = new File(loc);
//        if (!userRoot.isDirectory()) {
//            userRoot.mkdir();
//        }
//        try {
//            ObjectOutputStream os = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(loc + "user.dat")));
//            os.writeObject(user);
//        }
//        catch (IOException io) {
//            io.printStackTrace();
//        }
//    }
}
