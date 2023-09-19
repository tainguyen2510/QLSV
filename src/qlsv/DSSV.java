/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qlsv;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public class DSSV {
    private List<SinhVien> ds;

    public DSSV() {
        ds = new ArrayList<SinhVien>();
    }
    public boolean themSV(SinhVien sv){
        if(ds.contains(sv)){
            return false;
        }
        ds.add(sv);
        return true;
    }
    public SinhVien getSinhVien(int i){
        if(i>=0&&i<ds.size())
            return ds.get(i);
        return null;
    }
    public int tongSinhVien(){
        return ds.size();
    }
    public SinhVien timKiem(String MSV){
        SinhVien sv = new SinhVien(MSV);
        if(ds.contains(sv))
            return ds.get(ds.indexOf(sv));
        return null;
    }
    public boolean xoaSinhVien(String MSV){
        SinhVien sv = new SinhVien(MSV);
        if(ds.contains(sv))
        {
            ds.remove(sv);
            return true;
        }
        return false;
    }
    public boolean suaThongTinSinhVien(String MSV, SinhVien svVersion2){
        SinhVien sv = new SinhVien(MSV);
        if(ds.contains(sv)){
            sv=ds.get(ds.indexOf(sv));
            sv.setHoten(svVersion2.getHoten());
            sv.setDLT(svVersion2.getDLT());
            sv.setDTH(svVersion2.getDTH());
            return true;
        }
        return false;
    }
    
}
