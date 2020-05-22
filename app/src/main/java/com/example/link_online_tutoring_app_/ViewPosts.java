package com.example.link_online_tutoring_app_;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.link_online_tutoring_app_.FacultiesAcitvities.CommerceActivity;
import com.example.link_online_tutoring_app_.FacultiesAcitvities.HealthScienceActivity;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static androidx.core.content.ContextCompat.startActivity;

public  class ViewPosts extends AppCompatActivity {

    static ArrayList<Post>posts;
    RecyclerView recyclerView;
     @SuppressLint("StaticFieldLeak")
     static PostAdapter postAdapter;
     static Map<Integer,ArrayList<Reply>> replies;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_posts);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //the activity that starts this activity must pass course id to it
        int course_id = getIntent().getIntExtra("course_id", -1); //get course id that was passed from previous activity that started this
        posts=new ArrayList<>();
        replies=new HashMap<>();
        recyclerView=findViewById(R.id.post_recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        new FetchPosts(course_id,recyclerView,this).execute();




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.post_menu,menu);
        MenuItem item=menu.findItem(R.id.search_post);
        SearchView searchView= (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                postAdapter.getFilter().filter(s);
                return true;
            }
        });
        return true;
    }

}





class Post{
    private int id;
    private String caption;
    private String poster;
    private String date;
    private String time;
    private  int likes;
    private String photo_url;

