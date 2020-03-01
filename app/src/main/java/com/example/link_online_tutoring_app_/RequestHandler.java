package com.example.link_online_tutoring_app_;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

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

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class RequestHandler extends AsyncTask<String,Void,String> {
    Context context;
    String task;
    String result;

    public RequestHandler(Context context, String task) {
        this.context = context;
        this.task = task;
        result="";
    }

    @Override
    protected String doInBackground(String... strings) {
        switch (task){
            case "login":
                try {
                    URL url=new URL("http://lamp.ms.wits.ac.za/~s1819369/login.php");
                    HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                    String name=strings[0];
                    String password=strings[1];
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.setDoOutput(true);

                    OutputStream outputStream=httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));

                    String data= URLEncoder.encode("studentNo","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&&"+URLEncoder.encode("password","UTF-8")+"="
                            +URLEncoder.encode(password,"UTF-8");
                    bufferedWriter.write(data); //send data to the php file
                    bufferedWriter.flush();
                    bufferedWriter.close();

                    InputStream inputStream=httpURLConnection.getInputStream();
                    BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                    String line="";
                    while ((line=bufferedReader.readLine())!=null){
                        //reading the response we got from the php file
                        result+=line;
                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "register":
                //TODO
                break;

        }
        return result;
    }


    @Override
    protected void onPostExecute(String s) {
        //s is equal to result returned in do in the background method
       // super.onPostExecute(s);
        //Toast.makeText(context,s,Toast.LENGTH_LONG).show();
        switch (s){
            case "data matched":
                //in case login successful
                Toast.makeText(context,"login successful",Toast.LENGTH_LONG).show();
                //TODO go to users home menu
                break;
            case "try again":
                Toast.makeText(context,"login attempt failed",Toast.LENGTH_SHORT).show();
                //TODO handle failed login request like not registered and wrong password
        }
    }
}
