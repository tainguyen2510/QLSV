/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qlsv;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DELL
 */
public class GiaoDienSinhVien extends JFrame implements ActionListener, MouseListener{
    private static final long serialVersionUID = 1L;
    private JTextField tfMaso;
    private JComboBox<String> cboMalop;
    private JTextField tfDiemLT;
    private JTextField tfDiemTH;
    private JTextField tfDiemTB;
    private JTextField tfKQ;
    private JButton btnKQ;
    private JButton btnThem;
    private DefaultTableModel dfModel;
    private JTable table;
    private JTextField tfHoten;
    private DSSV ds;
    private JButton btnClear;
    private JButton btnLuu;
    private JButton btnXoa;
    private JButton btnSua;
    private JButton btnTim;
    private final String FILENAME="DATA_SINHVIEN.DAT";
    
    public GiaoDienSinhVien(){
        setTitle("Chương trình quản lí sinh viên");
        setSize(1000,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        buildGUI();
        napDSSV(FILENAME);
    }
    private void napDSSV(String file){
        if(new File(file).exists()){
            try{
                FileInputStream in = new FileInputStream(FILENAME);
                ObjectInputStream oos = new ObjectInputStream(in);
                ds =(DSSV)oos.readObject();
                napvaobang();
                oos.close();
            }
            catch (Exception e) {
                JOptionPane.showMessageDialog(null,"IO Error!");
                return;
            }
        }
        else ds = new DSSV();
    }
    private  void napvaobang(){
        dfModel.setRowCount(0);
        for(int i = 0; i <ds.tongSinhVien();i++){
            SinhVien sv = ds.getSinhVien(i);
            String[] dataRow = {sv.getMSV(),sv.getHoten(),sv.getML()
                                ,sv.getDLT()+"",sv.getDTH()+"",
                                sv.diemTB()+"",sv.KQ()};
            dfModel.addRow(dataRow);
        }
    }
    private  void buildGUI(){
        JPanel pnLeft = new JPanel(new GridLayout(0,1));
        pnLeft.add(new JLabel("THÔNG TIN SINH VIÊN", JLabel.CENTER));
        pnLeft.add(new JLabel("Mã sinh viên:"));
        pnLeft.add(tfMaso=new JTextField(20));
        pnLeft.add(new JLabel("Họ và tên:"));
        pnLeft.add(tfMaso=new JTextField(20));
        pnLeft.add(new JLabel("Mã lớp"));
        pnLeft.add(cboMalop=new JComboBox<String>());
        String [] dslop ={"DHTHA","DHTHB","DHTHC","DHTHD","DHTHK"};
        for(int i = 0; i <dslop.length;i++){
            cboMalop.addItem(dslop[i]);
        }
        pnLeft.add(new JLabel("Điểm lý thuyết:"));
        pnLeft.add(tfDiemLT=new JTextField(20));
        pnLeft.add(new JLabel("Điểm thực hành:"));
        pnLeft.add(tfDiemTH=new JTextField(20));
        pnLeft.add(new JLabel("Điểm trung bình:"));
        pnLeft.add(tfDiemTB=new JTextField(20));
        tfDiemTB.setEditable(false);
        pnLeft.add(new JLabel("Kết quả:"));
        pnLeft.add(tfKQ=new JTextField(20));
        tfKQ.setEditable(false);
        
        JPanel pnLeftBottom;
        pnLeft.add(pnLeftBottom=new JPanel());
        pnLeftBottom.add(btnKQ = new JButton("Kết quả"));
        pnLeftBottom.add(btnThem = new JButton("Thêm"));
        pnLeftBottom.add(btnClear = new JButton("Clear"));
        
        JPanel pnLeftBottom2;
        pnLeft.add(pnLeftBottom2=new JPanel());
        pnLeftBottom2.add(btnLuu = new JButton("Lưu"));
        pnLeftBottom2.add(btnXoa = new JButton("Xóa"));
        pnLeftBottom2.add(btnSua = new JButton("Sửa"));
        pnLeftBottom2.add(btnTim = new JButton("Tìm"));
        
        JPanel pnRight = new JPanel(new GridLayout(1,1));
        String [] headers={"Mã SV","Họ tên","Lớp", "Lý thuyết","Thực hành","Trung bình","Kết quả"};
        pnRight.add(new JScrollPane(table = new JTable(
                dfModel=new DefaultTableModel(headers,0)
        )));
        add(new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,pnLeft,pnRight));
        btnClear.addActionListener(this);
        btnKQ.addActionListener(this);
        btnThem.addActionListener(this);
        btnLuu.addActionListener(this);
        btnSua.addActionListener(this);
        btnTim.addActionListener(this);
        btnXoa.addActionListener(this);
        
        table.addMouseListener(this);
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        int vitri=table.getSelectedRow();
        SinhVien sv = ds.getSinhVien(vitri);
        naplaithongtin(sv);
    }
    private void naplaithongtin(SinhVien sv){
        tfMaso.setText(sv.getMSV());
        tfHoten.setText(sv.getHoten());
        cboMalop.setSelectedItem(sv.getML());
        tfDiemLT.setText(sv.getDLT()+"");
        tfDiemTH.setText(sv.getDTH()+"");
        tfDiemTB.setText(sv.diemTB()+"");
        tfKQ.setText(sv.KQ());
        tfMaso.requestFocus();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btnSua)) {
            
            SinhVien svVersion2  = new SinhVien(tfMaso.getText(),
                    cboMalop.getSelectedItem().toString(),
                    Double.parseDouble(tfDiemLT.getText()), 
                    Double.parseDouble(tfDiemLT.getText()));
            if (!ds.suaThongTinSinhVien(tfMaso.getText(), svVersion2)) {
                JOptionPane.showMessageDialog(null, "Không tồn tại sinh viên có mã số:" + tfMaso.getText());
                return; 
            }else napvaobang();
        }
        else if (e.getSource().equals(btnThem)){
            if (!ds.themSV(sv)) {
                    JOptionPane.showMessageDialog(null, "Trùng mã số");
                    return;
                }
                String[] dataRow = {sv.getMSV(), sv.getHoten(), sv.getML(), Double.toString(sv.getDLT()), sv.getDTH() + "", sv.diemTB() + "", sv.KQ()};
                dfModel.addRow(dataRow);
        }
        else if(e.getSource().equals(btnXoa)){
            
        }
           
