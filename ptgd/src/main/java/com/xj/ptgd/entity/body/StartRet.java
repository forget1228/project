package com.xj.ptgd.entity.body;

public class StartRet {
    private String pinKeyName; //PIN密钥名称
    private String pinKeyValue; //主密钥串
    private String pinVerifyValue; //校验值
    private String macKeyName; //MAC密钥名称
    private String macKeyValue; //主密钥串1
    private String macVerifyValue; //校验值1

    public String getPinKeyName() {
        return pinKeyName;
    }

    public void setPinKeyName(String pinKeyName) {
        this.pinKeyName = pinKeyName;
    }

    public String getPinKeyValue() {
        return pinKeyValue;
    }

    public void setPinKeyValue(String pinKeyValue) {
        this.pinKeyValue = pinKeyValue;
    }

    public String getPinVerifyValue() {
        return pinVerifyValue;
    }

    public void setPinVerifyValue(String pinVerifyValue) {
        this.pinVerifyValue = pinVerifyValue;
    }

    public String getMacKeyName() {
        return macKeyName;
    }

    public void setMacKeyName(String macKeyName) {
        this.macKeyName = macKeyName;
    }

    public String getMacKeyValue() {
        return macKeyValue;
    }

    public void setMacKeyValue(String macKeyValue) {
        this.macKeyValue = macKeyValue;
    }

    public String getMacVerifyValue() {
        return macVerifyValue;
    }

    public void setMacVerifyValue(String macVerifyValue) {
        this.macVerifyValue = macVerifyValue;
    }
}
