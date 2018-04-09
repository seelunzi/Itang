package org.tang.web.service;

import org.tang.web.domain.IcopTest;

import java.util.List;

/****
 * @author tang
 * */
public interface IcopService {

    IcopTest getIcop(Integer id);

    List<IcopTest> getIcops();

    Long delIcop(Integer id);
}
