package com.example.mujahid.resultpublish.BackendOperation;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

    private final String studentDataUrl = "http://mujahidprojects.000webhostapp.com/addStudentInfo.php";
   final String scienceDataUrl = "http://mujahidprojects.000webhostapp.com/addScienceMark.php";
   final String commerceDataUrl = "http://mujahidprojects.000webhostapp.com/addCommerceData.php";
   final String artDataUrl = "http://mujahidprojects.000webhostapp.com/addCommerceData.php";

    public void saveStudentInfo(Context context, String name, String father_name, String mother_name, String subject, String roll_number,String birth_date){
        new StudentAsyn(context).execute(name,father_name,mother_name,subject,roll_number,birth_date);
    }

  public void saveScienceMark(Context context, String roll_number, int physics, int biology, int chemistry, int english, int bangla,int islam){

        new ScienceMark(context).execute(Integer.parseInt(roll_number),physics,chemistry,biology,bangla,english,islam);
  }

  public void saveCommerceMark(Context context , String roll_number, int business, int entire, int accounting, int bangla, int english, int islam){
        new CommerceMark(context).execute(Integer.parseInt(roll_number),business,entire,accounting,bangla,english,islam);
  }


     class StudentAsyn extends AsyncTask<String, Void, String>{

        Context con;
        StudentAsyn(Context context){
            con = context;
        }

        JSONObject jsonObject;

        @Override
        protected String doInBackground(String... data) {
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
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder builder = new StringBuilder();
                String jSon;
                while ((jSon = reader.readLine())!=null){
                    builder.append(jSon).append("\n");
                }

                inputStream.close();
                connection.disconnect();
                return builder.toString().trim();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String s){
            Log.d("Test",s);
            try {
                jsonObject = new JSONObject(s);
                int indecate = jsonObject.getInt("success");
                String result = jsonObject.getString("message");
                if(indecate > 0){
                   Log.d("Result:","Saved Successfully");
                }else{
                    Log.d("Result:", result);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    //Science data insert section

    class ScienceMark extends AsyncTask<Integer, Void, String>{
        Context con;
        ScienceMark(Context context){
            con = context;
        }

        JSONObject jsonObject;

        @Override
        protected String doInBackground(Integer... data) {
            String roll_number;
            int physics, chemistry, biology, bangla, english, islam;
            roll_number = String.format("%s",data[0]);
            physics = data[1];
            chemistry = data[2];
            biology = data[3];
            bangla = data[4];
            english = data[5];
            islam = data[6];

            try {
                URL url = new URL(scienceDataUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));

                String encode = URLEncoder.encode("roll_number","UTF-8")+"="+URLEncoder.encode(roll_number,"UTF-8")+"&"+
                        URLEncoder.encode("physics","UTF-8")+"="+URLEncoder.encode(String.format("%s",physics),"UTF-8")+"&"+
                        URLEncoder.encode("biology","UTF-8")+"="+URLEncoder.encode(String.format("%s",biology),"UTF-8")+"&"+
                        URLEncoder.encode("cehmistry","UTF-8")+"="+URLEncoder.encode(String.format("%s",chemistry),"UTF-8")+"&"+
                        URLEncoder.encode("bangla","UTF-8")+"="+URLEncoder.encode(String.format("%s",bangla),"UTF-8")+"&"+
                        URLEncoder.encode("english","UTF-8")+"="+URLEncoder.encode(String.format("%s",english),"UTF-8")+"&"+
                        URLEncoder.encode("islam","UTF-8")+"="+URLEncoder.encode(String.format("%s",islam),"UTF-8");


                Log.d("Mim",encode);

                writer.write(encode);
                writer.flush();
                writer.close();
                outputStream.close();
                InputStream inputStream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder builder = new StringBuilder();
                String jSon;
                while ((jSon = reader.readLine())!=null){
                    builder.append(jSon).append("\n");
                }

                inputStream.close();
                connection.disconnect();
                return builder.toString().trim();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String s){
            Log.d("Test",s);
            try {
                jsonObject = new JSONObject(s);
                int indecate = jsonObject.getInt("success");
                String result = jsonObject.getString("message");
                if(indecate > 0){
                    Log.d("Science Mark:","Saved Successfully");
                }else{
                    Log.d("Science Mark:", result);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    //Commerce data insert part

    class CommerceMark extends AsyncTask<Integer, Void, String>{
        Context con;
        CommerceMark(Context context){
            con = context;
        }

        JSONObject jsonObject;

        @Override
        protected String doInBackground(Integer... data) {
            String roll_number;
            int business, accounting, entire, bangla, english, islam;
            roll_number = String.format("%s",data[0]);
            business = data[1];
            entire = data[2];
            accounting = data[3];
            bangla = data[4];
            english = data[5];
            islam = data[6];

            try {
                URL url = new URL(commerceDataUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                OutputStream outputStream = connection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));

                String encode = URLEncoder.encode("roll_number","UTF-8")+"="+URLEncoder.encode(roll_number,"UTF-8")+"&"+
                        URLEncoder.encode("business","UTF-8")+"="+URLEncoder.encode(String.format("%s",business),"UTF-8")+"&"+
                        URLEncoder.encode("accounting","UTF-8")+"="+URLEncoder.encode(String.format("%s",accounting),"UTF-8")+"&"+
                        URLEncoder.encode("entirepenure","UTF-8")+"="+URLEncoder.encode(String.format("%s",entire),"UTF-8")+"&"+
                        URLEncoder.encode("bangla","UTF-8")+"="+URLEncoder.encode(String.format("%s",bangla),"UTF-8")+"&"+
                        URLEncoder.encode("english","UTF-8")+"="+URLEncoder.encode(String.format("%s",english),"UTF-8")+"&"+
                        URLEncoder.encode("islam","UTF-8")+"="+URLEncoder.encode(String.format("%s",islam),"UTF-8");


                Log.d("Mim",encode);

                writer.write(encode);
                writer.flush();
                writer.close();
                outputStream.close();
                InputStream inputStream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder builder = new StringBuilder();
                String jSon;
                while ((jSon = reader.readLine())!=null){
                    builder.append(jSon).append("\n");
                }

                inputStream.close();
                connection.disconnect();
                return builder.toString().trim();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String s){
            Log.d("Test",s);
            try {
                jsonObject = new JSONObject(s);
                int indecate = jsonObject.getInt("success");
                String result = jsonObject.getString("message");
                if(indecate > 0){
                    Log.d("Science Mark:","Saved Successfully");
                }else{
                    Log.d("Science Mark:", result);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}
