package com.example.link_online_tutoring_app_;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import org.json.JSONArray;
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
import java.util.List;
import java.util.Map;

import managers.AsyncHTTP;




public class ChatActivity extends AppCompatActivity {
    static String YellowLight = "OFF";
    Button btnSend_, btnReload_;
    TextView receiverName;
    static EditText messageET;
    static String receiver, receiverStudNum, senderStudNum;
    static String message, sender;
    static ArrayList<Chat>chats;
    static int my_id,other_user_id;

    RecyclerView recyclerView;

   public Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        if(YellowLight == "ON") {
            SharedPreferences sharedPreferences1 = LoginActivity.context.getSharedPreferences(LoginActivity.SHARED_PREF_LOGIN, Context.MODE_PRIVATE);
            String user_id = sharedPreferences1.getString(RequestHandler.Unkey, "-1");
            my_id = Integer.parseInt(user_id);
            sender = sharedPreferences1.getString("my_username", "null");
        }
        else{
            my_id = 90;
            sender = "Murphy";
        }

//        if(YellowLight == "OFF"){
//            my_id = 90;
//            sender = "90";
//        }

        receiverName = findViewById(R.id.receiverName);
        btnReload_ = findViewById(R.id.btnReload);
        btnReload_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new FetchChat(my_id,other_user_id,recyclerView,getApplicationContext()).execute();
            }
        });
        btnSend_ = findViewById(R.id.btnSend);

        messageET = findViewById(R.id.messageEText);
        if(YellowLight == "ON") {
            Intent intent = getIntent();
            receiver = intent.getStringExtra("receiver");
            other_user_id = intent.getIntExtra("receiver_id", -1);
        }
        else{
            receiver = "some letters";
            other_user_id= 1122;
//            messageET.setText("Sending answer to someone 2.0", TextView.BufferType.EDITABLE);
        }
//        if(YellowLight == "OFF"){
//            receiver = "Murphy";
//            other_user_id = 90;
//        }
        receiverName.setText((receiver));

        recyclerView = findViewById(R.id.rvMessages);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        chats=new ArrayList<>();
        new FetchChat(my_id,other_user_id,recyclerView,this).execute();

        thread=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //every 7 seconds check for new message
                    Thread.sleep(5000);
                    new FetchChat(my_id,other_user_id,recyclerView,getApplicationContext()).execute();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });



    }






    public void sendMessage(View view) {

        message = messageET.getText().toString().trim();
        if(YellowLight == "ON") {
            SharedPreferences Prefs = LoginActivity.context.getSharedPreferences(LoginActivity.SHARED_PREF_LOGIN, Context.MODE_PRIVATE);
            senderStudNum = String.valueOf(my_id);
            receiverStudNum = String.valueOf(other_user_id);
        }
        else{
            senderStudNum = "90";
            receiverStudNum = "1122";
        }
//        if(YellowLight == "OFF"){
//            senderStudNum = "90";
//            receiverStudNum = "90";
//        }
        //sender = "Donald";
        /* check sender on onCreate method*/



        boolean checkMyThings = true;
        if (TextUtils.isEmpty(message)) {
            messageET.setError("Enter Your Message");
            checkMyThings = false;
        }
        if (checkMyThings) {

            new RequestHandler(ChatActivity.this, "send message").execute(message, sender, receiver, receiverStudNum, senderStudNum);
            messageET.getText().clear();

        } else {

            Toast toast = Toast.makeText(ChatActivity.this, "Please type something...", Toast.LENGTH_SHORT);
            toast.show();
        }


    }


}



class Chat{
    private int id;
    private String message;
    private String date;
    private String time;
    private String sender;
    private int sender_id;
    private String receiver;
    private int receiver_id;

    public Chat(int id, String message, String date, String time, String sender, int sender_id, String receiver, int receiver_id) {
        this.id = id;
        this.message = message;
        this.date = date;
        this.time = time;
        this.sender = sender;
        this.sender_id = sender_id;
        this.receiver = receiver;
        this.receiver_id = receiver_id;
    }

    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getSender() {
        return sender;
    }

    public int getSender_id() {
        return sender_id;
    }

    public String getReceiver() {
        return receiver;
    }

    public int getReceiver_id() {
        return receiver_id;
    }
}

