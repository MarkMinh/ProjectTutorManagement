/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.Province;
import Models.*;
import Models.User;
import java.security.MessageDigest;
import java.sql.Connection;
import java.util.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class DAO {
    
    public static DAO INSTANCE = new DAO();
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    private String status;
    private List<User> userList;
    
    public DAO() {
        if (INSTANCE == null) {
            try {
                conn = new DBContext().getConnection();
            } catch (Exception e) {
                status = "Error at Connection" + e.getMessage();
            }
        }
    }
    
    public List<User> getUserList() {
        return userList;
    }

//    public void loadDB() {
//        String sql = "select * FROM Student";
//        userList = new Vector<User>();
//        try {
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                userList.add(new User(
//                        rs.getInt(1),
//                        rs.getString(2),
//                        rs.getInt(3),
//                        rs.getString(4),
//                        rs.getString(5),
//                        rs.getString(6),
//                        rs.getString(7),
//                        rs.getString(8),
//                        rs.getInt(9),
//                        rs.getDate(10),
//                        rs.getDate(11),
//                        rs.getString(12),
//                        rs.getInt(13),
//                        rs.getInt(14),
//                        rs.getInt(15),
//                        rs.getInt(16)
//                ));
//            }
//        } catch (Exception e) {
//            status = "Error at read Student " + e.getMessage();
//        }
//
//    }
    public ArrayList<Province> getAllProvince() {
        ArrayList<Province> list = new ArrayList<>();
        String query = "select * from ProvinceHE171380";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Province(
                        rs.getInt(1),
                        rs.getString(2)
                )
                );
            }
        } catch (Exception e) {
            status = "Error at read Student " + e.getMessage();
        }
        return list;
    }
    
    public ArrayList<District> getAllDistrict() {
        ArrayList<District> list = new ArrayList<>();
        String query = "select * from DistrictHE171380";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new District(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3)
                )
                );
            }
        } catch (Exception e) {
            status = "Error at read District " + e.getMessage();
        }
        return list;
    }
    
    public ArrayList<Ward> getAllWard() {
        ArrayList<Ward> list = new ArrayList<>();
        String query = "select * from WardHE171380";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Ward(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3)
                )
                );
            }
        } catch (Exception e) {
            status = "Error at read Student " + e.getMessage();
        }
        return list;
    }
    
    public ArrayList<District> getAllDistrictByProvinceId(int id) {
        ArrayList<District> list = new ArrayList<>();
        String query = "select * from DistrictHE171380 where provinceId = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new District(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3)
                )
                );
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    public ArrayList<Ward> getAllWardByDistrictId(int id) {
        ArrayList<Ward> list = new ArrayList<>();
        String query = "select * from WardHE171380 where districtId = ?";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Ward(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3)
                )
                );
            }
        } catch (Exception e) {
        }
        return list;
    }

