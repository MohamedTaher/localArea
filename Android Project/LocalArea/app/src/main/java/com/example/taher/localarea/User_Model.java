package com.example.taher.localarea;

import android.content.Context;

import java.util.concurrent.ExecutionException;

/**
 * Created by taher on 2/7/16.
 */
public class User_Model {

    private String id;
    private String username;
    private String email;

    public User_Model(String id, String username, String email) {
        this.id = id;
        this.email = email;
        this.username = username;
    }

    public User_Model(String data, String type, Context ctx) {
        if (type == "ID")
            setUserDataByID(data, ctx);
        else if (type == "username")
            setUserDataByUsername(data, ctx);
    }

    public void setUserDataByID(String id, Context ctx)
    {
        String method = "searchByID";
        Background process = new Background(ctx);
        process.execute(method, id);

        try {
            process.get();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String[] fields = process.result.split("-");
        if(fields.length == 3) {
            this.id = fields[0];
            username = fields[1];
            email = fields[2];
        } else {
          //connection error
        }
    }

    public void setUserDataByUsername(String username, Context ctx)
    {
        String method = "searchByUsername";

    }

    public String getEmail() {
        return email;
    }

    public String getID() {
        return id;
    }

    public String getUsername() {
        return username;
    }
}
