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
    private JTAUser user;
    private String userPath;

    public JTADataWriter(JTAUser user) {
        this.user = user;
        this.userPath = JTADataManager.getStoragePath() + user.getFirstName() + "/";
        saveUserInfo();
    }

    public void saveUserInfo() {
        File userRoot = new File(userPath);
        if (!userRoot.isDirectory()) {
            userRoot.mkdir();
        }
        try {
            ObjectOutputStream os = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(userPath + "user.dat")));
            os.writeObject(user);
        }
        catch (IOException io) {
            io.printStackTrace();
        }
    }
}