//        if (e.getSource().equals(btnSua)) {
//            try{
//                SinhVien svVersion2 = new SinhVien(
//                        tfMaso.getText(), tfHoten.getText(),
//                        cboMalop.getSelectedItem().toString(),
//                        Double.parseDouble(tfDiemLT.getText()),
//                        Double.parseDouble(tfDiemTH.getText()));
//                if (!ds.suaThongTinSinhVien(tfMaso.getText(), svVersion2)) {
//                    JOptionPane.showMessageDialog(null, "Không tồn tại sinh viên có mã số:" + tfMaso.getText());
//                    return;
//
//                }else {
//                    napvaobang();
//                }
//            catch (Exception e) {
//                JOptionPane.showMessageDialog(null, "LỖi");
//                return;
//            }
//
//            }
//        }else if (e.getSource().equals(btnTim)) {
//            try {
//                String masv = JOptionPane.showInputDialog("Nhập mã số sinh viên cần tìm:");
//                if (masv != null && masv.trim().equals("")) {
//                    SinhVien sv = ds.timKiem(masv);
//                    if (sv != null) {
//                        naplaithongtin(sv);
//                    } else {
//                        JOptionPane.showMessageDialog(null, "Không tồn tại sinh viên có mã số:" + masv);
//                        return;
//                    }
//                }
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(null, "Lỗi");
//            }
//        } else if (e.getSource().equals(btnXoa)) {
//            try {
//                String masv = JOptionPane.showInputDialog("Nhập mã số sinh viên cần xóa:");
//                if (masv != null && masv.trim().equals("")) {
//                    SinhVien sv = ds.timKiem(masv);
//                    if (sv != null) {
//                        if (JOptionPane.showConfirmDialog(null, "Có chắn chắn muốn xóa không?", "Thông báo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
//                            ds.xoaSinhVien(masv);
//                            napvaobang();
//                        }
//                    } else {
//                        JOptionPane.showMessageDialog(null, "Không tồn tại mã sinh viên" + masv);
//                        return;
//                    }
//                }catch (Exception e) {
//                    JOptionPane.showMessageDialog(null, "Lỗi");
//                    return;
//                    }
//            }
//        } else if (e.getSource().equals(btnLuu)) {
//            try {
//                FileOutputStream out = new FileOutputStream(FILENAME);
//                ObjectOutputStream oos = new ObjectOutputStream(out);
//                oos.writeObject(ds);
//                oos.close();
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(null, "IO Error");
//            }
//        } else if (e.getSource().equals(btnClear)) {
//            tfMaso.setText("");
//            tfHoten.setText("");
//            tfDiemLT.setText("");
//            tfDiemTH.setText("");
//            tfKQ.setText("");
//            tfMaso.requestFocus();
//        } else {
//            String masv = "", hoten = "", malop = "";
//            double diemLT = 0.0, diemTH = 0.0;
//            SinhVien sv = null;
//            try {
//                masv = tfMaso.getText();
//                hoten = tfHoten.getText();
//                malop = cboMalop.getSelectedItem().toString();
//                diemLT = Double.parseDouble(tfDiemLT.getText());
//                diemTH = Double.parseDouble(tfDiemTH.getText());
//                SinhVien = new SinhVien(masv, hoten, malop, diemLT, diemTH);
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(null, "Lỗi");
//                return;
//            }
//            if (e.getSource().equals(btnThem)) {
//                if (!ds.themSV(sv)) {
//                    JOptionPane.showMessageDialog(null, "Trùng mã số");
//                    return;
//                }
//                String[] dataRow = {sv.getMSV(), sv.getHoten(), sv.getML(), Double.toString(sv.getDLT()), sv.getDTH() + "", sv.diemTB() + "", sv.KQ()};
//                dfModel.addRow(dataRow);
//            } else if (e.getSource().equals(btnKQ)) {
//                tfDiemTB.setText(sv.diemTB() + "");
//                tfKQ.setText(sv.KQ());
//            }
//        }
    }
}
