package com.jtaodyssey.namespace.communication;

/**
 * PubNubClient is used to initialize the PubNub client API
 * and do all necessary setup to communicate with PubNub
 */
public final class PubNubClient {
    private static PubNubClient pubNubClient = new PubNubClient();

    private PubNubClient() {}

    public static PubNubClient getInstance()
    {
        return pubNubClient;
    }

}
