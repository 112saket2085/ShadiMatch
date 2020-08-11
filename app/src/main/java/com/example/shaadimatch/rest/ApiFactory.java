package com.example.shaadimatch.rest;

import com.example.shaadimatch.BuildConfig;
import com.example.shaadimatch.app.Constants;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by SAKET on 11/08/2020
 * Class contains all retrofit api configuration
 */
public class ApiFactory {

    private ShadiApi shadiApi;
    private static ApiFactory retrofitClient;
    public static ApiFactory getInstance() {
        if (retrofitClient == null) {
            retrofitClient = new ApiFactory();
        }
        return retrofitClient;
    }

    public ApiFactory() {
        init();
    }

    private void init() {
        Retrofit retrofit = createRetrofitClient(getOkHttpClient());
        shadiApi = retrofit.create(ShadiApi.class);
    }

    private OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
        if (!BuildConfig.BUILD_TYPE.equals(Constants.BUILD_TYPE_RELEASE)) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            // set your desired log level
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpBuilder.addNetworkInterceptor(logging);
        }
        httpBuilder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession sslSession) {
                return hostname.equalsIgnoreCase(BuildConfig.HOST_NAME);
            }
        });
        httpBuilder.connectTimeout(4, TimeUnit.MINUTES);
        httpBuilder.readTimeout(4, TimeUnit.MINUTES);
//        SSLManager.setSslSocketFactory(httpBuilder);
        return httpBuilder.build();
    }

    /**
     * Method to get Retrofit instance
     */
    private Retrofit createRetrofitClient(OkHttpClient okClient) {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okClient)
                .build();
    }

    /**
     * Method to get shadi instance
     * @return shadi instance
     */
    public ShadiApi getShadiApi() {
        return shadiApi;
    }

}
