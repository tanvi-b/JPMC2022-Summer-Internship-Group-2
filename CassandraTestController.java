package com.practice;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.CqlSessionBuilder;
import org.springframework.web.bind.annotation.*;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping
public class CassandraTestController {
    CqlSession session;
    CassandraTestController () {
        CqlSessionBuilder builder = CqlSession.builder();
        builder.addContactPoint(new InetSocketAddress("127.0.0.1", 9042));
        builder.withLocalDatacenter("datacenter1");

        session = builder.build();
    }
    @RequestMapping(value = "/GetKeySpaces", method = RequestMethod.GET)
    public List<Map<String, Object>> GetKeySpaces () {
        return GetData ("system_schema", "keyspaces");
    }
    @RequestMapping(value = "/GetTables", method = RequestMethod.GET)
    public List<Map<String, Object>> GetTables (@RequestParam String keySpace) {
        List<Map<String, Object>> list = GetData ("system_schema", "tables");
        List<Map<String, Object>> newList = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            Map row=list.get(i);
            if (row.get("keyspace_name").equals(keySpace))
                newList.add(row);
        }
        return newList;
    }
    @RequestMapping(value = "/GetTableData", method = RequestMethod.GET)
    public List<Map<String, Object>> GetTableData (@RequestParam String keySpace, @RequestParam String tableName) {
        return GetData (keySpace, tableName);
    }
    public List<Map<String, Object>> GetData (String keySpace, String tableName) {
        List<Map<String, Object>> list = new QueryExecutor().executeQuery(session, keySpace,tableName);
        return list;
    }
}