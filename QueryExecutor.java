package com.practice;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ColumnDefinition;
import com.datastax.oss.driver.api.core.cql.ColumnDefinitions;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.querybuilder.QueryBuilder;
import com.datastax.oss.driver.api.querybuilder.select.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import java.util.Iterator;

public class QueryExecutor {
    public List executeQuery(CqlSession session, String keySpace, String tableName) {
        Select select = QueryBuilder.selectFrom(keySpace, tableName).all();

        ResultSet rs = session.execute(select.build());
        final List<String> colNames = new ArrayList<>();
        final List<Map<String, Object>> results = new ArrayList<>();

        ColumnDefinitions colDefs = rs.getColumnDefinitions();
        Iterator<ColumnDefinition> it = colDefs.iterator();

        Map columnHeader = new HashMap();
        while(it.hasNext()) {
            ColumnDefinition colDef = it.next();
            String colName = colDef.getName().toString();
            colNames.add(colName);
            columnHeader.put(colName, colName);
        }

        results.add(columnHeader);

        Iterator<Row> rowsIt = rs.iterator();

        while(rowsIt.hasNext()) {
            Row row = rowsIt.next();
            Map<String, Object> rowMap = new HashMap<>();
            for(int i=0; i<colNames.size(); i++) {
                Object colValue = row.getObject(colNames.get(i));
                rowMap.put(colNames.get(i), colValue);
            }
            results.add(rowMap);
        }
        return results;
    }
}
