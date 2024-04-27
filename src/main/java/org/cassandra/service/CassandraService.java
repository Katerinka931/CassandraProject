package org.cassandra.service;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import org.cassandra.entities.Team;
import org.cassandra.repository.CassandraRepository;
import org.cassandra.utils.PrintUtil;

public class CassandraService {
    private final CassandraRepository repository;

    public CassandraService(Session session) {
        this.repository = new CassandraRepository(session);
    }

    public void createTeam(Team team) {
        repository.createTeam(team);
    }

    public void getAllTeams() {
        ResultSet resultSet = repository.getAllTeams();
        PrintUtil.printTeam(resultSet);
    }
    public void getTeamById(String id) {
        ResultSet resultSet = repository.getTeamById(id);
        PrintUtil.printTeam(resultSet);
    }

    public void updateTeam(Team team) {
        repository.updateTeam(team);
    }

    public void deleteTeam(String id) {
        repository.deleteTeam(id);
    }
}
