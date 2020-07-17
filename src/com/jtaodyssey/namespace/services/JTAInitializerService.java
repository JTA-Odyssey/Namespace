package com.jtaodyssey.namespace.services;

import com.jtaodyssey.namespace.communication.PubNubActions;
import com.jtaodyssey.namespace.communication.PubNubReceiver;
import com.jtaodyssey.namespace.notification.JTANotificationRouter;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

/**
 * This service is used to load all user files once they login
 */
public class JTAInitializerService {
    private static volatile JTAInitializerService init = null;
    private static String userStoragePath;

    private void initialize() {
        Properties appProp = new Properties();
        String location = "config.properties";
        try {
            appProp.load(new BufferedInputStream(new FileInputStream(location)));
            JTAInitializerService.userStoragePath = appProp.getProperty("userStoragePath");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private JTAInitializerService() {
//        initialize();
//        File root = new File(userStoragePath);
//        if (!root.isDirectory()) {
//            root.mkdir();
//        }
    }

    /**
     * Should be called before anything else to make sure file structure
     * is in place
     */
    public void prepare() {
        JTANotificationRouter.getInstance().init();
        initialize();
        File root = new File(userStoragePath);
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
    public void init(JTACachedUser user) {
        //todo this service should also make sure the filepaths are correctly set
        PubNubActions.getInstance().subscribe(Arrays.asList("A"));
        PubNubReceiver.getInstance().listen();
        user.loadMessageData();
        // we will want to load contacts and channels in this step as well
    }
}
