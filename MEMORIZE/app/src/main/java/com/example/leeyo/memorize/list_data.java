package com.example.leeyo.memorize;

public class list_data {

    private String diaryNo;
    private String diaryTitle;
    private String diaryDate;
    private String diaryPlace;
    private String diaryContents;

    public list_data(String diaryNo, String diaryTitle, String diaryDate, String diaryPlace, String diaryContents) {
        this.diaryNo = diaryNo;
        this.diaryTitle = diaryTitle;
        this.diaryDate = diaryDate;
        this.diaryPlace = diaryPlace;
        this.diaryContents = diaryContents;
    }

    public String getDiaryNo() {
        return diaryNo;
    }

    public void setDiaryNo(String diaryNo) {
        this.diaryNo = diaryNo;
    }

    public String getDiaryTitle() {
        return diaryTitle;
    }

    public void setDiaryTitle(String diaryTitle) {
        this.diaryTitle = diaryTitle;
    }

    public String getDiaryDate() {
        return diaryDate;
    }

    public void setDiaryDate(String diaryDate) {
        this.diaryDate = diaryDate;
    }

    public String getDiaryPlace() {
        return diaryPlace;
    }

    public void setDiaryPlace(String diaryPlace) {
        this.diaryPlace = diaryPlace;
    }

    public String getDiaryContents() {
        return diaryContents;
    }

    public void setDiaryContents(String diaryContents) {
        this.diaryContents = diaryContents;
    }
}
