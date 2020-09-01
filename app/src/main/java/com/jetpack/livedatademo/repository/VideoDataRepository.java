package com.jetpack.livedatademo.repository;
import com.jetpack.livedatademo.entities.VideoHomeTabBean;
import com.jetpack.livedatademo.http.ApiClient;
import io.reactivex.Observable;

public class VideoDataRepository {

    public static Observable<VideoHomeTabBean> getFullDataRepository(String type) {

        Observable<VideoHomeTabBean> videoHomeTabBeanObservable = ApiClient.getVideoDataService().getVideoHomeTabData(type);
        return videoHomeTabBeanObservable;
    }
}
