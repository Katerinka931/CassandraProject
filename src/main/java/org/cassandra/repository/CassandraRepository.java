package org.cassandra.repository;

import com.datastax.oss.driver.api.core.PagingIterable;
import org.cassandra.entities.Team;

public class CassandraRepository {

    public void createTeam(Team team) {

    }

    public Team getTeamById(int id) {
        return null;
    }

    public void updateTeam(Team team) {

    }

    public void deleteTeam(Team team) {

    }

    public PagingIterable<Team> getTeamsByNameLike(String name) {
        return null;
    }

    public PagingIterable<Team> getTeamsCountBetween(int min, int max) {

        return null;
    }
}
