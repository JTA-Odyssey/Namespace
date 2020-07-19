package com.jtaodyssey.namespace.components;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

public class MessagingChannel implements JTAChannel {
    private static final String CH_PREFIX = "jta";
    private String channelName;
    private String timeOfUpdate;
    private Set<JTAUser> subscribers;

    public MessagingChannel(String channelName) {
        update();
        setChannelName(channelName);
    }

    public String getTimeOfUpdate() { return timeOfUpdate; }
    public String getChannelName() { return channelName; }

    public void addSubscriber(JTAUser user) { subscribers.add(user); }
    public Set<JTAUser> subscribers() {
        return Collections.unmodifiableSet(subscribers);
    }

    private void setChannelName(String channelName) { this.channelName = channelName; }
    private void setTimeOfUpdate(String timeOfUpdate) { this.timeOfUpdate = timeOfUpdate; }

    public void update() { setTimeOfUpdate(LocalDateTime.now().toString()); }

    @Override
    public String getType() { return "Messaging Channel"; }

    @Override
    public String getName() { return channelName; }

    @Override
    public String getNameIncludePrefix() { return CH_PREFIX + channelName; }

    @Override
    public int hashCode() {
        return Objects.hash(CH_PREFIX, channelName);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        else if (!(obj instanceof MessagingChannel)) {
            return false;
        }
        else {
            return channelName.equals(((MessagingChannel) obj).channelName);
        }
    }

    @Override
    public String toString() {
        return getName();
    }
}
