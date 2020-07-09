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
import jdk.jshell.spi.ExecutionControl;
import org.jetbrains.annotations.NotNull;

/**
 * This is simply a dummy to house the methods that i do not want defined
 * right now
 */
public abstract class PubNubListener extends SubscribeCallback {
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
    public void messageAction(@NotNull PubNub pubNub, @NotNull PNMessageActionResult pnMessageActionResult) { }
}
