package org.tang.web.domain;

import lombok.Data;
import org.tang.framework.entity.BaseModel;

/***
 * @author tang
 * */
@SuppressWarnings("serial")
@Data
public class UserInfo extends BaseModel {

    private String userId;

    private String userName;

    private Integer age;
}
