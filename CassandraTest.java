package com.practice;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.CqlSessionBuilder;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.querybuilder.QueryBuilder;
import com.datastax.oss.driver.api.querybuilder.relation.Relation;
import com.datastax.oss.driver.api.querybuilder.select.Select;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CassandraTest {
    public static void main (String args[]) {
        System.out.println("Hello World");

        CqlSessionBuilder builder = CqlSession.builder();
        builder.addContactPoint(new InetSocketAddress("127.0.0.1", 9042));
        builder.withLocalDatacenter("datacenter1");

        CqlSession session = builder.build();

        System.out.println("Cassandra Connection Established");
        List<Map<String, Object>> list = new QueryExecutor().executeQuery(session, "test_two", "student_by_id");

        for(int i=0; i<list.size(); i++) {
            Map<String, Object> row = list.get(i);
            System.out.println(row);
        }

        session.close();
        /*Select select = QueryBuilder.selectFrom("system_schema", "tables").all();

        ResultSet rs = session.execute(select.build());
        final List<String> result = new ArrayList<>();
        rs.forEach(x -> result.add(x.getString("table_name")));
        System.out.println("List of system tables");
        System.out.println(result);
        */
        System.exit(0);
    }
}