package com.jtaodyssey.namespace.communication;

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
public final class PubNubListener {
   private static volatile PubNubListener listener = null;
   private boolean isListening;
   private PubNub pubNub;
   private Thread t1;

   private PubNubListener() {
       isListening = false;
       pubNub = PubNubClient.getInstance().getPubNub();
       t1 = null;
   }


   public static PubNubListener getInstance() {
       if (listener == null) {
           synchronized (PubNubListener.class) {
               if (listener == null) {
                   listener = new PubNubListener();
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
               pubNub.addListener(new SubscribeCallback() {
                   @Override
                   public void message(@NotNull PubNub pubNub, @NotNull PNMessageResult message) {
                        System.out.println(message.getMessage());
                   }
                   @Override
                   public void status(@NotNull PubNub pubNub, @NotNull PNStatus pnStatus) { }
                   @Override
                   public void presence(@NotNull PubNub pubNub, @NotNull PNPresenceEventResult pnPresenceEventResult) { }
                   @Override
                   public void signal(@NotNull PubNub pubNub, @NotNull PNSignalResult pnSignalResult) { }
                   @Override
                   public void user(@NotNull PubNub pubNub, @NotNull PNUserResult pnUserResult) { }
                   @Override
                   public void space(@NotNull PubNub pubNub, @NotNull PNSpaceResult pnSpaceResult) { }
                   @Override
                   public void membership(@NotNull PubNub pubNub, @NotNull PNMembershipResult pnMembershipResult) { }
                   @Override
                   public void messageAction(@NotNull PubNub pubNub, @NotNull PNMessageActionResult pnMessageActionResult){}
               });
           });
           t1.start();
       }
   }

}
