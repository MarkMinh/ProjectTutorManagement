/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import DAL.DAO;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class User {

    private String username;
    private int gender;
    private String dob;
    private String email;
    private String password;
    private String phoneNumber;
    private String img;
    private int rollNo;
    private Date createAt;
    private Date updateAt;
    private String fullName;
    private int provinceId;
    private int districtId;
    private int wardId;
    private int isActive;
    private String question;
    private int questionType;

    public User() {
    }

    public User(String username, int gender, String dob, String email, String password, String phoneNumber, String img, int rollNo, Date createAt, Date updateAt, String fullName, int provinceId, int districtId, int wardId, int isActive, String question, int questionType) {
        this.username = username;
        this.gender = gender;
        this.dob = dob;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.img = img;
        this.rollNo = rollNo;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.fullName = fullName;
        this.provinceId = provinceId;
        this.districtId = districtId;
        this.wardId = wardId;
        this.isActive = isActive;
        this.question = question;
        this.questionType = questionType;
    }

    public int getQuestionType() {
        return questionType;
    }

    public void setQuestionType(int questionType) {
        this.questionType = questionType;
    }

    

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }   
    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    public int getWardId() {
        return wardId;
    }

    public void setWardId(int wardId) {
        this.wardId = wardId;
    }

    public Province getProvince() {
        return DAO.INSTANCE.getProvince(provinceId);
    }

    public District getDistrict() {
        return DAO.INSTANCE.getDistrict(districtId);
    }

    public Ward getWard() {
        return DAO.INSTANCE.getWard(wardId);
    }

    



}
