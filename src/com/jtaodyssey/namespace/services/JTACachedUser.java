package com.jtaodyssey.namespace.services;

import com.jtaodyssey.namespace.components.*;

import java.io.*;
import java.util.*;

/**
 * This class will save data to the appropriate location
 */
public class JTACachedUser {
    private static String storageRoot = null;
    private JTAUser user;
    private String userPath;
    private HashMap<String, List<JTATextMessage>> messages; // maps texts to the channel
                                                            // they were found on
    private HashMap<String, JTAChannel> channels;

    private void initStorageRoot() {
        if (storageRoot == null) {
            Properties appProp = new Properties();
            String location = "config.properties";
            try {
                appProp.load(new BufferedInputStream(new FileInputStream(location)));
                JTACachedUser.storageRoot = appProp.getProperty("userStoragePath");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public JTACachedUser(JTAUser user) {
        setUser(user);
        initStorageRoot();
        setUserPath(storageRoot
                + user.getFirstName().toLowerCase()
                + "_" + user.getLastName().toLowerCase() + "/");
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

    public List<JTATextMessage> getMessages(String channel) {
        if (messages.get(channel) == null) {
            return new ArrayList<>();
        }
        return Collections.unmodifiableList(messages.get(channel));
    }

    public List<JTATextMessage> getMessages(JTAChannel channel) {
        return getMessages(channel.getName());
    }

    public Collection<JTAChannel> getChannels() {
        return Collections.unmodifiableCollection(channels.values());
    }

    public static void main(String[] args) throws Exception {
        JTAUser user = new BasicUser("Tucker", "Harvey", "Raft", "tharvey556");
        user.setId(UUID.randomUUID().toString());
        JTAInitializerService.getInstance().init(user);
        JTACachedUser cached = LoggedInUser.getInstance().getUser();

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
        cached.loadMessages();
        HashMap<String, List<JTATextMessage>> map = cached.getMessages();
        for (String channel : map.keySet()) {
            for (JTATextMessage m : map.get(channel)) {
                System.out.println(m);
            }
        }
    }
}
