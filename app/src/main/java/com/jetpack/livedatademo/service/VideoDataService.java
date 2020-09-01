package com.jetpack.livedatademo.service;
import com.jetpack.livedatademo.entities.VideoHomeTabBean;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface VideoDataService {

    @GET("{type}")
    Observable<VideoHomeTabBean> getVideoHomeTabData(@Path("type") String type);
}
