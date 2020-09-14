package com.example.android.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.android.R;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public class RetrofitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
    }

    public void retrofit(View view) {
        String baseUrl="https://www.wanandroid.com/article/list/0/";

        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).build();
        MyServer myServer = retrofit.create(MyServer.class);
        Call<ResponseBody> data = myServer.getData();

        data.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String json = response.body().string();
                    System.out.println("TAG:"+response.body().string());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
    public interface MyServer {
        //POST("search?")    POST("search")相同
        //@Field("key") String value post请求用来提交参数的
        //@FormUrlEncoded post请求提交form表单的时候如果有参数,需要填加这个注解,用来将提交的参数编码
        //post请求不提交参数,不要加,
        //如果有提交的参数,没有加@FormUrlEncoded
        //@Field和@FieldMap一样，@FieldMap只不过是把一个一个的参数,合成一个map
        @POST("search?")
        @FormUrlEncoded
        Call<ResponseBody> postData1(@Field("key") String appKey, @Field("name") String appKeyName);
        @POST("search")
        @FormUrlEncoded
        Call<ResponseBody> postData2(@FieldMap Map<String,Object> map);
        @GET("json?cid=60")
        Call<ResponseBody> getData();
    }
}
