package com.xiaoji.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * sheet 页数据定制
 * @author cp
 * @since 2018/11/14
 */
public class SheetData  {

    // sheet 页中存储 #{key} 的数据
    private Map<String, Object> map = new HashMap<String, Object>();

    // 列表数据存储 sheet 页中替换 ${key} 并以列为单位向下赋值
    private List<Object> data = new LinkedList<Object>();

    // sheet 表格名称
    //private String name ;
    /*public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }*/

    public void put(String key , Object value) {
        map.put(key, value);
    }
    public void remove(String key) {
        map.remove(key);
    }
    public Object get(String key) {
        return map.get(key);
    }

    public void addData(Object t){
        data.add(t);
    }
    public void addDatas(List<? extends Object> list) {
        data.addAll(list);
    }
    public List<Object>  getDatas() {
        return data;
    }

    public SheetData() {
        super();
        //this.name = name;
    }
    /**
     * 清理map存储和数据存储
     */
    public void clear() {
        map.clear();
        data.clear();
    }

    @Override
    public String toString() {
        return "SheetData{" +
                "map=" + map +
                ", data=" + data +
                '}';
    }
}