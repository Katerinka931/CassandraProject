package org.cassandra.service;

import com.datastax.oss.driver.api.core.PagingIterable;
import org.cassandra.entities.Team;
import org.cassandra.repository.CassandraRepository;

public class CassandraService {
    private final CassandraRepository repository;

    public CassandraService() {
        this.repository = new CassandraRepository();
    }

    void createTeam(Team team) {
        repository.createTeam(team);
    }

    Team getTeamById(int id) {
        return repository.getTeamById(id);
    }

    void updateTeam(Team team) {
        repository.updateTeam(team);
    }

    void deleteTeam(Team team) {
        repository.deleteTeam(team);
    }

    PagingIterable<Team> getTeamsByNameLike(String name) {
        return repository.getTeamsByNameLike(name);
    }

    PagingIterable<Team> getTeamsCountBetween(int min, int max) {
        return repository.getTeamsCountBetween(min, max);
    }
}
