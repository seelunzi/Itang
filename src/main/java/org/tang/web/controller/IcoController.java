package org.tang.web.controller;

import org.tang.framework.annotation.JsonSerialize;
import org.tang.framework.annotation.OutBean;
import org.tang.framework.annotation.PathBinding;
import org.tang.framework.util.StringUtil;
import org.tang.web.comm.entity.MsgEntity;
import org.tang.web.domain.IcopTest;
import org.tang.web.service.IcopService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@PathBinding("/icop")
public class IcoController {


    @OutBean
    IcopService icopService;

    @PathBinding("loadIcops.do")
    @JsonSerialize
    public Object loadIcops() {
        List<IcopTest> icops = icopService.getIcops();
        return icops;
    }

    /**
     * 删除数据
     *
     * @param request
     * @return
     */
    @PathBinding("delIcop.do")
    @JsonSerialize
    public Object delIcop(HttpServletRequest request) {
        Integer id = StringUtil.toInteger(request.getParameter("id"));
        Long code = icopService.delIcop(id);
        if (code > 0) {
            return new MsgEntity(0, "操作成功");
        }
        return new MsgEntity(-1, "系统出错");
    }
}
