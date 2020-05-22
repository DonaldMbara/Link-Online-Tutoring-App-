package com.example.link_online_tutoring_app_;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Holder> {
    Context c;
    ArrayList<Answer_Model> models;

    public Adapter(Context c, ArrayList<Answer_Model> models) {
        this.c = c;
        this.models = models;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.postsholder, null);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {
        holder.Ans.setText(models.get(position).getAnswer());
        holder.Author.setText("Author: "+ models.get(position).getAuthor());
        holder.Likes.setText("Likes: " + models.get(position).getLikes());
        holder.Like_BTN.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public void onClick(View view) {
                new AsyncTask<Void, Void, String>() {
                    @SuppressLint("StaticFieldLeak")
                    @Override
                    protected String doInBackground(Void... voids) {
                        ArrayList<String> result=new ArrayList<>();
                        JSONObject jsonObject = null;

                        try {
                            Log.d("moving", "Still Good");
                            URL url=new URL("https://lamp.ms.wits.ac.za/~s1819369/Testing2.php");
                            HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                            String answer_id=String.valueOf(models.get(position).getId());
                            SharedPreferences sharedPreferences1=LoginActivity.context.getSharedPreferences(LoginActivity.SHARED_PREF_LOGIN,Context.MODE_PRIVATE);
                            String user_id= sharedPreferences1.getString(RequestHandler.Unkey,null);

                            httpURLConnection.setRequestMethod("POST");
                            httpURLConnection.setDoInput(true);     //allows us to use input stream
                            httpURLConnection.setDoOutput(true);    //allows us to use output stream

                            OutputStream outputStream=httpURLConnection.getOutputStream(); //used to write to url
                            BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8")); //used to load text  in buffer and formatting it before being sent

                            String data= URLEncoder.encode("id","UTF-8")+"="+URLEncoder.encode(answer_id,"UTF-8")+"&&"+URLEncoder.encode("userid","UTF-8")+"="
                                    +URLEncoder.encode(user_id,"UTF-8");   //note I did not encode '=' and '&&'

                            bufferedWriter.write(data); //send data to the php file
                            bufferedWriter.flush();
                            bufferedWriter.close();

                            InputStream inputStream=httpURLConnection.getInputStream(); //used to get read data from the url
                            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                            String line="";
                            while ((line=bufferedReader.readLine())!=null){
                                //reading the response we got from the php file
                                result.add(line);
                            }
                            jsonObject=new JSONObject(result.get(result.size()-1));
                            int a=0;
                            //the response is stored in result
                            //note that in the case that the response is in jason format you will have to JSON objects

                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        try {
                            models.get(position).setLikes(jsonObject.getInt("likes"));
                            //post_likes.setText("number of likes " +post_search.get(i).getLikes());
                            return jsonObject.getString("response");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }


                    @Override
                    protected void onPostExecute(String s) {
                        super.onPostExecute(s);
                        notifyDataSetChanged();
                    }
                }.execute();
            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}
