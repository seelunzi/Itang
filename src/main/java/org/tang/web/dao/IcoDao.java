package org.tang.web.dao;

import org.tang.web.domain.IcopTest;

import java.util.List;

public interface IcoDao {
    IcopTest getIco(Integer id);

    List<IcopTest> getIcos();

    Long delIco(Integer id);
}
