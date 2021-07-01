package com.marveltech.docare;

public class HelperClass {
        String name,phone,uid;

        public HelperClass() {

        }

    public HelperClass(String name, String phone, String uid) {
            this.name = name;
            this.phone = phone;
            this.uid = uid;
//            this.number1 = number1;
//            this.number2 = number2;

        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

//    public String getNumber1() {
//        return number1;
//    }
//
//    public void setNumber1(String number1) {
//        this.number1 = number1;
//    }
//
//    public String getNumber2() {
//        return number2;
//    }
//
//    public void setNumber2(String number2) {
//        this.number2 = number2;
//    }

}