//    public ArrayList<User> getAllUser() {
//        ArrayList<User> list = new ArrayList<>();
//        String query = "select * from UserHE171380";
//        try {
//            conn = new DBContext().getConnection();//mo ket noi voi sql
//            ps = conn.prepareStatement(query);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                list.add(new User(
//                        rs.getInt(1),
//                        rs.getString(2),
//                        rs.getInt(3),
//                        rs.getString(4),
//                        rs.getString(5),
//                        rs.getString(6),
//                        rs.getString(7),
//                        rs.getString(8),
//                        rs.getInt(9),
//                        rs.getDate(10),
//                        rs.getDate(11),
//                        rs.getString(12),
//                        rs.getInt(13),
//                        rs.getInt(14),
//                        rs.getInt(15),
//                        rs.getInt(16)
//                ));
//            }
//        } catch (Exception e) {
//        }
//        return list;
//    }
    public ArrayList<String> loadAttributeNames() {
        ArrayList<String> attributeNames = new ArrayList<>();
        String sql = "SELECT COLUMN_NAME\n"
                + "FROM INFORMATION_SCHEMA.COLUMNS\n"
                + "WHERE TABLE_NAME = 'UserHE171380' AND TABLE_SCHEMA = 'dbo'";
        try {
            
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String attributeName = rs.getString(1);
                attributeNames.add(attributeName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return attributeNames;
    }
    
    public User login(String username, String pass) {
        String sql = "SELECT * from UserHE171380 where [username] = ? and [password] = ? ";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new User(
                        rs.getString(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getDate(9),
                        rs.getDate(10),
                        rs.getString(11),
                        rs.getInt(12),
                        rs.getInt(13),
                        rs.getInt(14),
                        rs.getInt(15),
                        rs.getString(16),
                        rs.getInt(17)
                );
            }
        } catch (Exception e) {
        }
        return null;
    }

//    public Tutor loginTutorByUser(int id) {
//        String sql = "SELECT * from TutorHE171380 where [userid] = ? ";
//        try {
//            conn = new DBContext().getConnection();//mo ket noi voi sql
//            ps = conn.prepareStatement(sql);
//            ps.setInt(1, id);
//
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                return new Tutor(
//                        rs.getInt(1),
//                        rs.getInt(2),
//                        rs.getDouble(3),
//                        rs.getDouble(4));
//            }
//        } catch (Exception e) {
//            status = "Error at read Tutor" + e.getMessage();
//        }
//        return null;
//    }
//    public Student loginStudentByUser(int id) {
//        String sql = "SELECT * from StudentHE171380 where [userid] = ? ";
//        try {
//            conn = new DBContext().getConnection();//mo ket noi voi sql
//            ps = conn.prepareStatement(sql);
//            ps.setInt(1, id);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                return new Student(
//                        rs.getInt(1),
//                        rs.getInt(2),
//                        rs.getInt(3),
//                        rs.getDouble(4),
//                        rs.getDouble(5),
//                        rs.getString(6));
//            }
//        } catch (Exception e) {
//            status = "Error at read Student" + e.getMessage();
//        }
//        return null;
//    }
    public User checkAccountExist(String user) {
        String sql = "SELECT * from UserHE171380 where [username] = ? ";
        try {
            conn = new DBContext().getConnection();//mo ket noi voi sql
            ps = conn.prepareStatement(sql);
            ps.setString(1, user);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new User(
                        rs.getString(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getDate(9),
                        rs.getDate(10),
                        rs.getString(11),
                        rs.getInt(12),
                        rs.getInt(13),
                        rs.getInt(14),
                        rs.getInt(15),
                        rs.getString(16),
                        rs.getInt(17));
                
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    public void signUp(String user, String password, int rollNo, String question, String email, String createAt, String updateAt, String img, int questionType) {
        String sql = "Insert into UserHE171380(username,password,rollNo,question,email,createAt,updateAt,img,questionType) values(?,?,?,?,?,?,?,?,?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, password);
            ps.setInt(3, rollNo);
            ps.setString(4, question);
            ps.setString(5, email);
            ps.setString(6, createAt);
            ps.setString(7, updateAt);
            ps.setString(8, img);
            ps.setInt(9, questionType);
            ps.execute();
        } catch (Exception e) {
            System.out.println("sign up" + e);
        }
    }
    
    public void Insert(String user, int gender, String dob, String email, String password, String phoneNumber, String img, int rollNo,
            String createAt, String updateAt, String fullName, int provinceId, int districtId, int wardId, int isActive) {
        String sql = "Insert into UserHE171380 values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user);
            ps.setInt(2, gender);
            ps.setString(3, dob);
            ps.setString(4, email);
            ps.setString(5, password);
            ps.setString(6, phoneNumber);
            ps.setString(7, img);
            ps.setInt(8, rollNo);
            ps.setString(9, createAt);
            ps.setString(10, updateAt);
            ps.setString(11, fullName);
            ps.setInt(12, provinceId);
            ps.setInt(13, districtId);
            ps.setInt(14, wardId);
            ps.setInt(15, isActive);
            ps.execute();
        } catch (Exception e) {
            status = "Error at read User " + e.getMessage();
        }
    }
    
    public void insertRequestDetail(String username, int gradeLevel, double hourlyMinRate, double hourlyMaxRate, String description) {
        String sql = "Insert into RequestDetailHE171380 values(?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setInt(2, gradeLevel);
            ps.setDouble(3, hourlyMinRate);
            ps.setDouble(4, hourlyMaxRate);
            ps.setString(5, description);
            ps.execute();
        } catch (Exception e) {
            status = "Error at insert RequestDetail " + e.getMessage();
        }
    }
    
    public void insertRequest(String username, int gradeLevel, double hourlyMinRate, double hourlyMaxRate, String description, String requirement,
            String startTime, String updateTime, int subjectId, int learningType) {
        String sql = "Insert into RequestDetailHE171380(username, gradeLevel, hourlyMinRate, hourlyMaxRate, description,"
                + " requirement, startTime, updateTime, subjectId, learningType) values(?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setInt(2, gradeLevel);
            ps.setDouble(3, hourlyMinRate);
            ps.setDouble(4, hourlyMaxRate);
            ps.setString(5, description);
            ps.setString(6, requirement);
            ps.setString(7, startTime);
            ps.setString(8, updateTime);
            ps.setInt(9, subjectId);
            ps.setInt(10, learningType);
            ps.execute();
        } catch (Exception e) {
            status = "Error at read UserAvailability " + e.getMessage();
        }
    }
    
    public void insertSchedule(int classId, String dayOfWeek, int timeSlot) {
        String sql = "Insert into ScheduleHE171380 values(?,?,?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, classId);
            ps.setString(2, dayOfWeek);
            ps.setInt(3, timeSlot);
            ps.execute();
        } catch (Exception e) {
            System.out.println("Schedule fail" + e);
        }
    }
    
    public RequestDetail getRequestDetailById(int id) {
        String sql = "SELECT * FROM RequestDetailHE171380 where id = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new RequestDetail(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getDouble(4),
                        rs.getDouble(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getDate(8),
                        rs.getDate(9),
                        rs.getDate(10),
                        rs.getInt(11),
                        rs.getInt(12)
                );
            }
        } catch (Exception e) {
            System.out.println("request Detail" + e);
        }
        return null;
    }
    
    public ArrayList<Schedule> getScheduleByClassId(int classId) {
        ArrayList<Schedule> list = new ArrayList<>();
        String sql = "SELECT * FROM ScheduleHE171380 where classId = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, classId);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Schedule(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getInt(4))
                );
            }
        } catch (Exception e) {
            System.out.println("request Detail" + e);
        }
        return list;
    }
    
    public ArrayList<Subject> loadAllSubjects() {
        String sql = "SELECT * from SubjectHE171380";
        ArrayList<Subject> list = new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Subject(
                        rs.getInt(1),
                        rs.getString(2)));
            }
        } catch (Exception e) {
            status = "Error at read Subject " + e.getMessage();
        }
        return list;
    }
    
    public ArrayList<String> getTimeSlot(String username) {
        String sql = "SELECT dayOfWeek, timeSlot FROM ClassHE171380 c \n"
                + "join RequestDetailHE171380 rd \n"
                + "on c.requestDetailId = rd.id\n"
                + "join ScheduleHE171380 s\n"
                + "on s.classId = c.id\n"
                + "where tutorName = ? and c.endTime is null";
        ArrayList<String> list = new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString(1) + "," + rs.getInt(2));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    public ArrayList<String> loadAllDayAvail(String username) {
        String sql = "SELECT dayOfWeeks from UserAvailabilityHE171380 where username = ?";
        ArrayList<String> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString(1));
            }
            return list;
        } catch (Exception e) {
            status = "Error at read UserAvail " + e.getMessage();
        }
        return null;
    }
    
    public ArrayList<String> loadAllSubjectByName(String username) {
        String sql = "SELECT subject from UserSubjectHE171380 where username = ?";
        ArrayList<String> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString(1));
            }
            return list;
        } catch (Exception e) {
            status = "Error at read UserSubject " + e.getMessage();
        }
        return null;
    }
    
    public void deleteUserAvailability(String username) {
        String sql = "Delete from UserAvailabilityHE171380 where username = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.execute();
        } catch (Exception e) {
            status = "Error at read UserAvail " + e.getMessage();
        }
    }
    
    public void insertUserSubject(String username, String subject) {
        String sql = "Insert into UserSubjectHE171380 values(?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, subject);
            ps.execute();
        } catch (Exception e) {
            status = "Error at read UserSubject " + e.getMessage();
        }
    }
    
    public void deleteUserSubject(String username) {
        String sql = "Delete from UserSubjectHE171380 where username = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.execute();
        } catch (Exception e) {
            status = "Error at read UserSubject " + e.getMessage();
        }
    }
    
    public void Update(int gender, String dob, String email, String phoneNumber, String img,
            String updateAt, String fullName, int provinceId, int districtId, int wardId, String username, int isActive) {
        String sql = "Update UserHE171380 set gender = ?, dob = ?, email = ?,"
                + " phoneNumber=?, img=?,updateAt=?,fullName=?,provinceId=?,districtId=?,wardId=?, isActive = ? where [username] = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, gender);
            ps.setString(2, dob);
            ps.setString(3, email);
            ps.setString(4, phoneNumber);
            ps.setString(5, img);
            ps.setString(6, updateAt);
            ps.setString(7, fullName);
            ps.setInt(8, provinceId);
            ps.setInt(9, districtId);
            ps.setInt(10, wardId);
            ps.setInt(11, isActive);
            ps.setString(12, username);
            ps.execute();
        } catch (Exception e) {
            status = "Error at read Update user " + e.getMessage();
        }
    }

