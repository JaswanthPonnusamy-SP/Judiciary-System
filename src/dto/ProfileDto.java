package dto;

public class ProfileDto {

    public String name;

    public int Age;

    public String Contact_no;

    public String Address;

    public String email_Id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public String getContact_no() {
        return Contact_no;
    }

    public void setContact_no(String contact_no) {
        Contact_no = contact_no;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getEmail_Id() {
        return email_Id;
    }

    public void setEmail_Id(String email_Id) {
        this.email_Id = email_Id;
    }

    public String getAdhaar_number() {
        return adhaar_number;
    }


    public void setAdhaar_number(String adhaar_number) {
        this.adhaar_number = adhaar_number;
    }

    public String adhaar_number;



}

