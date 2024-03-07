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
public class RequestDetail {

    private int id;
    private String username;
    private int gradeLevel;
    private double hourlyMinRate;
    private double hourlyMaxRate;
    private String description;
    private String requirement;
    private Date startTime;
    private Date updateTime;
    private Date endTime;
    private int subjectId;
    private int learningType;

    public RequestDetail() {
    }

    public RequestDetail(int id, String username, int gradeLevel, double hourlyMinRate, double hourlyMaxRate, String description, String requirement, Date startTime, Date updateTime, Date endTime, int subjectId, int learningType) {
        this.id = id;
        this.username = username;
        this.gradeLevel = gradeLevel;
        this.hourlyMinRate = hourlyMinRate;
        this.hourlyMaxRate = hourlyMaxRate;
        this.description = description;
        this.requirement = requirement;
        this.startTime = startTime;
        this.updateTime = updateTime;
        this.endTime = endTime;
        this.subjectId = subjectId;
        this.learningType = learningType;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getLearningType() {
        return learningType;
    }

    public void setLearningType(int learningType) {
        this.learningType = learningType;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(int gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    public double getHourlyMinRate() {
        return hourlyMinRate;
    }

    public void setHourlyMinRate(double hourlyMinRate) {
        this.hourlyMinRate = hourlyMinRate;
    }

    public double getHourlyMaxRate() {
        return hourlyMaxRate;
    }

    public void setHourlyMaxRate(double hourlyMaxRate) {
        this.hourlyMaxRate = hourlyMaxRate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUserR() {
        return DAO.INSTANCE.getUser(username);
    }

    public String getSubjectName() {
        return DAO.INSTANCE.getSubjectName(subjectId);
    }

}