//    public UserDetail getUserDetail(int userId) {
//        String sql = "Select * from UserDetailHE171380 where userId = ?";
//        try {
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ps.setInt(1, userId);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                return new UserDetail(
//                        rs.getInt(1),
//                        rs.getInt(2),
//                        rs.getDouble(3),
//                        rs.getDouble(4),
//                        rs.getString(5),
//                        rs.getInt(6)
//                );
//            }
//        } catch (Exception e) {
//        }
//        return null;
//    }
    public User getUser(String username) {
        String sql = "Select * from UserHE171380 where username = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new User(
                        rs.getString(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getDate(9),
                        rs.getDate(10),
                        rs.getString(11),
                        rs.getInt(12),
                        rs.getInt(13),
                        rs.getInt(14),
                        rs.getInt(15),
                        rs.getString(16),
                        rs.getInt(17)
                );
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
    public ArrayList<User> getAllUser() {
        ArrayList<User> list = new ArrayList<>();
        String sql = "Select * from UserHE171380";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new User(
                        rs.getString(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getDate(9),
                        rs.getDate(10),
                        rs.getString(11),
                        rs.getInt(12),
                        rs.getInt(13),
                        rs.getInt(14),
                        rs.getInt(15),
                        rs.getString(16),
                        rs.getInt(17)
                ));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    public void deleteAccount(String username) {
        String sql = "DELETE FROM UserHE171380 WHERE username = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void updateRequestDetail(int gradeLevel, double hourlyMinRate, double hourlyMaxRate, String description, String requirement,
            String updateTime, int subjectId, int learningType, int id) {
        String sql = "Update RequestDetailHE171380 set gradeLevel = ?, hourlyMinRate = ?, hourlyMaxRate = ?, description = ?"
                + ", requirement = ?, updateTime = ?, subjectId = ?, learningType = ? where id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, gradeLevel);
            ps.setDouble(2, hourlyMinRate);
            ps.setDouble(3, hourlyMaxRate);
            ps.setString(4, description);
            ps.setString(5, requirement);
            ps.setString(6, updateTime);
            ps.setInt(7, subjectId);
            ps.setInt(8, learningType);
            ps.setInt(9, id);
            ps.execute();
        } catch (Exception e) {
            status = "Error at read RequestDetail " + e.getMessage();
        }
    }
    
    public void deleteUserDetail(String endTime, int id) {
        String sql = "Update RequestDetailHE171380 set endTime = ? where id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, endTime);
            ps.setInt(2, id);
            ps.execute();
        } catch (Exception e) {
            status = "Error at read UserDetail " + e.getMessage();
        }
    }
    
    public void deleteLessonRequest(String username) {
        String sql = "DELETE FROM LessonRequestHE171380 WHERE username = ? OR receiverName = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, username);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void deleteRequestDetail(String username) {
        String sql = "DELETE FROM RequestDetailHE171380 WHERE username = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void updateStudent(int gradeLevel, double hourlyMinRate, double hourlyMaxRate, String description, int id) {
        String sql = "Update StudentHE171380 set gradeLevel = ?, hourlyMinRate = ?, hourlyMaxRate = ?, description = ? where id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, gradeLevel);
            ps.setDouble(2, hourlyMinRate);
            ps.setDouble(3, hourlyMaxRate);
            ps.setString(4, description);
            ps.setInt(5, id);
            ps.execute();
        } catch (Exception e) {
            status = "Error at read Student " + e.getMessage();
        }
    }
    
    public void UpdateAvatar(String img, String username) {
        String sql = "Update UserHE171380 set img = ? where username = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, img);
            ps.setString(2, username);
            ps.execute();
        } catch (Exception e) {
            status = "Error at upload Avatar " + e.getMessage();
        }
    }
    
    public void UpdatePassword(String password, String username) {
        String sql = "Update UserHE171380 set password = ? where username = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, password);
            ps.setString(2, username);
            ps.execute();
        } catch (Exception e) {
            status = "Error at upload Password " + e.getMessage();
        }
    }
    
    public ArrayList<LessonRequest> getRequested(ArrayList<LessonRequest> listAllRequests, String username) {
        
        ArrayList<LessonRequest> list = new ArrayList<>();
        for (LessonRequest l : listAllRequests) {
            if ((l.getReceiverName().equals(username)) && l.getEndTime() == null) {
                list.add(l);
            }
        }
        return list;
    }
    
    public void denyAll(String username, String endTime, String status) {
        String sql = "Update LessonRequestHE171380 set endTime = ?, status = ? where receiverName = ? and endTime is null";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, endTime);
            ps.setString(2, status);
            ps.setString(3, username);
            ps.execute();
        } catch (Exception e) {
            status = "Error at read UserDetail " + e.getMessage();
        }
        
    }
    
    public ArrayList<LessonRequest> getAllRequestedByUser(String receiverName) {
        ArrayList<LessonRequest> list = new ArrayList<>();
        String sql = "SELECT * FROM LessonRequestHE171380\n"
                + "where receiverName = ? AND endTime is null";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, receiverName);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new LessonRequest(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getDate(6),
                        rs.getDate(7)
                )
                );
            }
        } catch (Exception e) {
            status = "Error at read UserDetail " + e.getMessage();
        }
        
        return list;
    }
    
    public ArrayList<RequestDetail> loadAllDetails() {
        ArrayList<RequestDetail> list = new ArrayList<>();
        String sql = "SELECT * FROM RequestDetailHE171380 where endTime is null";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new RequestDetail(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getDouble(4),
                        rs.getDouble(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getDate(8),
                        rs.getDate(9),
                        rs.getDate(10),
                        rs.getInt(11),
                        rs.getInt(12)
                )
                );
            }
        } catch (Exception e) {
            status = "Error at read RequestDetail " + e.getMessage();
        }
        return list;
    }
    
    public ArrayList<RequestDetail> loadAllMyRequest(String username) {
        ArrayList<RequestDetail> list = new ArrayList<>();
        String sql = "SELECT * FROM RequestDetailHE171380 where username = ? and endTime is null";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new RequestDetail(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getDouble(4),
                        rs.getDouble(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getDate(8),
                        rs.getDate(9),
                        rs.getDate(10),
                        rs.getInt(11),
                        rs.getInt(12)
                )
                );
            }
        } catch (Exception e) {
            status = "Error at read RequestDetail " + e.getMessage();
        }
        return list;
    }
    
    public int insertLessonRequest(String username, String receiverName, String status, String updateAt,
            String startTime, String endTime) {
        String sql = "Insert into LessonRequestHE171380 values(?,?,?,?,?,?)";
        int generatedId = -1; // Initialize with a default value

        try {
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, username);
            ps.setString(2, receiverName);
            ps.setString(3, status);
            ps.setString(4, updateAt);
            ps.setString(5, startTime);
            ps.setString(6, endTime);
            ps.execute();
            
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                generatedId = rs.getInt(1); // Lấy ID được tạo tự động
            }
        } catch (Exception e) {
            status = "Error at read LessonRequest " + e.getMessage();
        }
        
        return generatedId; // Trả về ID được tạo tự động
    }

