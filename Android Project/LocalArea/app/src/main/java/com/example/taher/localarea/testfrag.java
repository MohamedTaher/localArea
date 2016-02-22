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
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ImageButton;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import com.example.taher.localarea.R;


//The code is complicated, so take a deep breathe before start reading xD

public class testfrag extends Fragment {

    private static ImageButton sendNewMessage;
    private MessageThread[] messageThreads;
    private ListView threadsList;
    private User_Model user;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> adapterData = new ArrayList<String>();

//    public testfrag(User_Model user) {
//        this.user = user;
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_testfrag, container, false);
        threadsList = (ListView) view.findViewById(R.id.messageThreadsList);
        sendNewMessage = (ImageButton) view.findViewById(R.id.sendNewMessageButton);


        //Make sure to set the user model before the transition to this fragment
        Background process = new Background(getContext());

        //Getting messages from database
        process.execute("getMessageThreadsForUser", user.getID().toString());
        try {
            process.get();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }



        String threads = process.result;
        String[] tmp1 = threads.split("/");
        messageThreads = new MessageThread[tmp1.length];
        for(int i = 0;i < tmp1.length;i++) {
            String[] tmp2 = tmp1[i].split(",");
            messageThreads[i].setThreadID(Integer.parseInt(tmp2[0]));
            String[] tmp3 = tmp2[1].split("-");
            messageThreads[i].setPeople(new String[tmp3.length]);
            for(int j = 0;j < tmp3.length;j++) {
                messageThreads[i].getPeople()[j] = tmp3[j];
            }
            messageThreads[i].setUserModel(user);
            messageThreads[i].setUserModels();
            messageThreads[i].setThreadTitle();
            adapterData.add(messageThreads[i].getThreadTitle());
        }
//        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, adapterData);
        adapter = new ArrayAdapter<String>(getContext(), R.layout.simple_message_row, adapterData);
        threadsList.setAdapter(adapter);

        //link the adapter with the layout and show the data, then link the testfrag with the menu and then test everything

//        //Each message is of this format: [numberOfThreads]/[NumberOfNames]/[Name/Name/...]/[NumberOfMessages]/[MessageLength]/[SenderName:Message][MessageLength][SenderName:Message]/...
//        int index = 0;
//        String tmp = "";
//
//        //Getting number of threads
//        while(threads.charAt(index) != '/') {
//            tmp += threads.charAt(index);
//            index++;
//        }
//        index++;
//        int numberOfThreads = Integer.parseInt(tmp);
//        messageThreads = new MessageThread[numberOfThreads];
//
//        //for each thread, get the ids of the receivers and the messages associated
//        for(int threadNo = 0;threadNo < numberOfThreads;threadNo++) {
//            tmp = "";
//            while(threads.charAt(index) != '/') {
//                tmp += threads.charAt(index);
//                index++;
//            }
//            index++;
//            int numberOfPeople = Integer.parseInt(tmp);
//            messageThreads[threadNo].setPeople(new String[numberOfPeople]);
//            //Read those numberOfPeople names, they are splitted by commas
//            for(int personNo = 0;personNo < numberOfPeople;personNo++) {
//                tmp = "";
//                while(threads.charAt(index) != '/') {
//                    tmp += threads.charAt(index);
//                    index++;
//                }
//                index++;
//                messageThreads[threadNo].getPeople()[personNo] = new String(tmp);
//            }
//
//            //Getting number of messages in this thread, the message format is [Name:Message] where Name is the sender's name
//            tmp = "";
//            while(threads.charAt(index) != '/') {
//                tmp += threads.charAt(index);
//                index++;
//            }
//            index++;
//            int numberOfMessages = Integer.parseInt(tmp);
//            messageThreads[threadNo].setMessages(new String[numberOfMessages]);
//            //Read each message and store it in the thread
//            for(int messageNo = 0;messageNo < numberOfMessages;messageNo++) {
//                tmp = "";
//                while(threads.charAt(index) != '/') {
//                    tmp += threads.charAt(index);
//                    index++;
//                }
//                index++;
//                int messageLength = Integer.parseInt(tmp);
//                tmp = "";
//                for(int length = 0;length < messageLength;length++) {
//                    tmp += threads.charAt(index);
//                    index++;
//                }
//                index++;
//                messageThreads[threadNo].getMessages()[messageNo] = new String(tmp);
//            }
//        }


        //Make a custom array adapter and fill in the messages in the threads


        sendNewMessage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                //go to the new message fragment.
            }
        });
        return view;
    }

    void setUserModel(User_Model user) {this.user=user;}
    User_Model getUserModel(){return user;}

}
