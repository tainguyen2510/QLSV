/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qlsv;

/**
 *
 * @author DELL
 */
public class SinhVien {
    String MSV;
    String hoten,ML;
    double DLT;
    double DTH;

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }
    
    public SinhVien(String MSV, String ML, double DLT, double DTH) {
        this.MSV = MSV;
        this.ML = ML;
        this.DLT = DLT;
        this.DTH = DTH;
    }
    public SinhVien(String MSV){
        this.MSV = MSV;
    }

    public String getMSV() {
        return MSV;
    }

    public void setMSV(String MSV) {
        this.MSV = MSV;
    }

    public String getML() {
        return ML;
    }

    public void setML(String ML) {
        this.ML = ML;
    }

    public double getDLT() {
        return DLT;
    }

    public void setDLT(double DLT) {
        if(DLT>=0&& DLT<=10) this.DLT = DLT;
        else System.out.println("Điểm lý thuyết nhâp từ 0-->10");
    }

    public double getDTH() {
        return DTH;
    }

    public void setDTH(double DTH) {
        if(DTH>=0&& DTH<=10) this.DTH = DTH;
        else System.out.println("Điểm thực hành nhâp từ 0-->10");
    }
    @Override
    public boolean equals(Object obj){
        return this.MSV.equalsIgnoreCase(((SinhVien)obj).MSV);
    }
    public double diemTB(){
         return (DLT+DTH)/2;
         
    }
    public String KQ(){
        if(diemTB()>=5){
            System.out.println("Đậu");
        } 
        else System.out.println("Rớt");
        return null; 
    }
}
