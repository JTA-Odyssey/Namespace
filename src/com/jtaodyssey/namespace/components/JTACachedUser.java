package com.jtaodyssey.namespace.components;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This class will save data to the appropriate location
 */
public class JTACachedUser {
    private JTAUser user;
    private String userPath;
    private HashMap<String, List<JTATextMessage>> messages; // maps texts to the channel
                                                            // they were found on
    private HashMap<String, JTAChannel> channels;

    public JTACachedUser(JTAUser user) {
        setUser(user);
        setUserPath(JTAAppUsers.getStoragePath() + user.getFirstName() + "/");
        this.messages = new HashMap<>();
        this.channels = new HashMap<>();
    }

    public JTAUser getUser() {
        return user;
    }

    private void setUser(JTAUser user) {
        this.user = user;
    }

    public String getUserPath() {
        return userPath;
    }

    private void setUserPath(String userPath) {
        this.userPath = userPath;
    }

    public void record(JTAChannel channel, JTATextMessage message) {
        if (messages.get(channel.getName()) == null) {
            messages.put(channel.getName(), new ArrayList<>());
        }
        messages.get(channel.getName()).add(message);
        persistMessages();
    }

    private void persistMessages() {
        File userRoot = new File(userPath);
        new Thread(()-> {
            if (!userRoot.isDirectory()) {
                userRoot.mkdir();
            }
            try {
                ObjectOutputStream os = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(userPath + "messages.dat")));
                os.writeObject(user);
                os.flush();
                os.close();
            } catch (IOException io) {
                io.printStackTrace();
            }
        }).start();
    }

    //    public void saveUserInfo() {
//        File userRoot = new File(userPath);
//        if (!userRoot.isDirectory()) {
//            userRoot.mkdir();
//        }
//        try {
//            ObjectOutputStream os = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(userPath + "user.dat")));
//            os.writeObject(user);
//        }
//        catch (IOException io) {
//            io.printStackTrace();
//        }
//    }
}
