package com.example.taher.localarea;

import android.content.Context;

import java.util.concurrent.ExecutionException;

/**
 * Created by Kareem on 2/16/16.
 */


public class MessageThread {
    private User_Model user;
    private User_Model[] participants;
    private String[] messages, people;
    private String threadTitle;
    private Context ctx = null;
    private Integer threadID;

//    public MessageThread(User_Model user, String[] messages, String[] people, Context ctx) {
//        super();
//        this.user = user;
//        this.messages = messages;
//        this.people = people;
//        this.ctx = ctx;
//    }

    public MessageThread(User_Model user, String[] messages, String[] people, Integer threadID, Context ctx) {
        super();
        this.user = user;
        this.messages = messages;
        this.people = people;
        this.threadID = threadID;
        this.ctx = ctx;
    }


    void setUserModels() {
        participants = new User_Model[people.length];
        for(int i = 0;i < people.length;i++) {
            participants[i] = new User_Model();
            participants[i].setUserDataByID(people[i],ctx);
        }
    }

    void setThreadTitle() {
        for(int i = 0;i < people.length;i++) {
            threadTitle += participants[i].getUsername();
            if(i != people.length - 1)
                threadTitle += ", ";
        }
    }

    //Setters and getters, nothing much :v
    User_Model getUserModel() {return user;}
    String[] getMessages() {return messages;}
    String[] getPeople() {return people;}
    Integer getThreadID() {return threadID;}
    String getThreadTitle() {return threadTitle;}
    void setUserModel(User_Model user) {this.user = user;}
    void setMessages(String[] messages) {this.messages=messages;}
    void setPeople(String[] people) {this.people=people;}
    void setThreadID(Integer ThreadID) {this.threadID = ThreadID;}
}
