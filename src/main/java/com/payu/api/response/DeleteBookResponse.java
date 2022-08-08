package com.payu.api.response;

/**
 * @author Anele Chila
 */
public class DeleteBookResponse extends BookResponse {

    public DeleteBookResponse() {
        super(ResponseCode.DELETED.getCode(), ResponseCode.DELETED.getDesc());
    }
}
