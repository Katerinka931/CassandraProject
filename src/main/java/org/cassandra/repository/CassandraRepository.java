package org.cassandra;

import com.datastax.oss.driver.api.core.PagingIterable;

public class CassandraRepository {


    void createTeam(Team team) {

    }

    Team getTeamById(int id) {
        return null;
    }

    void updateTeam(Team team) {

    }

    void deleteTeam(Team team) {

    }

    PagingIterable<Team> getTeamsByNameLike(String name) {
        return null;
    }

    PagingIterable<Team> getTeamsCountBetween(int min, int max) {
        return null;
    }
}
