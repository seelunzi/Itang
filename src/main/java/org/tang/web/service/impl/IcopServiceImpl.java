package org.tang.web.service.impl;

import org.tang.framework.annotation.InitBean;
import org.tang.framework.annotation.OutBean;
import org.tang.framework.annotation.Transacted;
import org.tang.web.dao.IcoDao;
import org.tang.web.domain.IcopTest;
import org.tang.web.service.IcopService;

import java.util.List;

@InitBean
public class IcopServiceImpl implements IcopService {

    @OutBean
    IcoDao icopDao;

    @Override
    public IcopTest getIcop(Integer id) {
        return icopDao.getIcop(id);
    }

    @Override
    public List<IcopTest> getIcops() {
        return icopDao.getIcops()
                ;
    }

    @Transacted
    @Override
    public Long delIcop(Integer id) {
        Long code = icopDao.delIcop(id);
        Integer i = 50 / 0;
        System.out.println(i);
        return code;
    }

}
