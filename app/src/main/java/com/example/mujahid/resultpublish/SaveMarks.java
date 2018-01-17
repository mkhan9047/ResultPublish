package com.example.mujahid.resultpublish;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.database.sqlite.SQLiteConstraintException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mujahid.resultpublish.BackendOperation.SaveToServer;
import com.example.mujahid.resultpublish.EventBus.Event;
import com.example.mujahid.resultpublish.EventBus.GlobalBus;
import com.example.mujahid.resultpublish.Fragments.Arts_Subject;
import com.example.mujahid.resultpublish.Fragments.Commerce_Subject;
import com.example.mujahid.resultpublish.Fragments.Science_Subject;

import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;

public class SaveMarks extends AppCompatActivity {

    Spinner spinner;
    android.support.v4.app.Fragment fragment;

    HashMap<String, Integer> scienceData;
    HashMap<String, Integer> commerceData;
    HashMap<String, Integer> artsData;


    EditText stu_name;
    EditText Father_name;
    EditText Mother_name;
    EditText Roll_number;
    EditText Birth_Date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_marks);
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(getSpinner());
        stu_name = findViewById(R.id.student_name);
        Father_name = findViewById(R.id.Father_name);
        Mother_name = findViewById(R.id.Mother_name);
        Roll_number = findViewById(R.id.roll_number);
        Birth_Date = findViewById(R.id.birth_date);
    }

    private AdapterView.OnItemSelectedListener getSpinner() {
        return new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        fragment = new Science_Subject();
                        break;
                    case 1:
                        fragment = new Commerce_Subject();
                        break;
                    case 2:
                        fragment = new Arts_Subject();
                        break;
                    default:
                        fragment = new Science_Subject();
                }
                FragTran();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        };
    }

    private void FragTran() {
        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout, fragment);
        transaction.commit();

    }

    @Override
    protected void onStart() {
        super.onStart();
        // Register this fragment to listen to event.
        GlobalBus.getBus().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        GlobalBus.getBus().unregister(this);
    }


    void onScienceDatacall() {
        Science_Subject data = (Science_Subject) getSupportFragmentManager().findFragmentById(R.id.frameLayout);
        data.mainCall();
    }

    void onCommerceDatacall() {
        Commerce_Subject data = (Commerce_Subject) getSupportFragmentManager().findFragmentById(R.id.frameLayout);
        data.mainCall();
    }

    void onArtsDatacall() {
        Arts_Subject data = (Arts_Subject) getSupportFragmentManager().findFragmentById(R.id.frameLayout);
        data.mainCall();
    }


    @Subscribe
    public void SciencegetMessage(Event.ScienceFragmentActivityMessage fragmentActivityMessage) {
        scienceData = fragmentActivityMessage.getMessage();
    }

    @Subscribe
    public void CommercegetMessage(Event.CommerceFragmentActivityMessage fragmentActivityMessage) {
        commerceData = fragmentActivityMessage.getMessage();
    }

    @Subscribe
    public void ArtsgetMessage(Event.ArtFragmentActivityMessage fragmentActivityMessage) {
        artsData = fragmentActivityMessage.getMessage();

    }

    public void onEdit(View view) {
        SaveToServer a = new SaveToServer();
        a.saveArtsMark(this, "823870", 90, 49, 55, 69, 79, 54);
    }

    public boolean Validation() {

        boolean ok = false;

        if (stu_name.getText().length() != 0 && Father_name.getText().length() != 0 && Mother_name.getText().length() != 0 && Roll_number.getText().length() != 0 && Birth_Date.getText().length() != 0) {
            ok = true;
        }
        return ok;
    }

    public void onSave(View view) {
     SaveToServer a = new SaveToServer();
     if(Validation()){
         a.saveStudentInfo(this, stu_name.getText().toString(), Father_name.getText().toString(), Mother_name.getText().toString(), spinner.getSelectedItem().toString(), Roll_number.getText().toString(), Birth_Date.getText().toString());
         if (spinner.getSelectedItem().toString().equals("Science")) {
             onScienceDatacall();
             a.saveScienceMark(getApplicationContext(), Roll_number.getText().toString(), scienceData.get("phy"), scienceData.get("bio"), scienceData.get("che"), scienceData.get("eng"), scienceData.get("ban"),scienceData.get("is"));



         } else if (spinner.getSelectedItem().toString().equals("Commerce")) {
             onCommerceDatacall();
             a.saveCommerceMark(getApplicationContext(), Roll_number.getText().toString(), commerceData.get("bus"), commerceData.get("entire"), commerceData.get("acc"), commerceData.get("ban"), commerceData.get("eng"), commerceData.get("is"));


         } else if (spinner.getSelectedItem().toString().equals("Humanities")) {

             onArtsDatacall();
             a.saveArtsMark(getApplicationContext(), Roll_number.getText().toString(), artsData.get("his"), artsData.get("echo"), artsData.get("math"), artsData.get("ban"), artsData.get("eng"), artsData.get("is"));

         }else{
             Toast.makeText(this,"INput data",Toast.LENGTH_LONG).show();
         }
     }
    }


}
