package com.kh.semi.qa.areainfo;

import java.util.ArrayList;
import java.util.List;

public class AreaInfo {

    private final String SEOUL = "서울";
    private final String INCHEON = "인천";
    private final String GYEONGGI = "경기";
    private final String DAEJEON  = "대전";
    private final String DAEGU = "대구";
    private final String BUSAN  = "부산";
    private final String ULSAN  = "울산";
    private final String GWANGJU  = "광주";
    private final String JEJU = "제주도";

    List<String> areaInfo = new ArrayList<String>();

    public AreaInfo() {
        areaInfo.add(SEOUL);
        areaInfo.add(INCHEON);
        areaInfo.add(GYEONGGI);
        areaInfo.add(DAEJEON);
        areaInfo.add(DAEGU);
        areaInfo.add(BUSAN);
        areaInfo.add(ULSAN);
        areaInfo.add(GWANGJU);
        areaInfo.add(JEJU);
    }

    public List<String> getAreaInfo() {
        return areaInfo;
    }
}
