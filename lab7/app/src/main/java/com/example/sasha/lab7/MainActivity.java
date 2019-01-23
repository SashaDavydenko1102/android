package com.example.sasha.lab7;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    String[] users;
    AppDatabase db;

    ListView usersList;

    Button addUser, deleteUsers, findUsers;
    EditText firstName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "db").allowMainThreadQueries().build();

        usersList = findViewById(R.id.usersList);

        showAllUsers();

        addUser = findViewById(R.id.addUser);
        deleteUsers = findViewById(R.id.deleteUsers);
        findUsers = findViewById(R.id.findUsers);

        firstName = findViewById(R.id.firstName);

        findUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFoundUsers();
            }
        });

        deleteUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.userDao().deleteAllUsers();
                finish();
                startActivity(getIntent());
            }
        });

        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CreateUser.class));
            }
        });

    }

    public void showAllUsers(){
        if(db.userDao().getCountUsers() == 0){
            users = new String[1]; users[0] = "There are no data...";
        } else {
            List<User> allUsers = db.userDao().getAllUsers();
            users = new String[allUsers.size()];
            for(int i = 0; i < allUsers.size(); i++){
                users[i] = allUsers.get(i).getFirstName() + " " + allUsers.get(i).getLastName() + ", " + allUsers.get(i).getAge();
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, users);
        usersList.setAdapter(adapter);
    }

    public void showFoundUsers(){
        List<User> foundUsers = db.userDao().getUsersByName(firstName.getText().toString());
        if(foundUsers.size() == 0){
            users = new String[1]; users[0] = "There are no users with such first name...";
        } else {
            users = new String[foundUsers.size()];
            for(int i = 0; i < foundUsers.size(); i++){
                users[i] = foundUsers.get(i).getFirstName() + " " + foundUsers.get(i).getLastName() + ", " + foundUsers.get(i).getAge();
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, users);
        usersList.setAdapter(adapter);
    }

    public void reload(View view){
        finish();
        startActivity(getIntent());
    }
}
