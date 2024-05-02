package org.cassandra.repository;

import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import org.cassandra.entities.Team;
import org.cassandra.utils.CassandraConnector;

import java.util.UUID;

public class CassandraRepository {

    private final Session session;

    public CassandraRepository(Session session) {
        this.session = session;
    }

    public void createTeam(Team team) {
        session.execute("CREATE TABLE IF NOT EXISTS teams ("
                + "id UUID PRIMARY KEY,"
                + "name text,"
                + "count int);");

        PreparedStatement preparedStatement = session.prepare("INSERT INTO teams (id, name, count) VALUES (?, ?, ?);");
        session.execute(preparedStatement.bind(UUID.randomUUID(), team.getName(), team.getCount()));
    }

    public ResultSet getAllTeams(){
        return session.execute("SELECT * FROM teams");
    }

    public ResultSet getTeamById(String id) {
        return session.execute(String.format("SELECT * FROM teams WHERE id=%s", id));
    }

    public void updateTeam(String id, Team team) {
        PreparedStatement preparedStatement = session.prepare("UPDATE teams SET name = ?, count = ? WHERE id = ?");
        session.execute(preparedStatement.bind(team.getName(), team.getCount(), UUID.fromString(id)));
    }

    public void deleteTeam(String id) {
        session.execute(String.format("DELETE FROM %s.teams WHERE id = %s;", CassandraConnector.getKeySpace(), id));
    }

    public ResultSet selectTeamsByCount(int min, int max) {
        return session.execute(String.format("SELECT * FROM %s.teams WHERE count >= %d AND count <= %d ALLOW FILTERING;", CassandraConnector.getKeySpace(), min, max));
    }
}
