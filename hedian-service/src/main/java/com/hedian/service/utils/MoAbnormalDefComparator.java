package com.hedian.service.utils;

import com.hedian.entity.MoAbnormalDef;

import java.util.Comparator;

public class MoAbnormalDefComparator implements Comparator<MoAbnormalDef> {
    @Override
    public int compare(MoAbnormalDef o1, MoAbnormalDef o2) {
        return o2.getCountNum().compareTo(o1.getCountNum());
    }
}
