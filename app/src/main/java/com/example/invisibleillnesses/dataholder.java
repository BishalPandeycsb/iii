package com.example.invisibleillnesses;

public class dataholder
{
    String FirstName, LastName, Residential1, Residential2, ReasonForApplication, PhoneNumber, BirthDate;

    public dataholder(String name, String firstName, String lastName, String residential1, String residential2, String reasonForApplication, String phoneNumber, String birthDate) {

        FirstName = firstName;
        LastName = lastName;
        Residential1 = residential1;
        Residential2 = residential2;
        ReasonForApplication = reasonForApplication;
        PhoneNumber = phoneNumber;
        BirthDate = birthDate;

    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }


    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getResidential1() {
        return Residential1;
    }

    public void setResidential1(String residential1) {
        Residential1 = residential1;
    }

    public String getResidential2() {
        return Residential2;
    }

    public void setResidential2(String residential2) {
        Residential2 = residential2;
    }

    public String getReasonForApplication() {
        return ReasonForApplication;
    }

    public void setReasonForApplication(String reasonForApplication) {
        ReasonForApplication = reasonForApplication;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(String birthDate) {
        BirthDate = birthDate;
    }
}
