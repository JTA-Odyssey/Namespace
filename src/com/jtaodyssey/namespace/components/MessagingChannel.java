package com.jtaodyssey.namespace.components;

import java.time.LocalDateTime;

public class MessagingChannel implements JTAChannel {
    private static final String CH_PREFIX = "jta";
    private String channelName;
    private String timeOfUpdate;

    public MessagingChannel(String channelName) {
        update();
        setChannelName(channelName);
    }

    public String getTimeOfUpdate() { return timeOfUpdate; }
    public String getChannelName() { return channelName; }

    private void setChannelName(String channelName) { this.channelName = channelName; }
    private void setTimeOfUpdate(String timeOfUpdate) { this.timeOfUpdate = timeOfUpdate; }

    public void update() { setTimeOfUpdate(LocalDateTime.now().toString()); }

    @Override
    public String getType() { return "Messaging Channel"; }

    @Override
    public String getName() { return channelName; }

    @Override
    public String getNameIncludePrefix() { return CH_PREFIX + channelName; }
}
