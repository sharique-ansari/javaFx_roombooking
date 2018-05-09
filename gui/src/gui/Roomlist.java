package gui;

import java.io.*;
import java.util.ArrayList;

public class Roomlist implements Serializable{
    private static final long serialVersionUID = -4591961618263319998L;
    public static void main(String[] args){

        rooms R1 = new rooms("C01",100);
        rooms R2 = new rooms("C02",50);
        rooms R3 = new rooms("C03",50);
        rooms R4 = new rooms("C11",100);
        rooms R5 = new rooms("C12",50);
        rooms R6 = new rooms("C13",50);
        rooms R7 = new rooms("C21",100);
        rooms R8 = new rooms("C22",50);
        rooms R9 = new rooms("C23",50);
        rooms R10 = new rooms("S01",50);
        rooms R11= new rooms("S02",50);
        rooms R12= new rooms("LR1",50);
        rooms R13= new rooms("LR2",50);
        rooms R14= new rooms("LR3",50);

        Roomlist RL1 =new Roomlist();
        RL1.AllRooms.add(R1);
        RL1.AllRooms.add(R2);
        RL1.AllRooms.add(R3);
        RL1.AllRooms.add(R4);
        RL1.AllRooms.add(R5);
        RL1.AllRooms.add(R6);
        RL1.AllRooms.add(R7);
        RL1.AllRooms.add(R8);
        RL1.AllRooms.add(R9);
        RL1.AllRooms.add(R10);
        RL1.AllRooms.add(R11);
        RL1.AllRooms.add(R12);
        RL1.AllRooms.add(R13);
        RL1.AllRooms.add(R14);

        RL1.WriteAllRooms("allrooms.ser",RL1);

    }
    ArrayList<rooms> AllRooms = new ArrayList<rooms>();

    public ArrayList<rooms> getAllRooms() {
        return AllRooms;
    }


    public Roomlist readAllrooms(String location, Roomlist _Roomlist){
        FileInputStream fis = null;
        ObjectInputStream in = null;
        try {
            fis = new FileInputStream(location);
            in = new ObjectInputStream(fis);
            _Roomlist =(Roomlist) in.readObject();
            in.close();
            return _Roomlist;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }
    public void WriteAllRooms(String location,Roomlist _roomlist){
        final FileOutputStream[] fos = {null};
        final ObjectOutputStream[] out = {null};
        try {
            fos[0] = new FileOutputStream(location);
            out[0] = new ObjectOutputStream(fos[0]);
            out[0].writeObject(_roomlist);
            out[0].close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