//    public void updateLessonRequest(String status, String updateAt, int learningType,
//            String endTime, int id) {
//        String sql = "Update LessonRequestHE171380 set status = ?, updateAt = ?, learningType = ?, endTime = ? where id = ?";
//        try {
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ps.setString(1, status);
//            ps.setString(2, updateAt);
//            ps.setInt(3, learningType);
//            ps.setString(4, endTime);
//            ps.setInt(5, id);
//            ps.execute();
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//    }
    public int updateLessonRequest(String status, String updateAt, String endTime, String username, String receiverName) {
        String sql = "UPDATE LessonRequestHE171380 SET status=?, updateAt=?, endTime=? WHERE username=? and receiverName = ? and endTime is null";
        int affectedRows = 0; // Số bản ghi bị ảnh hưởng
        int updatedId = -1; // Initialize with a default value

        try {
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, status);
            ps.setString(2, updateAt);
            ps.setString(3, endTime);
            ps.setString(4, username);
            ps.setString(5, receiverName);
            affectedRows = ps.executeUpdate();
            
            if (affectedRows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    updatedId = rs.getInt(1); // Lấy ID của bản ghi sau khi cập nhật
                }
            }
        } catch (Exception e) {
            status = "Error at update LessonRequest " + e.getMessage();
        }
        
        return updatedId; // Trả về ID của bản ghi sau khi cập nhật
    }
    
    public LessonRequest getLessonRequest(String username, String receiverName) {
        String sql = "SELECT * FROM LessonRequestHE171380 where username = ? and receiverName = ? and endTime is null";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, receiverName);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new LessonRequest(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getDate(6),
                        rs.getDate(7)
                );
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    public ArrayList<LessonRequest> getMyLessonRequest(String username) {
        ArrayList<LessonRequest> list = new ArrayList<>();
        String sql = "SELECT * FROM LessonRequestHE171380 where username = ? and endTime is null";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new LessonRequest(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getDate(6),
                        rs.getDate(7)
                ));
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    public LessonRequest getLessonRequestById(int id) {
        String sql = "SELECT * FROM LessonRequestHE171380 where id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new LessonRequest(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getDate(6),
                        rs.getDate(7)
                );
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    public ArrayList<LessonRequest> getAllLessonRequestById(String username, String receiverName) {
        ArrayList<LessonRequest> list = new ArrayList<>();
        String sql = "Select * from LessonRequestHE171380 where username = ? and receiverName = ? AND endTime == null";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, receiverName);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new LessonRequest(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getDate(6),
                        rs.getDate(7)
                ));
            }
        } catch (Exception e) {
        }
        return list;
    }

