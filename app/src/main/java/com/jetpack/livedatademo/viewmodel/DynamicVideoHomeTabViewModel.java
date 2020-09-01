package com.jetpack.livedatademo.viewmodel;
import android.app.Application;
import androidx.annotation.NonNull;

public class DynamicVideoHomeTabViewModel<T> extends BaseViewModel<T> {

    public DynamicVideoHomeTabViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}

