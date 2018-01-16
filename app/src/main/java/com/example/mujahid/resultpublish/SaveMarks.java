package com.example.mujahid.resultpublish;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.mujahid.resultpublish.BackendOperation.SaveToServer;
import com.example.mujahid.resultpublish.Fragments.Arts_Subject;
import com.example.mujahid.resultpublish.Fragments.Commerce_Subject;
import com.example.mujahid.resultpublish.Fragments.Science_Subject;

public class SaveMarks extends AppCompatActivity {

    Spinner spinner;
    android.support.v4.app.Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_marks);
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(getSpinner());
    }

    private AdapterView.OnItemSelectedListener getSpinner(){
        return  new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
          switch (i){
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

    private void FragTran(){
        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout,fragment);
        transaction.commit();

    }


    public void onEdit(View view) {
        SaveToServer a = new SaveToServer();
        a.test();
    }


    public void onSave(View view){

    }
}
