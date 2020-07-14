package com.jtaodyssey.namespace.database;

import com.jtaodyssey.namespace.components.BasicUser;
import com.jtaodyssey.namespace.components.JTAUser;
import com.jtaodyssey.namespace.components.LoggedInUser;

import java.io.*;
import java.nio.file.InvalidPathException;
import java.util.Properties;

/**
 * This class will save data to the appropriate location
 */
public class JTADataWriter {
    private volatile static JTADataWriter writer = null;
    private String userDataPath;

    private void initialize() {
        Properties appProp = new Properties();
        String location = "config.properties";
        try {
            appProp.load(new BufferedInputStream(new FileInputStream(location)));
            this.userDataPath = appProp.getProperty("userDataPath");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private JTADataWriter() {
        initialize();
        File root = new File(userDataPath);
        if (!root.isDirectory()) {
            root.mkdir();
        }
    }

    public static JTADataWriter getInstance() {
        if (writer == null) {
            synchronized (JTADataWriter.class) {
                if (writer == null) {
                    writer = new JTADataWriter();
                }
            }
        }
        return writer;
    }

    public void save(JTAUser user) {
        if (!(user instanceof BasicUser)) {
            throw new IllegalArgumentException("Cannot save " + user.getClass()
                    + " user type");
        }

        String loc = userDataPath + user.getFirstName() + "/";
        File userRoot = new File(loc);
        if (!userRoot.isDirectory()) {
            userRoot.mkdir();
        }
        try {
            OutputStream os = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(loc + "user.dat")));
        }
        catch (IOException io) {
            io.printStackTrace();
        }
    }
}
