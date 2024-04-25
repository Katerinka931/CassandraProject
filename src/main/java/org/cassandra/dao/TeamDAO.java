package org.cassandra.dao;

import com.datastax.oss.driver.api.core.PagingIterable;
import com.datastax.oss.driver.api.mapper.annotations.*;
import org.cassandra.entities.Team;
import org.cassandra.repository.TeamQueryProvider;

@Dao
public interface TeamDAO {
    @Insert
    void insertTeam(Team team);

    @Select
    Team getTeamById(int id);

    @Update
    void updateTeam(Team team);

    @Delete
    void deleteTeam(Team team);

    //custom queries

    // показать в лабе оба этих метода для двух разных запросов
    @Query("SELECT * FROM team WHERE name like :name")
    PagingIterable<Team> getTeamsByNameLike(String name);

    //@Query("SELECT * FROM team WHERE count >= :min AND count <= :max ALLOW FILTERING")
    //PagingIterable<Team> getTeamsCountBetween(int min, int max);

    @QueryProvider(providerClass = TeamQueryProvider.class, entityHelpers = Team.class, providerMethod = "getTeamsCountBetween")
    PagingIterable<Team> getTeamsCountBetween(int min, int max);
}
