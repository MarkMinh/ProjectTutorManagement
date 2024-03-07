/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.sql.Date;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import static java.time.LocalDateTime.now;

/**
 *
 * @author ADMIN
 */
public class Notification {

    private int id;
    private String username;
    private String message;
    private LocalDateTime timeStamp;
    private String link;
    private String makerName;

    public Notification() {
    }

    public Notification(int id, String username, String message, LocalDateTime timeStamp, String link, String makerName) {
        this.id = id;
        this.username = username;
        this.message = message;
        this.timeStamp = timeStamp;
        this.link = link;
        this.makerName = makerName;
    }

    public String getMakerName() {
        return makerName;
    }

    public void setMakerName(String makerName) {
        this.makerName = makerName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public String getDiff() {
        Duration duration = Duration.between(timeStamp, now());
        long seconds = duration.getSeconds();
        if (seconds < 60) {
            return seconds + " seconds ago";
        }
        long minutes = duration.toMinutes();
        if (minutes < 60) {
            return minutes + " minutes ago";
        }
        long hours = duration.toHours();
        if (hours < 24) {
            return hours + " hours ago";
        }
        long days = duration.toDays();
        if (days < 30) {
            return days + " days ago";
        }
        long months = days / 30;
        if (months < 12) {
            return months + " months ago";
        }
        long years = months / 12;
        return years + " years ago";
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

}
