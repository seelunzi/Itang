package org.tang.web.service;

import org.tang.web.domain.IcopTest;

import java.util.List;

public interface IcopService {


    public IcopTest getIcop(Integer id);


    public List<IcopTest> getIcops();

    public Long delIcop(Integer id);

}
