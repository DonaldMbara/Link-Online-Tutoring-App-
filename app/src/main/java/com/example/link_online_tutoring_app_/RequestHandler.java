package com.example.link_online_tutoring_app_;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
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

public class RequestHandler extends AsyncTask<String,Void,String> {
    Context context;
    String task;
    String result;
    static String Unkey = "Key";
    String StudNum;
    String username;
    String UN;
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
                    Log.d("moving", "Still Good");
                    URL url=new URL("https://lamp.ms.wits.ac.za/~s1819369/login.php");
                    HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                    String name=strings[0];
                    StudNum = strings[0];
                    username=StudNum;
                    String password=strings[1];
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoInput(true);     //allows us to use input stream
                    httpURLConnection.setDoOutput(true);    //allows us to use output stream

                    OutputStream outputStream=httpURLConnection.getOutputStream(); //used to write to url
                    BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8")); //used to load text  in buffer and formatting it before being sent

                    String data= URLEncoder.encode("studentNo","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&&"+URLEncoder.encode("password","UTF-8")+"="
                            +URLEncoder.encode(password,"UTF-8");   //note I did not encode '=' and '&&'

                    bufferedWriter.write(data); //send data to the php file
                    bufferedWriter.flush();
                    bufferedWriter.close();

                    InputStream inputStream=httpURLConnection.getInputStream(); //used to get read data from the url
                    BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                    String line="";
                    while ((line=bufferedReader.readLine())!=null){
                        //reading the response we got from the php file
                        result+=line;
                    }
                    //the response is stored in result
                    //note that in the case that the response is in jason format you will have to JSON objects

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "register":
                //TODO

                try {
                    URL url=new URL("https://lamp.ms.wits.ac.za/~s1819369/registration.php");
                    HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                    String uname=strings[0];

                    String password=strings[1];
                    String email=strings[2];
                    String firstname=strings[3];
                    String lastname=strings[4];
                    String studentnum=strings[5];
                    UN=uname;
                    username=studentnum;
                    StudNum = strings[5];

                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoInput(true);     //allows us to use input stream
                    httpURLConnection.setDoOutput(true);    //allows us to use output stream

                    OutputStream outputStream=httpURLConnection.getOutputStream(); //used to write to url
                    BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8")); //used to load text  in buffer and formatting it before being sent

                    String data= URLEncoder.encode("studentNo","UTF-8")+"="+URLEncoder.encode(studentnum,"UTF-8")
                            +"&&" +URLEncoder.encode("password","UTF-8")+"=" +URLEncoder.encode(password,"UTF-8")
                            +"&&" +URLEncoder.encode("email","UTF-8")+"=" +URLEncoder.encode(email,"UTF-8")
                            +"&&" +URLEncoder.encode("first_name","UTF-8")+"=" +URLEncoder.encode(firstname,"UTF-8")
                            +"&&" +URLEncoder.encode("last_name","UTF-8")+"=" +URLEncoder.encode(lastname,"UTF-8")
                            +"&&" +URLEncoder.encode("username","UTF-8")+"=" +URLEncoder.encode(uname,"UTF-8");

                    bufferedWriter.write(data); //send data to the php file
                    bufferedWriter.flush();
                    bufferedWriter.close();

                    InputStream inputStream=httpURLConnection.getInputStream(); //used to get read data from the url
                    BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                    String line="";
                    while ((line=bufferedReader.readLine())!=null){
                        //reading the response we got from the php file
                        result+=line;
                    }
                    //the response is stored in result
                    //note that in the case that the response is in jason format you will have to JSON objects

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;

            case "send message":

                try{
                URL url=new URL("https://lamp.ms.wits.ac.za/~s1819369/insertMessage.php");
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();



                String message=strings[0];
                String sender=strings[1];
                String receiver=strings[2];
                String receiverStudNum = strings[3];
                String senderStudNum= strings[4];


                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);     //allows us to use input stream
                httpURLConnection.setDoOutput(true);    //allows us to use output stream

                OutputStream outputStream=httpURLConnection.getOutputStream(); //used to write to url
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8")); //used to load text  in buffer and formatting it before being sent

                String data= URLEncoder.encode("message","UTF-8")+"="+URLEncoder.encode(message,"UTF-8")
                        +"&&" +URLEncoder.encode("sender","UTF-8")+"=" +URLEncoder.encode(sender,"UTF-8")
                        +"&&" +URLEncoder.encode("receiver","UTF-8")+"=" +URLEncoder.encode(receiver,"UTF-8")
                        +"&&" +URLEncoder.encode("rStudNum","UTF-8")+"=" +URLEncoder.encode(receiverStudNum,"UTF-8")
                        +"&&" +URLEncoder.encode("sStudNum","UTF-8")+"=" +URLEncoder.encode(senderStudNum,"UTF-8");

                bufferedWriter.write(data); //send data to the php file
                bufferedWriter.flush();
                bufferedWriter.close();

                InputStream inputStream=httpURLConnection.getInputStream(); //used to get read data from the url
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                String line="";
                while ((line=bufferedReader.readLine())!=null){
                    //reading the response we got from the php file
                    result+=line;
                }
                //the response is stored in result
                //note that in the case that the response is in jason format you will have to JSON objects

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

        break;



        }
        //the result that is returned here is the one in onPostExecute signature
        return result;
    }


    @Override
    protected void onPostExecute(String s) {
        //s is equal to result returned in do in the background method
        String[] ss=s.split(",");
        switch (ss[0]){
            case "data matched":
                //in case login successful

                SharedPreferences sharedPreferences=LoginActivity.context.getSharedPreferences(LoginActivity.SHARED_PREF_LOGIN,Context.MODE_PRIVATE);
               SharedPreferences.Editor editor=sharedPreferences.edit();
               editor.putBoolean(LoginActivity.LOGIN_STATUS,true); //saves login status
                editor.putString(Unkey, username);
                editor.putString("my_username",ss[1]);
               editor.apply();

                Log.d("Good", "login successful");
                Log.d("QuickCheck", StudNum);
               Toast.makeText(context,"login successful",Toast.LENGTH_LONG).show();

                Intent intent=new Intent(context, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent); //go to home menu login success
                break;
            case "try again":
                Log.d("Bad", "login Failed");
                Toast.makeText(context,"login attempt failed",Toast.LENGTH_SHORT).show();
                break;
            case "Registered Successfully":

                SharedPreferences sharedPreferences1=LoginActivity.context.getSharedPreferences(LoginActivity.SHARED_PREF_LOGIN,Context.MODE_PRIVATE);
                SharedPreferences.Editor editor1=sharedPreferences1.edit();
                editor1.putBoolean(LoginActivity.LOGIN_STATUS,true); //saves login status
                editor1.putString(Unkey, username);
                editor1.putString("my_username",UN);
                editor1.apply();
                editor1.commit();


                Toast.makeText(context,"registration successful",Toast.LENGTH_SHORT).show();
                context.startActivity(new Intent(context, HomeActivity.class)); //registration success go to home menu
                break;
            case "Something went wrong":

                Toast.makeText(context,"registration not successful",Toast.LENGTH_SHORT).show();
                break;


            case "The message is sent":
                Toast.makeText(context,"The message is sent",Toast.LENGTH_SHORT).show();
                break;

            case "message not sent":
                Toast.makeText(context,"message not sent",Toast.LENGTH_SHORT).show();
                break;

        }
    }
}
