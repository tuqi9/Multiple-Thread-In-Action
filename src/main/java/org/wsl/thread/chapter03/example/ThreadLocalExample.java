package org.wsl.thread.chapter03.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ThreadLocalExample {

    private static final String DB_URL = "mock.db.url";

    private static ThreadLocal<Connection> connectionHolder
            = new ThreadLocal<Connection>() {
                public Connection initialValue(){
                    try {
                        return DriverManager.getConnection(DB_URL);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            };

    public static Connection getConnection(){
        return connectionHolder.get();
    }
}
