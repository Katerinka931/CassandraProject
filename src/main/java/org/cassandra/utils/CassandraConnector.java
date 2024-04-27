package org.cassandra.utils;

import lombok.Getter;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.datastax.oss.driver.api.core.CqlSession;

import java.sql.SQLException;


public class CassandraConnector {
    private final static String HOST = "localhost";
    private final static int PORT = 9042;
    private final static String keySpace = "sport";

    private static CassandraConnector INSTANCE;
    private static Cluster cluster;

    @Getter
    private final Session session;

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

    public void closeConnection() throws SQLException {
        session.close();
        cluster.close();
    }
}
