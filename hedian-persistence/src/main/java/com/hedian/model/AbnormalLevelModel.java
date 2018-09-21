package com.hedian.model;

import java.io.Serializable;

public class AbnormalLevelModel implements Serializable {

    private Integer abnormalLevelCount;
    private String abnormalLevelColor;

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

    @Override
    public String toString() {
        return "AbnormalLevelModel{" +
                "abnormalLevelCount=" + abnormalLevelCount +
                ", abnormalLevelColor='" + abnormalLevelColor + '\'' +
                '}';
    }
}
