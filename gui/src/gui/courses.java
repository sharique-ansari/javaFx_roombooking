package gui;

import java.io.Serializable;

public class courses implements Serializable{

    private static final long serialVersionUID = -4344407807891830532L;
    String name,code,instructor,acr,monday,tuesday,wednesday,thursday,friday,tutorial,lab;
    int credits;

    public String getTutorial() {
        return tutorial;
    }

    public String getLab() {
        return lab;
    }

    public courses(){
        name="default";
        code="XXXXXXX";
        instructor="default instructor";
        acr="DFL";

        credits=0;

    }
    public courses(String _name,String _code,String _ins, String _acr,int _cre,String _monday,String _tuesday,String _wednesday,String _thursday,String _friday,String _tutorial,String _lab){
        this.name=_name;this.credits=_cre;this.acr=_acr;this.instructor=_ins;this.code=_code;
        monday=add_day(_monday);
        tuesday=add_day(_tuesday);
        wednesday=add_day(_wednesday);
        thursday=add_day(_thursday);
        friday=_friday;
        tutorial=_tutorial;
        lab=_lab;
    }
    public String add_day(String s){
        if (!s.equals("0")){
            return s.split("$")[0];

        }return "-";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getMonday() {
        return monday;
    }

    public String getTuesday() {
        return tuesday;
    }

    public String getWednesday() {
        return wednesday;
    }

    public String getThursday() {
        return thursday;
    }

    public String getFriday() {
        return friday;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getAcr() {
        return acr;
    }

    public void setAcr(String acr) {
        this.acr = acr;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }
}
