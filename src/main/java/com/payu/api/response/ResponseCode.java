package com.payu.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Anele Chila
 */
@AllArgsConstructor
@Getter
public enum ResponseCode {

    DELETED("200", "Deleted successfully"),
    UPDATED("200", "Updated successfully"),
    RETRIEVED("200", "Retrieved successfully"),
    CREATED("200", "Created successfully");


    private String code;
    private String desc;

}
