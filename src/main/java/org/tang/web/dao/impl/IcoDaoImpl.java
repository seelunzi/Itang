package org.tang.web.dao.impl;

import org.tang.framework.annotation.InitBean;
import org.tang.framework.annotation.OutBean;
import org.tang.web.comm.base.JdbcTemplate;
import org.tang.web.dao.IcoDao;
import org.tang.web.domain.IcopTest;

import java.util.List;

@InitBean
public class IcoDaoImpl implements IcoDao {

    @OutBean
    JdbcTemplate jdbcTemplate;

    @Override
    public IcopTest getIcop(Integer id) {
        return jdbcTemplate.findBeanFirst(IcopTest.class, "id", id);
    }

    @Override
    public List<IcopTest> getIcops() {
        return jdbcTemplate.findBean(IcopTest.class);
    }

    @Override
    public Long delIcop(Integer id) {

        String sql = "delete from icop_test where id=? limit 1";
        return jdbcTemplate.doUpdate(sql, id);
    }
}
