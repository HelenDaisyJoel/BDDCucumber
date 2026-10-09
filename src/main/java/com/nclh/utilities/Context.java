package com.nclh.utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Context {

    Map<String, String> stepData;
    Map<String, List<String>> listData;

    public Context() {
        stepData = new HashMap<>();
        listData = new HashMap<>();
    }

    public void setContext(String key, String value) {
        stepData.put(key, value);
    }

    public String getContext(String key) {
        return stepData.get(key);
    }

    public void setListContext(String key, List<String> value){
        listData.put(key,value);
    }

    public List<String> getListContext(String key){
        return listData.get(key);
    }


}
