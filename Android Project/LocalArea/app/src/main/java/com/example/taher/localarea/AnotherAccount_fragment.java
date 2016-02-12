package com.example.taher.localarea;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

/**
 * Created by taher on 2/7/16.
 */
public class AnotherAccount_fragment extends Fragment implements View.OnClickListener{
    View rootview;
    Button type = null;
    private User_Model user = null;
    private User_Model sourceUser = null;

    boolean makeOperation(String type)
    {
        Background bt = new Background(getContext());
        bt.execute(type, sourceUser.getID().toString(), user.getID().toString());

        try {
            bt.get();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String str = bt.result;
        if (str.equals("YES")) {
            return true;
        } else if (str.equals("NO")){
            return false;
        }
        return false;
    }

    private void setUserType()
    {
        if (makeOperation("isFriend")) {
            type.setText("Friend");
        } else {
            if (makeOperation("isAddFriend"))
            {
                type.setText("Request sent");

            } else if (makeOperation("isRequestedFriend")) {

                type.setText("Confirm");

            } else {
                type.setText("Add Friend");
            }

        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.another_account, container, false);

        type = (Button) rootview.findViewById(R.id.type);
        type.setOnClickListener(this);
        setUserType();
        return rootview;
    }

    public void setUser(User_Model user, User_Model sourceUser) {
        this.user = user;
        this.sourceUser = sourceUser;
    }

    public User_Model getUser() {
        return user;
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.type:

                if (type.getText().equals("Add Friend")) {
                    makeOperation("addFriend");
                    type.setText("sent Request");
                } else if (type.getText().equals("Confirm")) {
                    makeOperation("acceptFriend");
                    type.setText("Friend");
                } else if (type.getText().equals("Friend")) {
                    // NO Action taken
                    //un-friend
                }

                break;

            default:
                return;
        }

    }
}
