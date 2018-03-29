package org.tang.framework.jdbc.entity;

import lombok.Data;

/***
 * @author tang
 * */
@Data
public class JdbcEntity {

    private String sql;

    private Object[] params;

    public JdbcEntity(String sql, Object[] params) {
        this.sql = sql;
        this.params = params;
    }
}
