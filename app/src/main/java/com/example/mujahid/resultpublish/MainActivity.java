package com.example.mujahid.resultpublish;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mujahid.resultpublish.BackendOperation.GetServerData;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements StudentDataMessageReceived {
    TextView name, father_name, mother_name, group, birth_date, result;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        editText = findViewById(R.id.search);
        name = findViewById(R.id.stu_name);
        father_name = findViewById(R.id.father_name);
        mother_name = findViewById(R.id.mother_name);
        group = findViewById(R.id.group);
        birth_date = findViewById(R.id.birth_date);
        result = findViewById(R.id.result);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SaveMarks.class);
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onLoad(View view) {
        GetServerData data = new GetServerData(this);
       data.getStudentData(this, editText.getText().toString());
        data.getScienceData(this,editText.getText().toString());

    }

    @Override
    public void onMessageReceived(Student student) {
       name.setText(student.getStudent_name());
       father_name.setText(student.getFather_name());
       mother_name.setText(student.getMother_name());
       group.setText(student.getSubject());
       birth_date.setText(student.getBirth_date());
       result.setText(String.format("%.2f",student.getPoint()));
    }
}
