package org.cassandra.repository;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.PagingIterable;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.datastax.oss.driver.api.mapper.MapperContext;
import com.datastax.oss.driver.api.mapper.entity.EntityHelper;
import com.datastax.oss.driver.api.querybuilder.QueryBuilder;
import org.cassandra.entities.Team;

public class TeamQueryProvider {
    private final CqlSession session;
    private final EntityHelper<Team> teamHelper;

    public TeamQueryProvider(MapperContext context, EntityHelper<Team> teamHelper) {
        this.session = context.getSession();
        this.teamHelper = teamHelper;
    }

    public PagingIterable<Team> getTeamsCountBetween(String min, String max) {
        SimpleStatement statement = QueryBuilder.selectFrom("team")
                .all()
                .whereColumn("count")
                .isGreaterThan(QueryBuilder
                        .bindMarker(min))
                .whereColumn("count")
                .isLessThan(QueryBuilder
                        .bindMarker(max))
                .build();
        PreparedStatement preparedSelectTeam = session.prepare(statement);
        return session
                .execute(preparedSelectTeam.getQuery())
                .map(result -> teamHelper.get(result, true));
    }
}
