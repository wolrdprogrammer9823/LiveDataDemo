package com.jetpack.livedatademo.viewmodel;
import android.app.Application;
import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.disposables.CompositeDisposable;

public class BaseViewModel<T> extends AndroidViewModel {

    //生命周期观察的数据
    private MutableLiveData<T> liveObservableData = new MutableLiveData<>();

    //UI使用可观察到的数据
    public ObservableField<T> uiObservableData = new ObservableField<>();

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    private static final MutableLiveData ABSENT = new MutableLiveData();

    {
        ABSENT.setValue(null);
    }

    public BaseViewModel(@NonNull Application application) {
        super(application);

    }

    @NonNull
    @Override
    public <T extends Application> T getApplication() {
        return super.getApplication();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }

    //LiveData支持了LifeCycle生命周期检测
    public MutableLiveData<T> getLiveObservableData() {
        return liveObservableData;
    }

    //当主动改变时重新设置被观察的数据
    public void setUiObservableData(T uiObservableData) {
        this.uiObservableData.set(uiObservableData);
    }

}
