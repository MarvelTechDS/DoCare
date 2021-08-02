package com.marveltech.docare;

public class BedBookingHelperClass {
    String name,age,number,uid,refernceid,hospitalname,location;
    public BedBookingHelperClass(String name, String age, String number, String uid, String refernceid, String hospitalname, String location) {

        this.name = name;
        this.age = age;
        this.number = number;
        this.uid = uid;
        this.refernceid = refernceid;
        this.hospitalname = hospitalname;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getRefernceid() {
        return refernceid;
    }

    public void setRefernceid(String refernceid) {
        this.refernceid = refernceid;
    }

    public String getHospitalname() {
        return hospitalname;
    }

    public void setHospitalname(String hospitalname) {
        this.hospitalname = hospitalname;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


}