//    public ArrayList<UserDetail> loadUserDetail(int rollNo) {
//        ArrayList<UserDetail> list = new ArrayList<>();
//        String sql = "SELECT * from \n"
//                + "UserDetailHE171380 ud\n"
//                + "join UserHE171380 u\n"
//                + "on ud.userId = u.id\n"
//                + "where rollNo = ? AND mode = 1";
//        
//        try {
//            ps = conn.prepareStatement(sql);
//            ps.setInt(1, rollNo);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                list.add(new UserDetail(
//                        rs.getInt(1),
//                        rs.getInt(2),
//                        rs.getDouble(3),
//                        rs.getDouble(4),
//                        rs.getString(5),
//                        rs.getInt(6)
//                )
//                );
//            }
//        } catch (Exception e) {
//            status = "Error at read UserDetail " + e.getMessage();
//        }
//        return list;
//    }
    public Province getProvince(int provinceId) {
        String sql = "Select * from ProvinceHE171380 where id = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, provinceId);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Province(
                        rs.getInt(1),
                        rs.getString(2)
                );
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
        
    }
    
    public District getDistrict(int districtId) {
        String sql = "Select * from DistrictHE171380 where id = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, districtId);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new District(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3)
                );
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
        
    }
    
    public Ward getWard(int wardId) {
        String sql = "Select * from WardHE171380 where id = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, wardId);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Ward(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3)
                );
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
    public String getSubjectName(int subjectId) {
        String sql = "Select [name] from SubjectHE171380 where id=?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, subjectId);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println("subject err" + e);
        }
        return null;
    }
    
    public ArrayList<String> getSubjectByTutor(String tutorName) {
        String sql = "SELECT s.[name] FROM RequestDetailHE171380 r join SubjectHE171380 s\n"
                + "on r.subjectId = s.id \n"
                + "join ClassHE171380 c \n"
                + "on c.requestDetailId = r.id \n"
                + "where c.tutorName = ?";
        ArrayList<String> list = new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, tutorName);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString(1));
            }
        } catch (Exception e) {
            System.out.println("subject err" + e);
        }
        return list;
    }
    
    public ArrayList<String> getStudentNameByTutor(String tutorName) {
        String sql = "SELECT studentName FROM ClassHE171380 where tutorName = ?";
        ArrayList<String> list = new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, tutorName);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString(1));
            }
        } catch (Exception e) {
            System.out.println("subject err" + e);
        }
        return list;
    }
    
    public void insertFeedBack(String tutorName, String stdName, int rating, String comment) {
        String sql = "Insert into TutorRatingHE171380 values(?,?,?,?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, tutorName);
            ps.setString(2, stdName);
            ps.setInt(3, rating);
            ps.setString(4, comment);
            ps.execute();
        } catch (Exception e) {
            System.out.println("Feedback loi " + e);
        }
    }
    
    public void updateFeedBack(String tutorName, String stdName, int rating, String comment) {
        String sql = "Update TutorRatingHE171380 set rating = ?, comment = ? where username=? and ratedName=?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, rating);
            ps.setString(2, comment);
            ps.setString(3, tutorName);
            ps.setString(4, stdName);
            ps.execute();
        } catch (Exception e) {
            System.out.println("update feedback fail " + e);
        }
    }
    
    public TutorRating getFeedBack(String tutorName, String stdName) {
        String sql = "SELECT * FROM TutorRatingHE171380 where username = ? and ratedName = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, tutorName);
            ps.setString(2, stdName);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new TutorRating(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5));
            }
        } catch (Exception e) {
            System.out.println("feedback err" + e);
        }
        return null;
    }
    
    public ArrayList<TutorRating> getFeedBack(String tutorName) {
        String sql = "SELECT * FROM TutorRatingHE171380 where username = ?";
        ArrayList<TutorRating> list = new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, tutorName);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new TutorRating(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5)
                ));
            }
        } catch (Exception e) {
            System.out.println("feedback err" + e);
        }
        return list;
    }
    
    public double getRating(String tutorName) {
        String sql = "SELECT rating from TutorRatingHE171380 where username = ?";
        ArrayList<Double> list = new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, tutorName);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getDouble(1)
                );
            }
        } catch (Exception e) {
            System.out.println("feedback err" + e);
        }
        double sum = 0;
        for (Double o : list) {
            sum += o;
        }
        
        return sum / list.size();
    }
    
    public int getCountRating(String tutorName) {
        String sql = "SELECT COUNT(rating) from TutorRatingHE171380 where username = ?";     
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, tutorName);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("feedback err" + e);
        }    
        return 0;
    }
    
    public int insertClass(String studentName, String tutorName, double amountOneHour, String startTime, String status, int requestDetailId) {
        String sql = "INSERT INTO ClassHE171380(studentName, tutorName, amountOneHour, startTime, status, requestDetailId) VALUES(?,?, ?, ?, ?, ?)";
        try {
            // Thay đổi loại PreparedStatement để có thể truy xuất generated keys
            ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, studentName);
            ps.setString(2, tutorName);
            ps.setDouble(3, amountOneHour);
            ps.setString(4, startTime);
            ps.setString(5, status);
            ps.setInt(6, requestDetailId);
            
            int affectedRows = ps.executeUpdate();
            
            if (affectedRows == 0) {
                throw new SQLException("Creating class failed, no rows affected.");
            }

            // Lấy ResultSet chứa các generated keys
            ResultSet generatedKeys = ps.getGeneratedKeys();
            
            if (generatedKeys.next()) {
                int generatedId = generatedKeys.getInt(1); // Lấy ID của class
                return generatedId;
            } else {
                throw new SQLException("Creating class failed, no ID obtained.");
            }
        } catch (SQLException e) {
            status = "Error at read Class " + e.getMessage();
        }
        return -1; // Trả về -1 nếu có lỗi
    }
    
    public Classes getClassById(int id) {
        String sql = "Select * from ClassHE171380 where id = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Classes(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getDate(5),
                        rs.getDate(6),
                        rs.getString(7),
                        rs.getInt(8)
                );
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
    public ArrayList<Classes> getAllClassByUsername(String username) {
        ArrayList<Classes> list = new ArrayList<>();
        String sql = "Select * from ClassHE171380 where studentName = ? or tutorName = ? and endTime is null";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Classes(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getDate(5),
                        rs.getDate(6),
                        rs.getString(7),
                        rs.getInt(8)
                ));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    public double getMoneyAll() {
        String sql = "SELECT SUM(amountOneHour) as total_amount\n"
                + "FROM ClassHE171380 where status = 'In-progress'";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                double totalAmount = rs.getDouble(1);
                return totalAmount / 50;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
    
    public int countInProgressClasses() {
        String sql = "SELECT COUNT(*) FROM ClassHE171380 WHERE status = 'In-progress'";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0; // Trả về 0 nếu có lỗi hoặc không có dữ liệu.
    }
    
    public ArrayList<Classes> getAllClassesInProgress() {
        ArrayList<Classes> list = new ArrayList<>();
        String sql = "Select * from ClassHE171380 where status = 'In-progress'";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Classes(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getDate(5),
                        rs.getDate(6),
                        rs.getString(7),
                        rs.getInt(8)
                ));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
        
    }

