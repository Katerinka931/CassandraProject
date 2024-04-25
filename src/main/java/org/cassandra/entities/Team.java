package org.cassandra;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import com.datastax.oss.driver.api.mapper.annotations.ClusteringColumn;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Getter
@Setter
@Entity
@Table
@AllArgsConstructor
public class Team {
    @PartitionKey
    private int id;

    private String name;
    @ClusteringColumn
    private int count;
}
