package com.mycodestack.knowd;

import java.util.Comparator;

/**
 * Created by lakshay on 19/3/18.
 */

public class DoctorData
{
    public String HospitalName ;
    public String DoctorName ;
    public String City ;
    public String location ;
    public String current_number ;
    public String Contact ;

    public DoctorData(){

    }
    public DoctorData(String HospitalName,String DoctorName , String City  , String contact , String location , String current_number){
        this.HospitalName = HospitalName;
        this.DoctorName = DoctorName;
        this.City = City ;
        this.Contact = contact;
        this.location = location;
        this.current_number = current_number;
    }


    public static Comparator<DoctorData> waiting  = new Comparator<DoctorData>() {

        @Override
        public int compare(DoctorData lhs, DoctorData rhs) {
            return Integer.parseInt(String.valueOf(lhs.getCurrent_number())) - Integer.parseInt(String.valueOf(rhs.getCurrent_number()));
        }
    };
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCurrent_number() {
        return current_number;
    }

    public String setCurrent_number(String current_number) {
        return current_number;
    }

    public void setHospitalName(String hospitalName) {
        this.HospitalName = hospitalName;
    }

    public void setDoctorName(String doctorName) {
        this.DoctorName = doctorName;
    }

    public void setCity(String city) {
        this.City = city;
    }

    public void setContact(String contact) {
        this.Contact = contact;
    }

    public String getHospitalName() {
        return HospitalName;
    }

    public String getDoctorName() {
        return DoctorName;
    }

    public String getCity() {
        return City;
    }

    public String getContact() {
        return Contact;
    }

}
