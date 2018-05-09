package gui;

import java.io.*;
import java.util.ArrayList;

public class StudentList implements Serializable{
    private static final long serialVersionUID = -3319969817212653903L;

    public static void main(String[] args){
        Student_login a = new Student_login("shark098@iiitd.ac.in","admin");
        Student_login b = new Student_login("abc@iiitd.ac.in","admin");
        StudentList StL = new StudentList();
        StL.insertStudent(a);
        StL.insertStudent(b);
        final FileOutputStream[] fos = {null};
        final ObjectOutputStream[] out = {null};
        try {
            fos[0] = new FileOutputStream("time.ser");
            out[0] = new ObjectOutputStream(fos[0]);
            out[0].writeObject(StL);
            out[0].close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }
    ArrayList<Student_login> studentData = new ArrayList<Student_login>();
    public StudentList(){}

    public void insertStudent(Student_login st){
        studentData.add(st);
    }

    public ArrayList<Student_login> getStudentData() {
        return studentData;
    }

    public void setStudentData(ArrayList<Student_login> studentData) {
        this.studentData = studentData;
    }
    public StudentList readSL(String location,StudentList sl){
        FileInputStream fis = null;
        ObjectInputStream in = null;
        try {
            fis = new FileInputStream(location);
            in = new ObjectInputStream(fis);
            sl =(StudentList) in.readObject();
            in.close();
            return sl;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }
    public void WriteSL(String location,StudentList sl){
        final FileOutputStream[] fos = {null};
        final ObjectOutputStream[] out = {null};
        try {
            fos[0] = new FileOutputStream("time.ser");
            out[0] = new ObjectOutputStream(fos[0]);
            out[0].writeObject(sl);
            out[0].close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
