package org.cassandra.repository;

import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import org.cassandra.entities.Team;

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

    public void updateTeam(Team team) {
        session.execute("");
    }

    public void deleteTeam(String id) {
        session.execute("");
    }
//    todo 2 запроса
}
