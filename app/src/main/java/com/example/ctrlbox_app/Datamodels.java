package com.example.ctrlbox_app;

public class Datamodels {

    private static int BoxId;
    private String  Vendor;
    private String VendorName;
    private String  TranDate ;

    public Datamodels(int BoxId, String Vendor, String VendorName, String TranDate) {
        this.BoxId = BoxId;
        this.Vendor = Vendor;
        this.VendorName = VendorName;
        this.TranDate = TranDate;
    }


    public static int getBoxId() {
        return BoxId;
    }

    public String getVendor() {
        return Vendor;
    }

    public String getVendorName() {
        return VendorName;
    }

    public String getTranDate() {
        return TranDate;
    }

    public void setBoxid(int boxid) {
        BoxId = boxid;
    }

    public void setVendor(String vendor) {
        Vendor = vendor;
    }

    public void setVendorName(String vendorName) {
        VendorName = vendorName;
    }

    public void TranDate(String trandate) {
        TranDate = trandate;
    }
}

