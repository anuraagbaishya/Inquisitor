package com.appex.android.inquisitor;

public class Quiz {

    private int id;
    private String ques;

    public String getQues() {

        return ques;
    }

    public void setQues(String ques) {
        this.ques = ques;
    }

    public Quiz(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Quiz(String ques) {
        super();
        this.ques = ques;
    }

    @Override
    public String toString() {
        return "Question:" + id +" "+ ques ;
    }
}
