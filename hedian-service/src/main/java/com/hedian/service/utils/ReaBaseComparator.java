package com.hedian.service.utils;

import com.hedian.entity.ResBase;

import java.util.Comparator;

public class ReaBaseComparator implements Comparator<ResBase> {
    @Override
    public int compare(ResBase o1, ResBase o2) {
        return o2.getCountNum().compareTo(o1.getCountNum());
    }
}
