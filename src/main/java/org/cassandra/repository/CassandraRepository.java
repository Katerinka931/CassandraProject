package org.cassandra.repository;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.datastax.oss.driver.api.core.CqlSession;
import org.cassandra.entities.Team;

public class CassandraRepository {

    private final Session session;

    public CassandraRepository(Session session) {
        this.session = session;
    }

    public void createTeam(Team team) {

    }

    public ResultSet getTeamById(int id) {
        return session.execute(String.format("SELECT * FROM teams WHERE id=%d", id));
//        for (Row row : results) {
//            System.out.format("%ssn", row.getString("name"),
//                    row.getInt("id"), row.getString("address"));
//        }
    }

    public void updateTeam(Team team) {

    }

    public void deleteTeam(Team team) {

    }
}
