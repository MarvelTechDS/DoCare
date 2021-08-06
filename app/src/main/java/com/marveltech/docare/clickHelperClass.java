package com.marveltech.docare;

public class clickHelperClass {
    String clicks,supplyid;
    public clickHelperClass(String clicks, String supplyid) {
        this.clicks = clicks;
        this.supplyid = supplyid;
    }
    public String getClicks() {
        return clicks;
    }

    public void setClicks(String clicks) {
        this.clicks = clicks;
    }

    public String getSupplyid() {
        return supplyid;
    }

    public void setSupplyid(String supplyid) {
        this.supplyid = supplyid;
    }
}