class ChatsAdapter  extends RecyclerView.Adapter<ChatsAdapter.MyHolder> implements Filterable {
    private ArrayList<Chat> posts ;
    //TODO create array list of best questions
    private ArrayList<Chat> post_search ;
    private Context context;
    public static int FONT_SIZE;
    ChatsAdapter(Context context) {
        this.context = context;
        this.posts =ChatActivity.chats;
        post_search= new ArrayList<>(posts); //create a copy of posts for search purposes

    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ConstraintLayout constraintLayout = (ConstraintLayout) LayoutInflater.from(context).inflate(R.layout.message_template, viewGroup, false);
        return new MyHolder(constraintLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, final int i) {
        ConstraintLayout constraintLayout = myHolder.myLayout;

        TextView message_author = constraintLayout.findViewById(R.id.senderID);
        TextView message_time=constraintLayout.findViewById(R.id.message_timeID);
        TextView message_date=constraintLayout.findViewById(R.id.message_dateID);
        TextView message_text=constraintLayout.findViewById(R.id.messageID);

        message_author.setText(post_search.get(i).getSender());
        message_text.setText(post_search.get(i).getMessage());
        message_date.setText(post_search.get(i).getDate());
        message_time.setText(post_search.get(i).getTime());

    }



    @Override
    public int getItemCount() {
        return post_search.size();
    }

    @Override
    public Filter getFilter() {
        return postsFilter;
    }

    private Filter postsFilter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Chat> filteredItems=new ArrayList<>();
            if(charSequence==null || charSequence.length()==0){
                filteredItems.addAll(posts);
            }
            else{
                String filterPattern=charSequence.toString().toLowerCase().trim();
                for (Chat post:posts){
                    if(post.getMessage().toLowerCase().contains(filterPattern)){
                        //filter based on caption
                        //if a post contains desired character sequences
                        filteredItems.add(post);
                    }
                }
            }
            FilterResults results=new FilterResults();
            results.values=filteredItems;
            return results;
        }



        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            post_search.clear();
            post_search.addAll((List)filterResults.values);
            notifyDataSetChanged();
        }
    };

    class MyHolder extends RecyclerView.ViewHolder {
        ConstraintLayout myLayout;

        MyHolder(@NonNull View itemView) {
            super(itemView);
            myLayout = (ConstraintLayout) itemView;
        }
    }
}

class FetchChat extends AsyncTask<Void,Void,String> {
    private int id;
    private int user_id;
    private String result;
    @SuppressLint("StaticFieldLeak")
    private RecyclerView recyclerView;
    @SuppressLint("StaticFieldLeak")
    private Context context;

    public FetchChat(int id,int user_id, RecyclerView recyclerView, Context context) {
        this.id = id;
        this.user_id=user_id;
        this.context=context;
        this.recyclerView=recyclerView;
        result="";
    }

    @Override
    protected String doInBackground(Void... voids) {
        try{
            URL url=new URL("https://lamp.ms.wits.ac.za/~s1819369/fetch_messages.php");
            HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);     //allows us to use input stream
            httpURLConnection.setDoOutput(true);    //allows us to use output stream

            OutputStream outputStream=httpURLConnection.getOutputStream(); //used to write to url
            BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8")); //used to load text  in buffer and formatting it before being sent

            String data= URLEncoder.encode("id","UTF-8")+"="+URLEncoder.encode(String.valueOf(id),"UTF-8")+"&&"+
                    URLEncoder.encode("userid","UTF-8")+"="+URLEncoder.encode(String.valueOf(user_id),"UTF-8");

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
            JSONArray jsonArray=new JSONArray(result);
            ArrayList<Integer>ids=new ArrayList<>();   //stores post ids ->used to fetch best comment

            ChatActivity.chats=new ArrayList<>();
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject= (JSONObject) jsonArray.get(i);
                Chat chat=new Chat(jsonObject.getInt("id"),jsonObject.getString("message"),jsonObject.getString("date"),jsonObject.getString("time"),
                        jsonObject.getString("sender"),jsonObject.getInt("sender_id"),jsonObject.getString("receiver"),jsonObject.getInt("receiver_id"));
                ChatActivity.chats.add(chat);
            }



        } catch (
                MalformedURLException e) {
            e.printStackTrace();
        } catch (
                IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "posts fetched";
    }

    @Override
    protected void onPostExecute(String s) {

        if(s.equals("posts fetched")){
            Log.d("Skey", s);
            //TODO do something after fetching posts
            //ViewPosts.postAdapter= new PostAdapter(context);
            recyclerView.setAdapter(new ChatsAdapter(context));
        }
    }
}

