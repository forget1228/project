package com.xiaoji.model;

public class TestData {
    private int projectName ;
    private String demandName ;
    private String sumNumbers ;

    public TestData(int projectName, String demandName, String sumNumbers) {
        this.projectName = projectName;
        this.demandName = demandName;
        this.sumNumbers = sumNumbers;
    }

    public int getProjectName() {
        return projectName;
    }

    public void setProjectName(int projectName) {
        this.projectName = projectName;
    }

    public String getDemandName() {
        return demandName;
    }

    public void setDemandName(String demandName) {
        this.demandName = demandName;
    }

    public String getSumNumbers() {
        return sumNumbers;
    }

    public void setSumNumbers(String sumNumbers) {
        this.sumNumbers = sumNumbers;
    }
}
