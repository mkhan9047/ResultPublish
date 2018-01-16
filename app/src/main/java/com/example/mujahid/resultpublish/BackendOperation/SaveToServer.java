package com.example.mujahid.resultpublish.BackendOperation;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Mujahid on 1/16/2018.
 */

public class SaveToServer  {

   final String studentDataUrl = "http://mujahidprojects.000webhostapp.com/addStudentInfo.php";
   final String scienceDataUrl = "http://mujahidprojects.000webhostapp.com/addScienceMark.php";
   final String commerceDataUrl = "http://mujahidprojects.000webhostapp.com/addCommerceData.php";
   final String artDataUrl = "http://mujahidprojects.000webhostapp.com/addCommerceData.php";

    public void saveStudentInfo(Context context, String name, String father_name, String mother_name, String subject, String roll_number){


    }

    public void test(){
        new StudentAsyn().execute("Kousik","Gitom Bos","Mita","Science","823906","02-05-1997");
    }

    class StudentAsyn extends AsyncTask<String, Void, Void>{

        @Override
        protected Void doInBackground(String... data) {
            String name, father_name, mother_name, subject, roll_number,birth_date;
            name = data[0];
            father_name =data[1];
            mother_name = data[2];
            subject = data[3];
            roll_number = data[4];
            birth_date = data[5];
            try {
                URL url = new URL(studentDataUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));

                String encode = URLEncoder.encode("student_name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"+
                        URLEncoder.encode("father_name","UTF-8")+"="+URLEncoder.encode(father_name,"UTF-8")+"&"+
                        URLEncoder.encode("mother_name","UTF-8")+"="+URLEncoder.encode(mother_name,"UTF-8")+"&"+
                        URLEncoder.encode("roll_number","UTF-8")+"="+URLEncoder.encode(roll_number,"UTF-8")+"&"+
                        URLEncoder.encode("birth_date","UTF-8")+"="+URLEncoder.encode(birth_date,"UTF-8")+"&"+
                        URLEncoder.encode("subject","UTF-8")+"="+URLEncoder.encode(subject,"UTF-8");

                Log.d("Mim",encode);

                writer.write(encode);
                writer.flush();
                writer.close();
                outputStream.close();
                InputStream inputStream = connection.getInputStream();
                inputStream.close();
                connection.disconnect();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }


    }
}
