package com.acme.a3csci3130;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DetailViewActivity extends Activity {

    private EditText nameField, emailField;
    Contact receivedPersonInfo;
    private MyApplicationData appState;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appState = ((MyApplicationData) getApplicationContext());

        setContentView(R.layout.activity_detail_view);
        receivedPersonInfo = (Contact)getIntent().getSerializableExtra("Contact");

        nameField = (EditText) findViewById(R.id.name);
        emailField = (EditText) findViewById(R.id.email);

        if(receivedPersonInfo != null){
            nameField.setText(receivedPersonInfo.name);
            emailField.setText(receivedPersonInfo.email);
        }
    }

    public void updateContact(View v){
        //TODO: Update contact funcionality
        String personID = receivedPersonInfo.uid; // get selected person id
        String name = nameField.getText().toString();     //set updated name
        String email = emailField.getText().toString();   //set updated email
        Contact person = new Contact(personID, name, email);

        appState.firebaseReference.child(receivedPersonInfo.uid).setValue(person); //put updated contact into database

        finish();
    }

    public void eraseContact(View v)
    {
        //TODO: Erase contact functionality
        appState.firebaseReference.child(receivedPersonInfo.uid).removeValue();


    }
}
