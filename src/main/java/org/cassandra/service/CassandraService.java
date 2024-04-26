package org.cassandra.service;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import org.cassandra.entities.Team;
import org.cassandra.repository.CassandraRepository;

public class CassandraService {
    private final CassandraRepository repository;

    public CassandraService(Session session) {
        this.repository = new CassandraRepository(session);
    }

    public void createTeam(Team team) {
        repository.createTeam(team);
    }

    public ResultSet getTeamById(int id) {
        return repository.getTeamById(id);
    }

    public void updateTeam(Team team) {
        repository.updateTeam(team);
    }

    public void deleteTeam(Team team) {
        repository.deleteTeam(team);
    }
}
