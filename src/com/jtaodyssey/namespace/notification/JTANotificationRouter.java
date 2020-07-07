package com.jtaodyssey.namespace.notification;

/**
 * This class will facilitate notifications to the appropriate internal
 * component. i.e. OutgoingMessageNotification will be routed the to PubNub
 * client to distribute
 *
 * This router will sit on its own thread and should be able to run async
 */
public final class JTANotificationRouter implements JTANotificationObserver{
    private static final JTANotificationRouter notif = new JTANotificationRouter();

    private JTANotificationRouter() {}
    public static JTANotificationRouter getInstance() { return notif; }

        @Override
    public void update(JTANotification notification) {
        // facilitate the different notification types here.
        // i.e. if its outgoing call the correct observer
        /**
         *
         * if (notification instanceOf IncomingMessageNotifcation) {
         *      if (its a text message) {
         *          // verify that its a valid channel
         *          PubNubClient.publish(message, channel);
         *      }
         *      else if (its a item to be stored local db) {
         *          // direct call to the appropriate DB interface
         *      }
         * }
         *
         */
    }
}
