package org.cassandra.utils;

import lombok.Getter;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;


public class CassandraConnector {
    private final static String HOST = "localhost";
    private final static int PORT = 9042;
    @Getter
    private final static String keySpace = "sport";

    private static CassandraConnector INSTANCE;
    private static Cluster cluster;

    @Getter
    private final Session session;

    private CassandraConnector() {
        cluster = Cluster.builder().addContactPoint(HOST)
                .withPort(PORT)
                .build();

        session = cluster.connect();
        session.execute("CREATE KEYSPACE IF NOT EXISTS " + keySpace + " WITH replication = {'class':'SimpleStrategy', 'replication_factor':1};");
        session.execute("USE " + keySpace);
    }

    public static CassandraConnector getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CassandraConnector();
        }
        return INSTANCE;
    }

    public void closeConnection() {
        session.close();
        cluster.close();
    }
}
