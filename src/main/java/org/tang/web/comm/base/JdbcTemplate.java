package org.tang.web.comm.base;

import org.tang.framework.annotation.InitBean;
import org.tang.framework.iface.InitFace;
import org.tang.framework.jdbc.JdbcHandle;

import java.beans.PropertyVetoException;
import java.io.IOException;

/***
 * @author tang
 * */
@InitBean
public class JdbcTemplate extends JdbcHandle implements InitFace {

    @Override
    public void init() {
        try {
            initConfig("config/c3p0.properties");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
    }

}
