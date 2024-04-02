package com.portal.jobconnect.utils;

import org.springframework.stereotype.Component;

@Component
public class ResponseObject {
    public int code;
    public Object result;

    public ResponseObject() {};
    public ResponseObject(int code, Object result) {
        this.code = code;
        this.result = result;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
    
    public void setResult(Object result) {
        this.result = result;
    }

    public Object getResult() {
        return this.result;
    }
}
