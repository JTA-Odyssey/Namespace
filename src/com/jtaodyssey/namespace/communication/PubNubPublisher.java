package com.jtaodyssey.namespace.communication;

/**
 * This class should be called when ready to publish a method to pubnub
 */
public final class PubNubPublisher {
    private volatile static PubNubPublisher publisher = null;

    private PubNubPublisher() {}

    public static PubNubPublisher getInstance() {
        if (publisher == null) {
            synchronized (PubNubPublisher.class) {
                if (publisher == null) {
                    publisher = new PubNubPublisher();
                }
            }
        }
        return publisher;
    }

    public void publish(){}

}
