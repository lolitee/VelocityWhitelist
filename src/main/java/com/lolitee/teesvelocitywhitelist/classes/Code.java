package com.lolitee.teesvelocitywhitelist.classes;

public class Code {
    private final String code;
    private final long expire;

    public Code(String code, long expire) {
        this.code = code;
        this.expire = expire;
    }

    public String getCode() {
        return code;
    }

    public long getExpire() {
        return expire;
    }
}
