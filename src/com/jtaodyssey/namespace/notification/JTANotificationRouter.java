package com.jtaodyssey.namespace.notification;

import com.jtaodyssey.namespace.communication.PubNubActions;
import com.jtaodyssey.namespace.communication.PubNubReceiver;
import com.jtaodyssey.namespace.components.*;
import com.jtaodyssey.namespace.services.AuthenticationService;
import com.jtaodyssey.namespace.services.JTAInitializerService;
import com.jtaodyssey.namespace.services.JTARegistrationService;

/**
 * This class will facilitate notifications to the appropriate internal
 * component. i.e. OutgoingMessageNotification will be routed the to PubNub
 * client to distribute
 *
 * This router will sit on its own thread and should be able to run async
 */
public final class JTANotificationRouter implements JTANotificationObserver{
    private static final JTANotificationRouter notif = new JTANotificationRouter(); // happening at compile so call an init
    private final JTANotificationSubject toUINotifier = ToUINotifier.getInstance();

    /**
     * This ctor will observe the component that the UI writes notifications too
     */
    private JTANotificationRouter() { }

    public void init() {
        FromUINotifier.getInstance().addObserver(this);
        PubNubReceiver.getInstance().addObserver(this);
    }

    public static JTANotificationRouter getInstance() { return notif; }

    @Override
    public void update(JTANotification notification) {
        if (notification instanceof OutgoingMessageNotification) {
            OutgoingMessageNotification out = (OutgoingMessageNotification)notification;
            // todo remove after debugging
            PubNubActions.getInstance().publish(out.readPayload(), out.getChannel());
//            System.out.println("Message processed going out of Router: ");
//            System.out.print((JTATextMessage)out.readPayload());
        }
        else if (notification instanceof IncomingMessageNotification) {
            // send to the UI
            toUINotifier.notify(notification);
            LoggedInUser.getInstance().getUser().record(((IncomingMessageNotification) notification).getChannelInfo(),
                    ((JTATextMessage)notification.readPayload()));

            // todo remove after debug
//            IncomingMessageNotification msg = (IncomingMessageNotification)notification;
//            System.out.println("Message processed coming in Router: ");
//            System.out.print(msg.readPayload());
        }
        else if (notification instanceof AuthNotification) {
            actionOnLogin((JTALogin)notification.readPayload());
            System.out.println(notification.readPayload());
        }
        else if (notification instanceof RegistrationNotification) {
            handleRegistration((JTARegistration)notification.readPayload());
        }
    }

    private void handleRegistration(JTARegistration registration) {
        String authMsg = "";
        boolean isValidated = false;
        if (JTARegistrationService.getInstance().register(registration)) {
            authMsg = "Registration successful";
            isValidated = true;
        }
        else {
            authMsg = "Username was not unique";
        }
        toUINotifier.notify(new AuthNotification( new AuthStatus(authMsg, isValidated)));
    }

    private void actionOnLogin(JTALogin login) {
        String authMsg = "";
        boolean isValidated = false;
        if (AuthenticationService.getInstance().authorize(login)) {
            JTAInitializerService.getInstance().init();
            authMsg = "username and password authorized";
            isValidated = true;
        }
        else {
            authMsg = "Invalid username/password";
        }
        AuthStatus status = new AuthStatus(authMsg, isValidated);
        JTANotification notif = new AuthStatusNotification(status);
        toUINotifier.notify(notif);
    }
}
