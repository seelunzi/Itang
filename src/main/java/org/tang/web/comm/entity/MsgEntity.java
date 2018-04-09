package org.tang.web.comm.entity;

import lombok.Data;
import org.tang.framework.entity.BaseModel;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tang
 * @remark 消息机制容器。
 */
@SuppressWarnings("serial")
@Data
public class MsgEntity extends BaseModel {

    public Integer code;
    public String msg;
    public Object datas;

    public MsgEntity(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public MsgEntity() {
    }

    public MsgEntity(Integer code, String msg, Object datas) {
        super();
        this.code = code;
        this.msg = msg;
        this.datas = datas;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public void setDataField(String fieldName, Object value) {
        if (datas == null) {
            datas = new HashMap<String, Object>();
        }
        if (!Map.class.isAssignableFrom(datas.getClass())) {
            return;
        }
        try {
            ((Map) datas).put(fieldName, value);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
