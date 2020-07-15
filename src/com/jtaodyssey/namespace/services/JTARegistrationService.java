package com.jtaodyssey.namespace.services;

import com.jtaodyssey.namespace.database.DBManager;
import com.jtaodyssey.namespace.database.SQLDatabase;

/**
 * This service is used to register users to the db
 */
public class JTARegistrationService {
    private volatile static JTARegistrationService reg = null;
    private DBManager manager;

    private JTARegistrationService() {
        this.manager = SQLDatabase.getInstance();
    }

    public static JTARegistrationService getInstance() {
        if (reg == null) {
            synchronized (AuthenticationService.class) {
                if (reg == null) {
                    reg = new JTARegistrationService();
                }
            }
        }
        return reg;
    }
}
