package com.jtaodyssey.namespace.communication;

import com.jtaodyssey.namespace.components.AuthStatus;
import com.jtaodyssey.namespace.components.JTALogin;
import com.jtaodyssey.namespace.components.JTATextMessage;
import com.jtaodyssey.namespace.components.LoggedInUser;
import com.jtaodyssey.namespace.notification.*;
import com.jtaodyssey.namespace.services.JTAInitializerService;
import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;

import java.io.*;
import java.util.*;

/**
 * PubNubClient is used to initialize the PubNub client API
 * and do all necessary setup to communicate with PubNub
 */
public final class PubNubClient {
    private static PubNubClient pubNubClient = new PubNubClient();
    private String DEV_UUID;
    private String SUB_KEY;
    private String PUB_KEY;
    private PubNub pubNub;

    /**
     * Will read from the application configuration file to load properties
     * including the device DEV_UUID, Publish Key, and Subscribe Key
     */
    private void loadConfig() {
        Properties appProp = new Properties();
        String location = "config.properties";
        try {
            appProp.load(new BufferedInputStream(new FileInputStream(location)));
            String id = appProp.getProperty("deviceUUID");
            if (id.equals("NULL")) {
                id = UUID.randomUUID().toString();
                appProp.setProperty("deviceUUID", id);
                appProp.store(new BufferedOutputStream(new FileOutputStream(location)), null);
            }
            DEV_UUID = id;
            SUB_KEY = appProp.getProperty("pubNubSubscribeKey");
            PUB_KEY = appProp.getProperty("pubNubPublishKey");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes the PubNub API so that it has a secure connection
     * over the web with an app specific Publisher and Subscriber key
     * and a device DEV_UUID
     */
    private PubNubClient()
    {
        loadConfig();
        PNConfiguration pnConfig = new PNConfiguration();
        pnConfig.setUuid(DEV_UUID);
        pnConfig.setPublishKey(PUB_KEY);
        pnConfig.setSubscribeKey(SUB_KEY);
        pnConfig.setSecure(true);
        pubNub = new PubNub(pnConfig);
    }

    public static PubNubClient getInstance() { return pubNubClient; }
    public PubNub getPubNub() { return pubNub; }
}

class MockController implements JTANotificationObserver {
    private static volatile boolean valid = false;
    public MockController() {
        ToUINotifier.getInstance().addObserver(this);
    }

    @Override
    public void update(JTANotification notification) {
        if (notification instanceof IncomingMessageNotification) {
            //todo remove after run in production
            System.out.print("End point controller reached: ");
            System.out.println(((JTATextMessage)notification.readPayload()).toString());
        }
        else if (notification instanceof AuthStatusNotification) {
            boolean res = ((AuthStatus)notification.readPayload()).isValidated();
            if (res) {
                System.out.println("Valid Login System init");
                valid = true;
            }
            else {
                System.out.println("Invalid login, try again");
            }
        }
    }

    public static void main(String[] args) {
        new MockController();
        Scanner scanner = new Scanner(System.in);

//        PubNubActions.getInstance().subscribe(Arrays.asList("A"));
//        PubNubReceiver.getInstance().listen();
        JTAInitializerService.getInstance().prepare();
        JTANotificationRouter.getInstance().init();

        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String pwd = scanner.nextLine();

        FromUINotifier.getInstance().notify(new AuthNotification(new JTALogin(username, pwd)));

        while (!valid) { }

        List<JTATextMessage> msg = LoggedInUser.getInstance().getUser().getMessages("A");
        System.out.println(msg.size());
        for (JTATextMessage m : msg) {
            System.out.println(m);
        }

        System.out.print("Enter your message: ");
        String message = scanner.nextLine();
        while (!message.equals("end")) {
            FromUINotifier.getInstance().notify(new OutgoingMessageNotification(new JTATextMessage(message), "A"));

            System.out.print("Enter your message: ");
            message = scanner.nextLine();
        }
        PubNubClient.getInstance().getPubNub().forceDestroy();
    }
}
