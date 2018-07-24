package com.example.rohit.retrofitdemo;

import android.content.Context;

import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class API {


    private static final String API_URL = "https://dog.ceo/api/";

    private static API sAPI;
    private static ApiInterface sAPIInterface;
    private Context mContext;


    public static API getSingleInstance(Context context) {
        if (sAPI == null) {
            synchronized (API.class) {
                if (sAPI == null) {
                    sAPI = new API(context);
                }
            }
        }

        return sAPI;
    }

    private API(Context context) {
        sAPIInterface = RestClient.getClient();
        mContext = context;
    }

    private static class RestClient {

        private static ApiInterface apiInterface;


        public static ApiInterface getClient() {

            if (apiInterface == null) {
                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

                OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(API_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(client)
                        .build();

                apiInterface = retrofit.create(ApiInterface.class);
            }
            return apiInterface;
        }
    }


    public interface ApiInterface {

        @GET("breeds/image/random")
        Call<ModelClass> checkNumberAlreadyLogin
                ();

    }
    public void checkNumberAlreadyLogin( final CallBacks.LoginCallback i_callback) {
        try {

            // 0 ->  Customer
            // 1 -> Driver

            Call<ModelClass> call = sAPIInterface.checkNumberAlreadyLogin();

            Callback<ModelClass> callback = new Callback<ModelClass>() {
                @Override
                public void onResponse(Call<ModelClass> call, Response<ModelClass> response) {
                    if (response.code() == 200) {
                        try {

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        i_callback.onSuccess(response.body());

                    } else if (response.code() == 400) {
                        try {
                            JSONObject mObject = new JSONObject(response.errorBody().string());

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        i_callback.onSuccess(response.body());


                    } else if (response.code() == 401) {
                        try {
                            JSONObject mObject = new JSONObject(response.errorBody().string());

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        i_callback.onSuccess(response.body());


                    } else if (response.code() == 404) {
                        try {
                            JSONObject mObject = new JSONObject(response.errorBody().string());

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        i_callback.onSuccess(response.body());


                    } else if (response.code() == 406) {
                        try {
                            JSONObject mObject = new JSONObject(response.errorBody().string());

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        i_callback.onSuccess(response.body());


                    } else {
                        i_callback.onSuccess(null);
                    }
                }

                @Override
                public void onFailure(Call<ModelClass> call, Throwable t) {




                    i_callback.onSuccess(null);
                }
            };

            call.enqueue(callback);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }










}