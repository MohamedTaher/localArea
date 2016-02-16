package com.example.taher.localarea;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.widget.ImageButton;



public class testfrag extends Fragment {

    private static ImageButton sendNewMessage;

    testfragListener activityCommander;

    public interface testfragListener {
        public void sendNewMessage();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_testfrag, container, false);
        sendNewMessage = (ImageButton) view.findViewById(R.id.sendNewMessageButton);

        sendNewMessage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                //go to the new message fragment.
            }
        });
        return view;
    }

}
