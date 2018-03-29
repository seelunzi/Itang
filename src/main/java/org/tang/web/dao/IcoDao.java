package org.tang.web.dao;

import org.tang.web.domain.IcopTest;

import java.util.List;

public interface IcoDao {


    public IcopTest getIcop(Integer id);


    public List<IcopTest> getIcops();

    public Long delIcop(Integer id);
}
