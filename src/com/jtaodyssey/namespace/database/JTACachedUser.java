package com.jtaodyssey.namespace.database;

import com.jtaodyssey.namespace.components.JTAUser;

/**
 * This class will save data to the appropriate location
 */
public class JTACachedUser {
    private JTAUser user;
    private String userPath;

    public JTACachedUser(JTAUser user) {
        setUser(user);
        setUserPath(JTAAppUsers.getStoragePath() + user.getFirstName() + "/");
    }

    public JTAUser getUser() {
        return user;
    }

    private void setUser(JTAUser user) {
        this.user = user;
    }

    public String getUserPath() {
        return userPath;
    }

    private void setUserPath(String userPath) {
        this.userPath = userPath;
    }

    //    public void saveUserInfo() {
//        File userRoot = new File(userPath);
//        if (!userRoot.isDirectory()) {
//            userRoot.mkdir();
//        }
//        try {
//            ObjectOutputStream os = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(userPath + "user.dat")));
//            os.writeObject(user);
//        }
//        catch (IOException io) {
//            io.printStackTrace();
//        }
//    }
}