    public Post(int id, String caption, String poster, String date, String time, int likes, String photo_url) {
        this.id = id;
        this.caption = caption;
        this.poster = poster;
        this.date = date;
        this.time = time;
        this.likes = likes;
        this.photo_url = photo_url;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getId() {
        return id;
    }

    public String getCaption() {
        return caption;
    }

    public String getPoster() {
        return poster;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public int getLikes() {
        return likes;
    }

    public String getPhoto_url() {
        return photo_url;
    }
}

class Reply{
    private int id;
    private String message;
    private int likes;
    private String author;

    public Reply(int id, String message, int likes) {
        this.id = id;
        this.message = message;
        this.likes = likes;
    }

    public Reply(int id, String message, int likes, String author) {
        this.id = id;
        this.message = message;
        this.likes = likes;
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public int getLikes() {
        return likes;
    }
 }

 class FetchPosts extends AsyncTask<Void,Void,String> {
    private int course_id;
    private String result;
    @SuppressLint("StaticFieldLeak")
    private RecyclerView recyclerView;
    @SuppressLint("StaticFieldLeak")
    private Context context;

     public FetchPosts(int course_id,RecyclerView recyclerView,Context context) {
         this.course_id = course_id;
         this.context=context;
         this.recyclerView=recyclerView;
         result="";
     }

     @Override
     protected String doInBackground(Void... voids) {
         try{
         URL url=new URL("https://lamp.ms.wits.ac.za/~s1819369/trial_fetch_posts.php");
         HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
         httpURLConnection.setRequestMethod("POST");
         httpURLConnection.setDoInput(true);     //allows us to use input stream
         httpURLConnection.setDoOutput(true);    //allows us to use output stream

         OutputStream outputStream=httpURLConnection.getOutputStream(); //used to write to url
         BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8")); //used to load text  in buffer and formatting it before being sent

         String data= URLEncoder.encode("id","UTF-8")+"="+URLEncoder.encode(String.valueOf(course_id),"UTF-8");

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

         for (int i=0;i<jsonArray.length();i++){
             JSONObject jsonObject= (JSONObject) jsonArray.get(i);
             Post post=new Post(jsonObject.getInt("id"),jsonObject.getString("caption"),jsonObject.getString("poster"),
                     jsonObject.getString("date"), jsonObject.getString("time"),jsonObject.getInt("likes"),jsonObject.getString("photo"));
             ids.add(post.getId());
             ViewPosts.posts.add(post);
         }

         /* now to fetch best replies*/

             for (int post_id:ids) {
                 result = "";
                 url = new URL("https://lamp.ms.wits.ac.za/~s1819369/Sort.php");
                 httpURLConnection = (HttpURLConnection) url.openConnection();
                 httpURLConnection.setRequestMethod("POST");
                 httpURLConnection.setDoInput(true);     //allows us to use input stream
                 httpURLConnection.setDoOutput(true);    //allows us to use output stream

                 outputStream = httpURLConnection.getOutputStream(); //used to write to url
                 bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8")); //used to load text  in buffer and formatting it before being sent

                 data = URLEncoder.encode("post_id", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(post_id), "UTF-8");

                 bufferedWriter.write(data); //send data to the php file
                 bufferedWriter.flush();
                 bufferedWriter.close();

                 inputStream = httpURLConnection.getInputStream(); //used to get read data from the url
                 bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                 line = "";
                 while ((line = bufferedReader.readLine()) != null) {
                     //reading the response we got from the php file
                     result += line;
                 }


                 jsonArray = new JSONArray(result);
                 ArrayList<Reply>replyArrayList=new ArrayList<>();
                 for (int i = 0; i < jsonArray.length(); i++) {
                     JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                     Reply reply = new Reply(jsonObject.getInt("REPLY_ID"), jsonObject.getString("REPLY"), jsonObject.getInt("REPLY_LIKES"));
                     replyArrayList.add(reply);
                 }
                 ViewPosts.replies.put(post_id,replyArrayList);
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
             //TODO do something after fetching posts
             ViewPosts.postAdapter= new PostAdapter(context);
             recyclerView.setAdapter(ViewPosts.postAdapter);
         }
     }
 }

 class PostAdapter  extends RecyclerView.Adapter<PostAdapter.MyHolder> implements Filterable {
    private ArrayList<Post> posts ;
    //TODO create array list of best questions
    private ArrayList<Post> post_search ;
    private Context context;
    public static int FONT_SIZE;
    PostAdapter(Context context) {
        this.context = context;
        this.posts =ViewPosts.posts;
        post_search= new ArrayList<>(posts); //create a copy of posts for search purposes

    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ConstraintLayout constraintLayout = (ConstraintLayout) LayoutInflater.from(context).inflate(R.layout.post_template, viewGroup, false);
        return new MyHolder(constraintLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, final int i) {
       ConstraintLayout constraintLayout = myHolder.myLayout;
        TextView post_caption = constraintLayout.findViewById(R.id.post_caption);
        TextView post_author = constraintLayout.findViewById(R.id.post_author);
        TextView post_likes=constraintLayout.findViewById(R.id.post_likes);
        TextView post_time=constraintLayout.findViewById(R.id.post_time);
        TextView post_date=constraintLayout.findViewById(R.id.post_date);
        Button like=constraintLayout.findViewById(R.id.post_like_btn);
        Button goToComments=constraintLayout.findViewById(R.id.post_comments);

        post_caption.setText(post_search.get(i).getCaption());
        post_author.setText(post_search.get(i).getPoster());
        post_likes.setText("number of likes " +post_search.get(i).getLikes());
        post_date.setText(post_search.get(i).getDate());
        post_time.setText(post_search.get(i).getTime());
        final int Theid = post_search.get(i).getId();

        like.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public void onClick(View view) {
                //TODO erase the above toast and implement likes logic
                new AsyncTask<Void, Void, String>() {
                    @Override
                    protected String doInBackground(Void... voids) {
                        ArrayList<String> result=new ArrayList<>();
                        JSONObject jsonObject = null;

                        try {
                            Log.d("moving", "Still Good");
                            URL url=new URL("https://lamp.ms.wits.ac.za/~s1819369/Testing.php");
                            HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                            String post_id=String.valueOf(Theid);
                            SharedPreferences sharedPreferences1=LoginActivity.context.getSharedPreferences(LoginActivity.SHARED_PREF_LOGIN,Context.MODE_PRIVATE);
                            String user_id= sharedPreferences1.getString(RequestHandler.Unkey,null);

                            httpURLConnection.setRequestMethod("POST");
                            httpURLConnection.setDoInput(true);     //allows us to use input stream
                            httpURLConnection.setDoOutput(true);    //allows us to use output stream

                            OutputStream outputStream=httpURLConnection.getOutputStream(); //used to write to url
                            BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8")); //used to load text  in buffer and formatting it before being sent

                            String data= URLEncoder.encode("id","UTF-8")+"="+URLEncoder.encode(post_id,"UTF-8")+"&&"+URLEncoder.encode("userid","UTF-8")+"="
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
                            post_search.get(i).setLikes(jsonObject.getInt("likes"));
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
        goToComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO erase the above toast and implement go to comments activity
                String id = Integer.toString(Theid);
                Intent intent = new Intent(context, ViewAnswers.class);
                intent.putExtra("post_id", id);
                context.startActivity(intent);

            }
        });
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
            List<Post> filteredItems=new ArrayList<>();
            if(charSequence==null || charSequence.length()==0){
                filteredItems.addAll(posts);
            }
            else{
                String filterPattern=charSequence.toString().toLowerCase().trim();
                for (Post post:posts){
                    if(post.getCaption().toLowerCase().contains(filterPattern)){
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


