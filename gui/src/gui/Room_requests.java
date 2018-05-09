package gui;

import java.io.*;
import java.util.ArrayList;


public class Room_requests implements Serializable{
    private static final long serialVersionUID = 9124585637598404507L;
    public static void main(String[] args){
        rooms R1 = new rooms("demoC01",200);
        rooms R2 = new rooms("demoC02");
        Student_login S1 = new Student_login();
        Student_login S2 = new Student_login("shark","admin");
        request Rq1 =new  request(R1,S1,"dance practice",100);
        request Rq2 = new request(R2,S2,"Meme making competition",100);
        Room_requests RQ= new Room_requests();
        RQ.pendingReq.add(Rq1);
        RQ.approved.add(Rq2);
        RQ.UpdateRequests("request.ser",RQ);
    }
    ArrayList<request> pendingReq = new ArrayList<request>();
    ArrayList<request> approved = new ArrayList<request>();
    Room_requests(){}

    public Boolean getReqStatus(rooms _room) {
        if (approved.contains(_room)){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public Room_requests getRequests(String location,Room_requests RQ){
        FileInputStream fis = null;
        ObjectInputStream in = null;
        try {
            fis = new FileInputStream(location);
            in = new ObjectInputStream(fis);
            RQ =(Room_requests) in.readObject();
            in.close();
            return RQ;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }
    public void UpdateRequests(String location,Room_requests RQ){
        final FileOutputStream[] fos = {null};
        final ObjectOutputStream[] out = {null};
        try {
            fos[0] = new FileOutputStream(location);
            out[0] = new ObjectOutputStream(fos[0]);
            out[0].writeObject(RQ);
            out[0].close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
