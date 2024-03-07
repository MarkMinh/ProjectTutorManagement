/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import DAL.DAO;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class Classes {

    private int id;
    private String studentName;
    private String tutorName;
    private double amountOneHour;
    private Date startTime;
    private Date endTime;
    private String status;
    private int requestDetailId;

    public Classes(int id, String studentName, String tutorName, double amountOneHour, Date startTime, Date endTime, String status, int requestDetailId) {
        this.id = id;
        this.studentName = studentName;
        this.tutorName = tutorName;
        this.amountOneHour = amountOneHour;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.requestDetailId = requestDetailId;
    }

    public int getRequestDetailId() {
        return requestDetailId;
    }

    public void setRequestDetailId(int requestDetailId) {
        this.requestDetailId = requestDetailId;
    }

    public Classes() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getTutorName() {
        return tutorName;
    }

    public void setTutorName(String tutorName) {
        this.tutorName = tutorName;
    }

    public double getAmountOneHour() {
        return amountOneHour;
    }

    public void setAmountOneHour(double amountOneHour) {
        this.amountOneHour = amountOneHour;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public RequestDetail getReqDetail() {
        return DAO.INSTANCE.getRequestDetailById(requestDetailId);
    }

}
