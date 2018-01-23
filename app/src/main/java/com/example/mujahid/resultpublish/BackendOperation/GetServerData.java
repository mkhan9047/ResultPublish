package com.example.mujahid.resultpublish.BackendOperation;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.mujahid.resultpublish.MainActivity;
import com.example.mujahid.resultpublish.Student;
import com.example.mujahid.resultpublish.StudentDataMessageReceived;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * Created by Mujahid on 1/21/2018.
 */

public class GetServerData {
private boolean isStusuccess = false;
    Student student;
        HashMap<String, String> stuDataPass;
        HashMap<String, Integer> scienceMark;
        ProgressDialog dialog;

    StudentDataMessageReceived messageReceived;

    public GetServerData(StudentDataMessageReceived s){

        this.messageReceived = s;
    }
    public void getStudentData(Context context, String roll){

        new StudentAsynTask(context).execute(roll);

    }

    public void getScienceData(Context context, String roll){
new ScienceAsyntask().execute(roll);
    }
/*
    public HashMap<String, Integer> getCommerceData(Context context, String roll){

    }

    public HashMap<String, Integer> getArtsData(Context context, String roll){

    }
*/

    class StudentAsynTask extends AsyncTask<String, Void, String>{
        Context con;
        String StudentURl ="http://mujahidprojects.000webhostapp.com/getStudentData.php?";

        public StudentAsynTask(Context context){
            con = context;
            dialog = new ProgressDialog(context);
        }

        protected  void onPreExecute(){
            stuDataPass = new HashMap<>();
            dialog.setMessage("Loading data....");
            dialog.show();
        }
        @Override
        protected String doInBackground(String... strings) {
            String roll_num = strings[0];
            try {
                String encode = URLEncoder.encode("roll_number","UTF-8")+"="+URLEncoder.encode(roll_num,"UTF-8");

                URL url = new URL(StudentURl+encode);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                InputStream inputStream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder builder = new StringBuilder();
                String keep;
                while((keep = reader.readLine())!=null){
                    builder.append(keep);
                }
                reader.close();
                inputStream.close();
                connection.disconnect();
                return  builder.toString().trim();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String S){
            try {
                JSONObject mainObject = new JSONObject(S);
                JSONArray array = mainObject.getJSONArray("Student");
                if(array.length()==0){
                    Toast.makeText(con,"no data found",Toast.LENGTH_LONG).show();
                    if(dialog.isShowing()){
                        dialog.dismiss();
                    }
                }else{

                    JSONObject object = array.getJSONObject(0);
                     student = new Student(object.getString("Student_name"),object.getString("Father_Name"),object.getString("Mother_Name"),object.getString("Birth_Date"),object.getString("Subject"));
                    isStusuccess = true;

                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.d("JSON Result:",S);
        }
    }

    //get science data
    class ScienceAsyntask extends AsyncTask<String, Void, String>{

        Context con;
        String ScienceURL ="http://mujahidprojects.000webhostapp.com/getScienceData.php?";
        protected void onPreExecute(){
            scienceMark = new HashMap<>();
        }
        @Override
        protected String doInBackground(String... strings) {
            String roll_num = strings[0];
            try {
                String encode = URLEncoder.encode("roll_number","UTF-8")+"="+URLEncoder.encode(roll_num,"UTF-8");

                URL url = new URL(ScienceURL+encode);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                InputStream inputStream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder builder = new StringBuilder();
                String keep;
                while((keep = reader.readLine())!=null){
                    builder.append(keep);
                }
                reader.close();
                inputStream.close();
                connection.disconnect();
                return  builder.toString().trim();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String S){
            try {

                JSONObject mainObject = new JSONObject(S);
                JSONArray array = mainObject.getJSONArray("SciSubject");

                if(array.length()==0){
                    Toast.makeText(con,"no data found",Toast.LENGTH_LONG).show();
                    if(dialog.isShowing()){
                        dialog.dismiss();
                    }

                }else{

                    JSONObject object = array.getJSONObject(0);
                    scienceMark.put("s1",object.getInt("Physics"));
                    scienceMark.put("s2",object.getInt("Chemistry"));
                    scienceMark.put("s3",object.getInt("Bangla"));
                    scienceMark.put("s4",object.getInt("English"));
                    scienceMark.put("s5",object.getInt("Biology"));
                    scienceMark.put("s6",object.getInt("IslamicEdu"));


                    ResultEngine engine = new ResultEngine();
                    double point = engine.getPoint(con,scienceMark);
                   student.setPoint(point);

                    messageReceived.onMessageReceived(student);
                    if(dialog.isShowing()&&isStusuccess){
                        dialog.dismiss();
                    }
                    Log.d("Result",String.format("%.2f",point));


                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.d("JSON Result:",S);
        }
    }

}
