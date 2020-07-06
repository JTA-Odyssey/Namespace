package com.jtaodyssey.namespace.communication;

import com.pubnub.api.PubNub;

import java.util.List;

/**
 * This class should be called when ready to publish a method to pubnub
 */
public final class PubNubActions {
    private volatile static PubNubActions publisher = null;
    private final PubNub pubNub = PubNubClient.getInstance().getPubNub();

    private PubNubActions() {}

    public static PubNubActions getInstance()
    {
        if (publisher == null) {
            synchronized (PubNubActions.class) {
                if (publisher == null) {
                    publisher = new PubNubActions();
                }
            }
        }
        return publisher;
    }


    /**
     * Call this method when sending a message to a specified channel
     * @param message Can be any Object that you would like to send
     * @param channel destination of the message
     */
    public void publish(Object message, String channel)
    {
        pubNub.publish().message(message).channel(channel);
    }

    /**
     * Call this method when you want to add a channel to subscribe too
     * @param channels list of all channels you would like to listen too
     */
    public void subscribe(List<String> channels)
    {
        pubNub.subscribe().channels(channels).execute();
    }
}
