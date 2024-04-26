package org.cassandra.utils;

import lombok.Getter;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.datastax.oss.driver.api.core.CqlSession;

import java.sql.SQLException;


public class CassandraConnector {

    private final static String HOST = "127.0.0.1";
    private final static int PORT = 9042;
    private final static String keySpace = "ks";

    private static CassandraConnector INSTANCE;
    private static Cluster cluster;

    @Getter
    private static Session session;

    private CassandraConnector() {
        cluster = Cluster.builder().addContactPoint(HOST)
                .withPort(PORT)
                .build();
        session = cluster.connect(keySpace);
    }

    public static CassandraConnector getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CassandraConnector();
        }
        return INSTANCE;
    }

    public static void closeConnection() throws SQLException {
        session.close();
        cluster.close();
    }
}
