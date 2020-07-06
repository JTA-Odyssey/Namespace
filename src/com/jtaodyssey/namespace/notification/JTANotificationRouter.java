package com.jtaodyssey.namespace.notification;

/**
 * This class will facilitate notifications to the appropriate internal
 * component. i.e. OutgoingMessageNotification will be routed the to PubNub
 * client to distribute
 *
 * This router will sit on its own thread and should be able to run async
 */
public final class JTANotificationRouter implements Runnable{
    @Override
    public void run() {

    }
}
