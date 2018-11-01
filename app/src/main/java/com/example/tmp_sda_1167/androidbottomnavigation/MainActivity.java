package com.example.tmp_sda_1167.androidbottomnavigation;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements ValueEventListener {

    private BottomNavigationView mainNav;
    private FrameLayout mainFrame;
    private HomeFragment homeFragment;
    private NotificationFragment notificationFragment;
    private SettingsFragment settingsFragment;
    private EditText inputEvent;
    private TextView eventType;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference rootDatabaseReference = firebaseDatabase.getReference();
    private DatabaseReference eventTypeDatabasereference=rootDatabaseReference.child("eType");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        inputEvent=(EditText) findViewById(R.id.inputEventType);
        eventType=(TextView) findViewById(R.id.eventType);


        mainFrame=(FrameLayout)findViewById(R.id.main_frame);

        mainNav=(BottomNavigationView)findViewById(R.id.main_nav);

        homeFragment= new HomeFragment();
        notificationFragment= new NotificationFragment();
        settingsFragment = new SettingsFragment();

        setFragment(homeFragment);
        mainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_home:
                        mainNav.setItemBackgroundResource(R.color.colorPrimary);
                        setFragment(homeFragment);
                        return true;
                    case R.id.nav_notify:
                        mainNav.setItemBackgroundResource(R.color.colorAccent);
                        setFragment(notificationFragment);
                        return true;
                    case R.id.nav_settings:
                        mainNav.setItemBackgroundResource(R.color.colorPrimaryDark);
                        setFragment(settingsFragment);
                        return true;
                        default:
                            return false;
                }

            }


        });


    }
    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction =getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame,fragment);
        fragmentTransaction.commit();
    }
    public void submitEvent(View view){
String eventText =inputEvent.getText().toString();
        eventTypeDatabasereference.setValue(eventText);


    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
if(dataSnapshot.getValue(String.class)!=null){

    String key = dataSnapshot.getKey();
    if(key.equals("eType")){

        String event=dataSnapshot.getValue(String.class);
        eventType.setText(event);
    }
}
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        eventTypeDatabasereference.addValueEventListener(this);
    }
}
