package org.cassandra.utils;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.TypeCodec;

import java.util.UUID;

public class PrintUtil {

    public static void printTeam(ResultSet results) {
        if (results.isExhausted())
            System.out.println("Данных нет");
        else {
            for (Row row : results) {
                System.out.format("ID: %s\nName: %s\nCount: %d\n", row.get("id", TypeCodec.uuid()), row.getString("name"), row.getInt("count"));
            }
        }
    }
}
