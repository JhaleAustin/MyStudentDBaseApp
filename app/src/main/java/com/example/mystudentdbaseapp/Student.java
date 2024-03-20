package com.example.mystudentdbaseapp;

public class Student {
    private int studentNumber;
    private String Productname;
    private String ProductPrice;
    private String Productcode;
    private String Productquantity;

    public Student(String Productname, String ProductPrice,String Productcode, String Productquantity){
        this.Productname = Productname;
        this.ProductPrice = ProductPrice;
        this.Productcode = Productcode;
        this.Productquantity = Productquantity;
    }

    public void setStudentNumber( int studentNumber){
        this.studentNumber = studentNumber;
    }
    public void setProductname( String Productname) {
        this.Productname = Productname;
    }

    public void setProductPrice( String ProductPrice) {
        this.ProductPrice = ProductPrice;
    }


    public void setProductcode( String Productcode) {
        this.Productcode = Productcode;
    }

    public void setProductquantity( String Productquantity) {
        this.Productquantity = Productquantity;
    }

    public int getStudentNumber(){
        return this.studentNumber;
    }
    public String getProductname(){
        return this.Productname;
    }
    public String getProductPrice(){
        return this.ProductPrice;
    }

    public String getProductcode(){
        return this.Productcode;
    }
    public String getProductquantity(){
        return this.Productquantity;
    }
}