//    public ArrayList<Classes> getAllClasses() {
//        ArrayList<Classes> list = new ArrayList<>();
//        String sql = "Select * from ClassHE171380 where status = 'In-progress' or status = 'Unconfirmed'";
//        try {
//            ps = conn.prepareStatement(sql);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                list.add(new Classes(
//                        rs.getInt(1),
//                        rs.getString(2),
//                        rs.getString(3),
//                        rs.getDouble(4),
//                        rs.getDate(5),
//                        rs.getDate(6),
//                        rs.getString(7),
//                        rs.getString(8)
//                ));
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        return list;
//
//    }
    public int countUsers() {
        String sql = "SELECT COUNT(*) FROM UserHE171380";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0; // Trả về 0 nếu có lỗi hoặc không có dữ liệu.
    }
    
    public int getIncompleteRequests() {
        String sql = "SELECT COUNT(*) FROM RequestDetailHE171380 WHERE endTime IS NULL";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0; // Trả về 0 nếu có lỗi hoặc không có dữ liệu.
    }
    
    public ArrayList<Notification> getAllNotification(String username) {
        String sql = "Select * FROM NotificationHE171380 where username = ? order by timeStamp desc";
        ArrayList<Notification> list = new ArrayList<>();
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                LocalDateTime localDateTime = rs.getTimestamp(4).toLocalDateTime();
                list.add(new Notification(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        localDateTime,
                        rs.getString(5),
                        rs.getString(6)
                ));
            }
        } catch (Exception e) {
            System.out.println("Noti fail" + e);
        }
        return list;
    }
    
    public void insertNotification(String username, String message, String timeStamp, String link, String makerName) {
        String sql = "Insert into NotificationHE171380 values(?,?,?,?,?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, message);
            ps.setString(3, timeStamp);
            ps.setString(4, link);
            ps.setString(5, makerName);
            ps.execute();
        } catch (Exception e) {
            System.out.println("noti fail " + e);
        }
    }
    
    public void updateClass(String studentName, String tutorName, double amountOneHour, String startTime, String endTime, String status, String dayOfWeek, int id) {
        String sql = "Update ClassHE171380 set studentName = ?, tutorName = ?, amountOneHour = ?, startTime = ?, endTime = ?, status = ?, dayOfWeek = ? where id = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, studentName);
            ps.setString(2, tutorName);
            ps.setDouble(3, amountOneHour);
            ps.setString(4, startTime);
            ps.setString(5, endTime);
            ps.setString(6, status);
            ps.setString(7, dayOfWeek);
            ps.execute();
        } catch (Exception e) {
            status = "Error at read Class " + e.getMessage();
        }
    }
    
    public void deleteClass(String username) {
        String sql = "DELETE FROM ClassHE171380 WHERE studentName = ? OR tutorName = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, username);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void deleteNotification(String username) {
        String sql = "DELETE FROM NotificationHE171380 WHERE username = ? OR makerName = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, username);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void updateClass(String endTime, String status, int id) {
        String sql = "Update ClassHE171380 set endTime = ?, status = ? where id = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, endTime);
            ps.setString(2, status);
            ps.setInt(3, id);
            ps.execute();
        } catch (Exception e) {
            status = "Error at read Class " + e.getMessage();
        }
    }

//    public ArrayList Search(String sql, String txt, int size) {
//        ArrayList<UserDetail> listSearch = new ArrayList<>();
//        try {
//            ps = conn.prepareStatement(sql);
//            for (int i = 1; i <= size; i++) {
//                ps.setString(i, txt);
//            }
//
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                listSearch.add(new UserDetail(
//                        rs.getInt(1),
//                        rs.getInt(2),
//                        rs.getDouble(3),
//                        rs.getDouble(4),
//                        rs.getString(5),
//                        rs.getInt(6)
//                ));
//            }
//        } catch (Exception e) {
//            status = "Error at read UserDetail " + e.getMessage();
//        }
//        return listSearch;
//    }
}
