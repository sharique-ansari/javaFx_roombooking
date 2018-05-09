package gui;

import java.io.Serializable;

public class request implements Serializable {
    private static final long serialVersionUID = 3698476780025263977L;
    Boolean status;
    Student_login user;
    Faculty_login fuser;
    String name ,roomname;
    rooms room;
    String purpose;
    int requiredCap;
    Boolean rejected;

    public request(rooms _room, Student_login _student,String _purpose,int _cap){
        user=_student;
        name = user.name;
        room=_room;
        roomname = _room.RoomID;
        status=Boolean.FALSE;
        purpose = _purpose;
        requiredCap = _cap;
        rejected=Boolean.FALSE;
    }
    public request(rooms _room, Faculty_login _fac){
        room =_room;
        fuser = _fac;
        name = _fac.name;
        roomname = room.RoomID;
        status = Boolean.TRUE;
        rejected=Boolean.FALSE;

    }

    public Faculty_login getFuser() {
        return fuser;
    }

    public void setFuser(Faculty_login fuser) {
        this.fuser = fuser;
    }

    public Boolean getRejected() {
        return rejected;
    }

    public void setRejected(Boolean rejected) {
        this.rejected = rejected;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Boolean getStatus() {
        return status;
    }

    public Student_login getUser() {
        return user;
    }

    public void setUser(Student_login user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoomname() {
        return roomname;
    }

    public void setRoomname(String roomname) {
        this.roomname = roomname;
    }

    public rooms getRoom() {
        return room;
    }

    public void setRoom(rooms room) {
        this.room = room;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public int getRequiredCap() {
        return requiredCap;
    }

    public void setRequiredCap(int requiredCap) {
        this.requiredCap = requiredCap;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
    public void Approve(){
        status=Boolean.TRUE;
        Room_requests RqL=new Room_requests();
        RqL.getRequests("request.ser",RqL);
        RqL.pendingReq.remove(this);
        RqL.approved.add(this);
        RqL.UpdateRequests("request.ser",RqL);
    }
}
