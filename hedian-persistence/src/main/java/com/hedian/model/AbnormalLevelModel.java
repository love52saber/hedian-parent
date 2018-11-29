package com.hedian.model;

import java.io.Serializable;

public class AbnormalLevelModel implements Serializable {

    private Integer abnormalLevelCount;
    private String abnormalLevelColor;
    private String abnormalLevelName;

    public Integer getAbnormalLevelCount() {
        return abnormalLevelCount;
    }

    public void setAbnormalLevelCount(Integer abnormalLevelCount) {
        this.abnormalLevelCount = abnormalLevelCount;
    }

    public String getAbnormalLevelColor() {
        return abnormalLevelColor;
    }

    public void setAbnormalLevelColor(String abnormalLevelColor) {
        this.abnormalLevelColor = abnormalLevelColor;
    }

    public String getAbnormalLevelName() {
        return abnormalLevelName;
    }

    public void setAbnormalLevelName(String abnormalLevelName) {
        this.abnormalLevelName = abnormalLevelName;
    }

    @Override
    public String toString() {
        return "AbnormalLevelModel{" +
                "abnormalLevelCount=" + abnormalLevelCount +
                ", abnormalLevelColor='" + abnormalLevelColor + '\'' +
                ", abnormalLevelName='" + abnormalLevelName + '\'' +
                '}';
    }
}
