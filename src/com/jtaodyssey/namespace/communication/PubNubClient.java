package com.jtaodyssey.namespace.communication;

import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;

/**
 * PubNubClient is used to initialize the PubNub client API
 * and do all necessary setup to communicate with PubNub
 */
public final class PubNubClient {
    private static PubNubClient pubNubClient = new PubNubClient();
    private final String UUID = "3b364090-17b5-4a6e-bd1e-4175c1939f95";
    private final String SUB_KEY = "sub-c-8f8d14d0-bf0c-11ea-a57f-4e41fc185ce6";
    private final String PUB_KEY = "pub-c-a18b5f2b-f067-4ce3-b0b3-51b030b21d0d";
    private PubNub pubNub;

    // todo generate a device UUID
    /**
     * Initializes the PubNub API so that it has a secure connection
     * over the web with an app specific Publisher and Subscriber key
     * and a device UUID
     */
    private PubNubClient()
    {
        PNConfiguration pnConfig = new PNConfiguration();
        pnConfig.setUuid(UUID);
        pnConfig.setPublishKey(PUB_KEY);
        pnConfig.setSubscribeKey(SUB_KEY);
        pnConfig.setSecure(true);
        pubNub = new PubNub(pnConfig);
    }

    public static PubNubClient getInstance() { return pubNubClient; }
    public PubNub getPubNub() { return pubNub; }
}
