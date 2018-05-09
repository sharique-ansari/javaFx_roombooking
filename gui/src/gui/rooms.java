package gui;

import java.io.*;
import java.util.ArrayList;

public class rooms implements Serializable{
    private static final long serialVersionUID = 7428769381125432776L;
    String RoomID;
    int capacity;
    Boolean booked;
    ArrayList<String> time_booked = new ArrayList<String>();
    public rooms(String _id){
        RoomID=_id;
        capacity=404;
    }
    public rooms(String _id,int _capacity){
        RoomID=_id;
        capacity=_capacity;
    }

    public String getRoomID() {
        return RoomID;
    }

    public void setRoomID(String roomID) {
        RoomID = roomID;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public ArrayList<String> getTime_booked() {
        return time_booked;
    }

    public void setTime_booked(ArrayList<String> time_booked) {
        this.time_booked = time_booked;
    }


}

