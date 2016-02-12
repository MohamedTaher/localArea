package com.example.taher.localarea;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.taher.localarea.R;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by taher on 2/6/16.
 */
public class Search_fragment extends Fragment implements View.OnClickListener{
    View rootview;

    private Home parant = null;
    private User_Model sorceUser = null;

    private String sourceID; // want to sent to another account activity
    private EditText searchTxt;
    private Button search;
    private RadioButton name, place, brand;
    private ListView searchList;
    private ArrayAdapter<String> adapter;
    private ArrayList<User_Model> users;
    private ArrayList<String> list;

    public static User_Model wanted = null;

    private boolean e = true;

    private void nameSearch() { // fill array list name ,id ,email

        users.clear();
        list.clear();

        String method = "searchName";
        String _uname = searchTxt.getText().toString();

        Background bt = new Background(getContext());
        bt.execute(method, _uname);

        try {
            bt.get();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String temp = bt.result;
        String[] users = temp.split("~");
        if (users[0].length() > 0) {
            for (int i = 0; i < users.length; i++) {
                String[] str = users[i].split("-");
                if(str[0].equals(sourceID))continue;
                this.users.add(new User_Model(str[0], str[1], str[2]));
                list.add(str[1]);
                e = false;
            }
        } else {
            list.add("Empty List");
            e = true;
        }

    }

    public void setParent(Home home)
    {
        parant = home;
    }

    public void setSourceUser(User_Model sourceUser) { this.sorceUser = sourceUser; }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.search, container, false);

        searchTxt = (EditText) rootview.findViewById(R.id.searchText);

        search = (Button) rootview.findViewById(R.id.searchButton);
        search.setOnClickListener(this);

        name = (RadioButton) rootview.findViewById(R.id.nameRadio);
        name.setOnClickListener(this);
        name.setChecked(true);

        place = (RadioButton) rootview.findViewById(R.id.placeRadio);
        place.setOnClickListener(this);

        brand = (RadioButton) rootview.findViewById(R.id.brandRadio);
        brand.setOnClickListener(this);

        searchList = (ListView) rootview.findViewById(R.id.searchList);
        searchList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long arg3) {

                if (position >= 0) {
                    String t = "Add Friend";
                    if (list.get(position) != "Empty List") {
                        Toast.makeText(getContext(),
                                list.get(position), Toast.LENGTH_LONG)
                                .show();
                        wanted = users.get(position);
                        AnotherAccount_fragment anotherUser = new AnotherAccount_fragment();
                        anotherUser.setUser(wanted, sorceUser);
                        Fragment f = anotherUser;
                        parant.replaceFragment(wanted.getUsername(),f);

                    }
                }
            }
        });

        users = new ArrayList<User_Model>();
        list = new ArrayList<String>();
        list.add("Empty List");

        adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1, list);
        searchList.setAdapter(adapter);

        sourceID = Home.user.getID();

        return rootview;
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.nameRadio:
                place.setChecked(false);
                brand.setChecked(false);
                break;

            case R.id.placeRadio:
                name.setChecked(false);
                brand.setChecked(false);
                break;

            case R.id.brandRadio:
                place.setChecked(false);
                name.setChecked(false);
                break;

            case R.id.searchButton:
                if (searchTxt.getText().length() == 0) {
                    Toast.makeText(getContext(),
                            "Enter text to search on it", Toast.LENGTH_LONG)
                            .show();
                } else {
                    nameSearch();
                    if (e == true) {
                        adapter.notifyDataSetChanged();
                        Toast.makeText(getContext(), "Not Found",
                                Toast.LENGTH_LONG).show();
                    } else
                        adapter.notifyDataSetChanged();
                }
                break;

            default:
                return;
        }

    }
}