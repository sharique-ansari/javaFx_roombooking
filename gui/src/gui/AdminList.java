package gui;

import java.io.*;
import java.util.ArrayList;

public class AdminList implements Serializable{
    public AdminList() {

    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Admin_login f1=new Admin_login("admin@iiitd.ac.in","admin");
        AdminList Fl=new AdminList();
        Fl.insertAdmin(f1);
        final FileOutputStream[] fos = {null};
        final ObjectOutputStream[] out = {null};
        try {
            fos[0] = new FileOutputStream("admin.ser");
            out[0] = new ObjectOutputStream(fos[0]);
            out[0].writeObject(Fl);
            out[0].close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }
    ArrayList<Admin_login> AdminData=new ArrayList<Admin_login>();
    public void insertAdmin(Admin_login st){
        AdminData.add(st);
    }


    public AdminList readSL(String location, AdminList sl){
        FileInputStream fis = null;
        ObjectInputStream in = null;
        try {
            fis = new FileInputStream(location);
            in = new ObjectInputStream(fis);
            sl =(AdminList) in.readObject();
            in.close();
            return sl;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }
    public void WriteSL(String location, AdminList sl){
        final FileOutputStream[] fos = {null};
        final ObjectOutputStream[] out = {null};
        try {
            fos[0] = new FileOutputStream("admin.ser");
            out[0] = new ObjectOutputStream(fos[0]);
            out[0].writeObject(sl);
            out[0].close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}

