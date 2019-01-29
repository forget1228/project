package com.xj.ptgd.entity.body;

/**
 * 流水号生成
 * @author cp
 * @since 2018/9/10
 */
public class ObjectMaxSn {
    private String tableName;
    private String columnName;
    private String maxSerialNo;
    private String dateFmt;
    private String noFmt;

    @Override
    public String toString() {
        return "ObjectMaxSn{" +
                "tableName='" + tableName + '\'' +
                ", columnName='" + columnName + '\'' +
                ", maxSerialNo='" + maxSerialNo + '\'' +
                ", dateFmt='" + dateFmt + '\'' +
                ", noFmt='" + noFmt + '\'' +
                '}';
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getMaxSerialNo() {
        return maxSerialNo;
    }

    public void setMaxSerialNo(String maxSerialNo) {
        this.maxSerialNo = maxSerialNo;
    }

    public String getDateFmt() {
        return dateFmt;
    }

    public void setDateFmt(String dateFmt) {
        this.dateFmt = dateFmt;
    }

    public String getNoFmt() {
        return noFmt;
    }

    public void setNoFmt(String noFmt) {
        this.noFmt = noFmt;
    }
}
