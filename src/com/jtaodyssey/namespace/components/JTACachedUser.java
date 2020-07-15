package com.jtaodyssey.namespace.components;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

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
        setUserPath(JTAAppUsers.getStoragePath()
                + user.getFirstName().toLowerCase()
                + "_" + user.getLastName().toLowerCase() + "/");
        this.messages = new HashMap<>();
        this.channels = new HashMap<>();
        loadMessages();
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

    /**
     * because this runs on a thread. if another thread tries to read before
     * it closes there will be a problem
     */
    private void persistMessages() {
        File userRoot = new File(userPath);
        new Thread(()-> {
            if (!userRoot.isDirectory()) {
                userRoot.mkdir();
            }
            try {
                ObjectOutputStream os = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(userPath + "messages.dat")));
                os.writeObject(messages);
                os.flush();
                os.close();
            } catch (IOException io) {
                io.printStackTrace();
            }
        }).start();
    }

    void loadMessages() {
        // todo will it be necessary to thread this
        File fin = new File(userPath + "messages.dat");
        if (fin.exists()) {
            try {
                ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(userPath + "messages.dat")));
                messages = (HashMap<String, List<JTATextMessage>>)in.readObject();
                in.close();
            }
            catch (IOException io) {
                io.printStackTrace();
            }
            catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    HashMap<String, List<JTATextMessage>> getMessages() { return messages; }


    public static void main(String[] args) throws Exception {
        JTAAppUsers.getInstance();
        JTAUser user = new BasicUser("Tucker", "Harvey", "Raft", "tharvey556");
        JTACachedUser cached = new JTACachedUser(user);

        System.out.print("Message: ");
        Scanner scanner = new Scanner(System.in);

        String message = scanner.nextLine();
        while (!message.equals("done")) {
            cached.record(new MessagingChannel("A"), new JTATextMessage(message));

            System.out.print("Message: ");
            message = scanner.nextLine();
        }
        cached.persistMessages();

        Thread.sleep(500);

        cached = new JTACachedUser(user);
        HashMap<String, List<JTATextMessage>> map = cached.getMessages();
        for (String channel : map.keySet()) {
            for (JTATextMessage m : map.get(channel)) {
                System.out.println(m);
            }
        }
    }
}
