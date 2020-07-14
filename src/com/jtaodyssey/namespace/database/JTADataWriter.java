package com.jtaodyssey.namespace.database;

/**
 * This class will save data to the appropriate location
 */
public class JTADataWriter {
    private volatile static JTADataWriter writer = null;

    private JTADataWriter() {}

    public static JTADataWriter getInstance() {
        if (writer == null) {
            synchronized (JTADataWriter.class) {
                if (writer == null) {
                    writer = new JTADataWriter();
                }
            }
        }
        return writer;
    }
}
