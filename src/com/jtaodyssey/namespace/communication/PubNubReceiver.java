package com.jtaodyssey.namespace.communication;

import com.google.gson.Gson;
import com.jtaodyssey.namespace.components.JTATextMessage;
import com.jtaodyssey.namespace.notification.IncomingMessageNotification;
import com.jtaodyssey.namespace.notification.JTANotification;
import com.jtaodyssey.namespace.notification.JTANotificationObserver;
import com.jtaodyssey.namespace.notification.JTANotificationSubject;
import com.pubnub.api.PubNub;
import com.pubnub.api.callbacks.SubscribeCallback;
import com.pubnub.api.models.consumer.PNStatus;
import com.pubnub.api.models.consumer.pubsub.PNMessageResult;
import com.pubnub.api.models.consumer.pubsub.PNPresenceEventResult;
import com.pubnub.api.models.consumer.pubsub.PNSignalResult;
import com.pubnub.api.models.consumer.pubsub.message_actions.PNMessageActionResult;
import com.pubnub.api.models.consumer.pubsub.objects.PNMembershipResult;
import com.pubnub.api.models.consumer.pubsub.objects.PNSpaceResult;
import com.pubnub.api.models.consumer.pubsub.objects.PNUserResult;
import org.jetbrains.annotations.NotNull;

/**
 * This object can be used to listen for messages on all channels
 * subscribed too by the client
 */
public final class PubNubReceiver extends JTANotificationSubject {
   private static volatile PubNubReceiver listener = null;
   private boolean isListening;
   private PubNub pubNub;
   private Thread t1;

   private PubNubReceiver() {
       isListening = false;
       pubNub = PubNubClient.getInstance().getPubNub();
       t1 = null;
   }

   public static PubNubReceiver getInstance() {
       if (listener == null) {
           synchronized (PubNubReceiver.class) {
               if (listener == null) {
                   listener = new PubNubReceiver();
               }
           }
       }
       return listener;
   }

    /**
     * This method will invoke the client to listen for all messages
     * occurring on their channel
     */
   public void listen() {
       if (!isListening) {
           t1 = new Thread(()-> {
               pubNub.addListener(new PubNubListener() {
                   @Override
                   public void message(@NotNull PubNub pubNub, @NotNull PNMessageResult pnMessageResult) {
                       //JTATextMessage message = new Gson().fromJson(pnMessageResult.getMessage().toString(), JTATextMessage.class);
                       JTATextMessage message = new JTATextMessage("Incoming Message");
                       PubNubReceiver.this.notify(new IncomingMessageNotification(message));
                   }
               });
           });
           t1.start();
           isListening = true;
       }
   }

    @Override
    public void notify(JTANotification notification) {
        for (JTANotificationObserver o : super.observers) {
            o.update(notification);
        }
    }
}
