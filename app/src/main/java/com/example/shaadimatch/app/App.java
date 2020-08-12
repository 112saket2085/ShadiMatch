package com.example.shaadimatch.app;

import android.app.Application;
import android.content.Context;
import com.example.shaadimatch.rest.ApiFactory;
import com.google.gson.Gson;

/**
 * Created by SAKET on 11/08/2020
 * Class that store all App related variables
 */
public class App extends Application {


    private ApiFactory apiFactory;
    private static App instance;
    private Gson gson;
    public App() {
        instance = this;
    }

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initRest();
        initGson();
    }

    /**
     * Initialise Gson Library
     */
    private void initGson() {
        gson = new Gson();
    }

    /**
     * Initialise api factory
     */
    public void initRest() {
        this.apiFactory = new ApiFactory();
    }

    /**
     * @return Context : Application context
     */
    public static Context getAppContext() {
        return instance.getApplicationContext();
    }


    /**
     * @return Gson instance
     */
    public Gson getGson() {
        return gson;
    }

    /**
     * @return ApiFactory instance
     */
    public ApiFactory getApiFactory() {
        return apiFactory;
    }

}
