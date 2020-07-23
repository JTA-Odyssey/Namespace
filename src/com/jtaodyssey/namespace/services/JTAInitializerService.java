package com.jtaodyssey.namespace.services;

import com.jtaodyssey.namespace.communication.PubNubActions;
import com.jtaodyssey.namespace.communication.PubNubReceiver;
import com.jtaodyssey.namespace.components.LoggedInUser;
import com.jtaodyssey.namespace.database.SQLDatabase;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * This service is used to load all user files once they login
 */
public class JTAInitializerService {
    private static volatile JTAInitializerService init = null;
    private static String userStoragePath;
    private static String defaultImgPath;

    private void initialize() {
        Properties appProp = new Properties();
        String location = "config.properties";
        try {
            appProp.load(new BufferedInputStream(new FileInputStream(location)));
            JTAInitializerService.userStoragePath = appProp.getProperty("userStoragePath");
            JTAInitializerService.defaultImgPath = appProp.getProperty("defaultImg");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private JTAInitializerService() { }

    /**
     * Should be called before anything else to make sure file structure
     * is in place
     */
    public void prepare() {
        JTANotificationRouter.getInstance().init();
        initialize();
        buildDirectory(userStoragePath);
        SQLDatabase.getInstance().createTable();
    }

    /**
     * This method will call mkdir() if the directory doesn't already
     * exist
     */
    private void buildDirectory(String location) {
        File root = new File(location);
        if (!root.isDirectory()) {
            root.mkdir();
        }
    }

    public static JTAInitializerService getInstance() {
        if (init == null) {
            synchronized (JTAInitializerService.class) {
                if (init == null) {
                    init = new JTAInitializerService();
                }
            }
        }
        return init;
    }

    /**
     * loads all the files and caches information for use
     */
    public void init() {
        // only load if the user does not already exist in the system

        PubNubReceiver.getInstance().listen();
        JTACachedUser user = LoggedInUser.getInstance().getUser();
        user.loadUserData();
        subscribedToSavedChannels();
    }

    private void subscribedToSavedChannels() {
        Set<String> channelNames =  LoggedInUser.getInstance().getUser().getChannelNames();
        if (channelNames.size() > 0) {
            PubNubActions.getInstance().subscribe(new ArrayList<>(channelNames));
        }
    }
}
