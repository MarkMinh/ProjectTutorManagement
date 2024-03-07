/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;



/**
 *
 * @author ADMIN
 */
public class Schedule {

    private int id;
    private int classId;
    private String dayOfWeek;
    private int timeSlot;

    public Schedule() {
    }

    public Schedule(int id, int classId, String dayOfWeek, int timeSlot) {
        this.id = id;
        this.classId = classId;
        this.dayOfWeek = dayOfWeek;
        this.timeSlot = timeSlot;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(int timeSlot) {
        this.timeSlot = timeSlot;
    }

}
