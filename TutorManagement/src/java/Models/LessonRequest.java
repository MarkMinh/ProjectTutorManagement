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
public class LessonRequest {

    private int id;
    private String username;
    private String receiverName;
    private String status;
    private String updateAt;
    private Date startTime;
    private Date endTime;

    public LessonRequest() {
    }

    public LessonRequest(int id, String username, String receiverName, String status, String updateAt, Date startTime, Date endTime) {
        this.id = id;
        this.username = username;
        this.receiverName = receiverName;
        this.status = status;
        this.updateAt = updateAt;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
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

    public User getRequester() {
        return DAO.INSTANCE.getUser(username);
    }

    public User getReceiver() {
        return DAO.INSTANCE.getUser(receiverName);
    }

    public boolean getCompareDate() {
        Date now = new java.util.Date();
        return getEndTime().before(now);
    }

}
