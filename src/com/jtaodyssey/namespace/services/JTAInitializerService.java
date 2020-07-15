package com.jtaodyssey.namespace.services;

import com.jtaodyssey.namespace.components.JTAUser;
import com.jtaodyssey.namespace.components.LoggedInUser;

/**
 * This service is used to load all user files once they login
 */
public class JTAInitializerService {
    private static volatile JTAInitializerService init = null;

    private JTAInitializerService() {}

    public static JTAInitializerService getInstance() {
        if (init == null) {
            synchronized (JTAInitializerService.class) {
                if (init == null) {
                    init = new JTAInitializerService();
                }
            }
        }
        return init;
    }

    /**
     * loads all the files and caches information for use
     */
    public void load(JTAUser user) {
        JTACachedUser loadedUser = JTAAppUsers.getInstance().getUser(user);
        loadedUser.loadMessages();
        LoggedInUser.getInstance().setUser(loadedUser);
        // we will want to load contacts and channels in this step as well
    }
}
