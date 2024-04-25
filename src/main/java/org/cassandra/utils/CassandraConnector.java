package org.cassandra.utils;


import com.datastax.oss.driver.api.core.CqlSession;

import java.net.InetSocketAddress;


public class CassandraConnector {

    private CqlSession session;

    public void connect(String node, int port) {
        session = CqlSession.builder()
                .addContactPoint(new InetSocketAddress(node, port))
                .build();
    }

    public void close() {
        session.close();
    }

    public void createKeyspace(String keyspaceName, String replicationStrategy, int replicationFactor) {
        StringBuilder sb =
                new StringBuilder("CREATE KEYSPACE IF NOT EXISTS ")
                        .append(keyspaceName).append(" WITH replication = {")
                        .append("'class':'").append(replicationStrategy)
                        .append("','replication_factor':").append(replicationFactor)
                        .append("};");

        String query = sb.toString();
        session.execute(query);
    }
}
