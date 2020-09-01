package com.jetpack.livedatademo.http;
import com.jetpack.livedatademo.service.VideoDataService;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static VideoDataService getVideoDataService() {

        VideoDataService videoDataService = initService(VideoDataService.class);
        return videoDataService;
    }

    /**
     * 获得想要的 retrofit service
     * @param clazz    想要的 retrofit service 接口，Retrofit会代理生成对应的实体类
     * @param <T>      api service
     * @return
     */
    private static <T> T initService(Class<T> clazz) {
        return getRetrofitInstance().create(clazz);
    }

    /**单例retrofit*/
    private volatile static Retrofit retrofitInstance;
    private static Retrofit getRetrofitInstance() {
        if (retrofitInstance == null) {
            synchronized (ApiClient.class) {
                if (retrofitInstance == null) {
                    retrofitInstance = new Retrofit.Builder()
                            .baseUrl(ApiConstant.BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .client(getOkHttpClientInstance())
                            .build();
                }
            }
        }
        return retrofitInstance;
    }

    /**单例OkHttpClient*/
    private volatile static OkHttpClient okHttpClientInstance;
    private static OkHttpClient getOkHttpClientInstance() {
        if (okHttpClientInstance == null) {
            synchronized (ApiClient.class) {
                if (okHttpClientInstance == null) {
                    OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
//                    if (BuildConfig.DEBUG) {
//                        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
//                        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//                        builder.addInterceptor(httpLoggingInterceptor);
//                      //builder.addNetworkInterceptor(new StethoInterceptor());
//                      //BuildConfig.STETHO.addNetworkInterceptor(builder);
//                    }
                    okHttpClientInstance = builder.build();
                }
            }
        }
        return okHttpClientInstance;
    }

}
