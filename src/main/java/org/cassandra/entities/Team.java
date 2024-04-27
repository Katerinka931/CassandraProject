package org.cassandra.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Team {
    private int id;
    private String name;
    private int count;

    public Team(String name, int count) {
        this.name = name;
        this.count = count;
    }
}

