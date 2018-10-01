package com.winsofteducationtechnologies.wetinstitute.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/*
Singleton class is used to make:
Ensure that only one instance of a class is created.
Provide a global point of access to the object
for that
it contains :
1) its private static instance
2) private default constructor so that is is called with in the same class so that only one object is created as in 1)
3) public synchronized method so that only one thread can use it at once

limitation:
1) unit test became difficult
2) difficult to debug as it has global use
*/

public class RetrofitClient {

    private static RetrofitClient uniqueInstance; // 1)
    private static final String baseURL = "http://192.168.43.251/RetrofitExample/public/";

    private Retrofit retrofit;

    private RetrofitClient(){// 2)
        retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RetrofitClient getInstance(){// 3)
        if(uniqueInstance == null){
            uniqueInstance = new RetrofitClient();
        }
        return uniqueInstance;
    }

    public  API getAPI() {
        return retrofit.create(API.class);
    }
}
