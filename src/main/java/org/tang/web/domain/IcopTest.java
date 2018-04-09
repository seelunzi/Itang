package org.tang.web.domain;

import lombok.Data;
import org.tang.framework.entity.BaseModel;

import java.util.Date;

/****
 * @author tang
 * */
@SuppressWarnings("serial")
@Data
public class IcopTest extends BaseModel {
    private Integer id;
    private String name;
    private Integer age;
    private Date createTime;
    private Integer sex;
}
