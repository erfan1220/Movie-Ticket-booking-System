package org.example.movieticketbookingsystem;

public class HistoryData {
    private String Title;
    private String Cinema;
    private String Time;
    private String user;
    private String seat;

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getCinema() {
        return Cinema;
    }

    public void setCinema(String cinema) {
        Cinema = cinema;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
