package com.lolitee.teesvelocitywhitelist.database;

public abstract class AbstractCommand {
    public abstract String sql();
    String[] cols = new String[0];
    Object[] vals = new Object[0];
    public AbstractCommand columns(String[] cols){
        this.cols = cols;
        return this;
    }
    public AbstractCommand values(Object[] vals){
        this.vals = vals;
        return this;
    }

}
