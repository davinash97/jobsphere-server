package com.portal.jobconnect.utils;

import org.springframework.stereotype.Component;

@Component
public class ResponseObject {
    public int code;
    public String result;

    public ResponseObject() {};
    public ResponseObject(int code, String result) {
        this.code = code;
        this.result = result;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
    
    public void setResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return this.result;
    }
}
