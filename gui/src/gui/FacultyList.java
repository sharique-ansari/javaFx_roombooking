package gui;

import java.io.*;
import java.util.ArrayList;

public class FacultyList implements Serializable{
	public FacultyList() {
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Faculty_login f1=new Faculty_login("admin@iiitd.ac.in","admin");
		FacultyList Fl=new FacultyList();
		Fl.insertFaculty(f1);
		 final FileOutputStream[] fos = {null};
	        final ObjectOutputStream[] out = {null};
	        try {
	            fos[0] = new FileOutputStream("faculty.ser");
	            out[0] = new ObjectOutputStream(fos[0]);
	            out[0].writeObject(Fl);
	            out[0].close();
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	

	}
	ArrayList<Faculty_login> FacultyData=new ArrayList<Faculty_login>();
	 public void insertFaculty(Faculty_login st){
	        FacultyData.add(st);
	    }

	    public ArrayList<Faculty_login> getStudentData() {
	        return FacultyData;
	    }

	    public void setStudentData(ArrayList<Faculty_login> facultyData) {
	        this.FacultyData = facultyData;
	    }
	    public FacultyList readSL(String location, FacultyList sl){
	        FileInputStream fis = null;
	        ObjectInputStream in = null;
	        try {
	            fis = new FileInputStream(location);
	            in = new ObjectInputStream(fis);
	            sl =(FacultyList) in.readObject();
	            in.close();
	            return sl;
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            return null;
	        }

	    }
	    public void WriteSL(String location, FacultyList sl){
	        final FileOutputStream[] fos = {null};
	        final ObjectOutputStream[] out = {null};
	        try {
	            fos[0] = new FileOutputStream("faculty.ser");
	            out[0] = new ObjectOutputStream(fos[0]);
	            out[0].writeObject(sl);
	            out[0].close();
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    }

}
