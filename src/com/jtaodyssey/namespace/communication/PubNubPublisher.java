package com.jtaodyssey.namespace.communication;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.pubnub.api.PubNub;

/**
 * This class should be called when ready to publish a method to pubnub
 */
public final class PubNubPublisher {
    private volatile static PubNubPublisher publisher = null;
    private final PubNub pubNub = PubNubClient.getInstance().getPubNub();

    private PubNubPublisher() {}

    public static PubNubPublisher getInstance()
    {
        if (publisher == null) {
            synchronized (PubNubPublisher.class) {
                if (publisher == null) {
                    publisher = new PubNubPublisher();
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
}
