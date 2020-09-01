package com.jetpack.livedatademo;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.widget.Toast;

import com.jetpack.livedatademo.databinding.ActivityMainBinding;
import com.jetpack.livedatademo.entities.VideoHomeTabBean;
import com.jetpack.livedatademo.viewmodel.VideoHomeTabViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mainBinding;
    private VideoHomeTabAdapter videoHomeTabAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        VideoHomeTabViewModel videoHomeTabViewModel = ViewModelProviders.of(MainActivity.this).get(VideoHomeTabViewModel.class);
        videoHomeTabAdapter = new VideoHomeTabAdapter(videoHomeTabItemCallback);
        mainBinding.setRecyclerAdapter(videoHomeTabAdapter);
        subscribeToModel(videoHomeTabViewModel);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainBinding.unbind();
    }

    VideoHomeTabItemCallback videoHomeTabItemCallback = new VideoHomeTabItemCallback() {
        @Override
        public void onTabItemClick(VideoHomeTabBean.HomeTabInfo homeTabInfo) {
            Toast.makeText(MainActivity.this, "type:" + homeTabInfo.getName(), Toast.LENGTH_SHORT).show();
        }
    };

    private void subscribeToModel(final VideoHomeTabViewModel videoHomeTabViewModel) {
        videoHomeTabViewModel.getLiveObservableData().observe(this, new Observer<VideoHomeTabBean>() {
            @Override
            public void onChanged(VideoHomeTabBean videoHomeTabBean) {

                videoHomeTabViewModel.setUiObservableData(videoHomeTabBean);

                videoHomeTabAdapter.setVideoHomeTabDataSet(videoHomeTabBean.getResult());

                //Log.d("ViewModel", "code:" + videoHomeTabBean.getCode() + ",message:" + videoHomeTabBean.getMessage());
                //Log.d("ViewModel", "result:" + videoHomeTabBean.getResult().size());

                //for (VideoHomeTabBean.HomeTabInfo homeTabInfo :  videoHomeTabBean.getResult()) {
                //    Log.d("ViewModel", "url:" + homeTabInfo.getApiUrl());
                //}
            }
        });
    }

}