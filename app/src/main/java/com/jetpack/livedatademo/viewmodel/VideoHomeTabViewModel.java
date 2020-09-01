package com.jetpack.livedatademo.viewmodel;
import android.app.Application;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import com.jetpack.livedatademo.entities.VideoHomeTabBean;
import com.jetpack.livedatademo.http.ApiConstant;
import com.jetpack.livedatademo.repository.VideoDataRepository;
import com.jetpack.livedatademo.util.NetUtils;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class VideoHomeTabViewModel extends AndroidViewModel {

    //生命周期观察的数据
    private LiveData<VideoHomeTabBean> liveObservableData;

    //UI使用可观察到的数据
    public ObservableField<VideoHomeTabBean> uiObservableData = new ObservableField<>();

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    private static final MutableLiveData ABSENT = new MutableLiveData();

    {
        ABSENT.setValue(null);
    }

    public VideoHomeTabViewModel(@NonNull Application application) {
        super(application);

        liveObservableData = Transformations.switchMap(NetUtils.netConnected(application), new Function<Boolean, LiveData<VideoHomeTabBean>>() {
            @Override
            public LiveData<VideoHomeTabBean> apply(Boolean mIsConnected) {

                if (!mIsConnected) {
                    return ABSENT;
                }

                Log.d("ViewModel", "apply");

                final MutableLiveData<VideoHomeTabBean> applyData = new MutableLiveData<>();
                VideoDataRepository.getFullDataRepository(ApiConstant.VIDEO_HOME_TAB)
                                   .subscribeOn(Schedulers.io())
                                   .observeOn(AndroidSchedulers.mainThread())
                                   .subscribe(new Observer<VideoHomeTabBean>() {
                                       @Override
                                       public void onSubscribe(Disposable d) {
                                           compositeDisposable.add(d);
                                       }

                                       @Override
                                       public void onNext(VideoHomeTabBean videoHomeTabBean) {
                                           applyData.setValue(videoHomeTabBean);
                                       }
                                       @Override
                                       public void onError(Throwable e) {
                                           Log.d("ViewModel", "onError");
                                           Log.d("ViewModel", "Throwable:" + e.getMessage());
                                       }
                                       @Override
                                       public void onComplete() {
                                           Log.d("ViewModel", "onComplete");
                                       }
                                   });
                return applyData;
            }
        });
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }

    //LiveData支持了LifeCycle生命周期检测
    public LiveData<VideoHomeTabBean> getLiveObservableData() {
        return liveObservableData;
    }

    //当主动改变时重新设置被观察的数据
    public void setUiObservableData(VideoHomeTabBean uiObservableData) {
        this.uiObservableData.set(uiObservableData);
    }

}
