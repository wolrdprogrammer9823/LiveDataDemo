package com.jetpack.livedatademo;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.jetpack.livedatademo.databinding.VideoHomeTabItemBinding;
import com.jetpack.livedatademo.entities.VideoHomeTabBean;
import java.util.List;

public class VideoHomeTabAdapter extends RecyclerView.Adapter<VideoHomeTabAdapter.VideoHomeTabHolder> {

    private List<VideoHomeTabBean.HomeTabInfo> homeTabInfoList;
    private VideoHomeTabItemCallback videoHomeTabItemCallback;

    public VideoHomeTabAdapter(@NonNull VideoHomeTabItemCallback videoHomeTabItemCallback) {
        this.videoHomeTabItemCallback = videoHomeTabItemCallback;
    }

    @NonNull
    @Override
    public VideoHomeTabHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        VideoHomeTabItemBinding videoHomeTabItemBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.video_home_tab_item, parent, false);
        return new VideoHomeTabHolder(videoHomeTabItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoHomeTabHolder holder, int position) {

        holder.videoHomeTabItemBinding.setHomeTabItem(homeTabInfoList.get(position));
        holder.videoHomeTabItemBinding.setCallback(videoHomeTabItemCallback);
        holder.videoHomeTabItemBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {

        return homeTabInfoList == null ? 0 : homeTabInfoList.size();
    }

    public void setVideoHomeTabDataSet(final List<VideoHomeTabBean.HomeTabInfo> dataSet) {
        if (homeTabInfoList == null) {
            homeTabInfoList = dataSet;
            this.notifyItemRangeChanged(0, homeTabInfoList.size());
        } else {
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return homeTabInfoList.size();
                }

                @Override
                public int getNewListSize() {
                    return dataSet.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {

                    VideoHomeTabBean.HomeTabInfo oldHomeTabInfo = homeTabInfoList.get(oldItemPosition);
                    VideoHomeTabBean.HomeTabInfo newHomeTabInfo = homeTabInfoList.get(newItemPosition);
                    return oldHomeTabInfo.getId() == newHomeTabInfo.getId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {

                    VideoHomeTabBean.HomeTabInfo oldHomeTabInfo = homeTabInfoList.get(oldItemPosition);
                    VideoHomeTabBean.HomeTabInfo newHomeTabInfo = homeTabInfoList.get(newItemPosition);
                    return oldHomeTabInfo.getId() == newHomeTabInfo.getId()
                            && oldHomeTabInfo.getApiUrl().equals(newHomeTabInfo.getApiUrl())
                            && oldHomeTabInfo.getName().equals(newHomeTabInfo.getName());
                }
            });

            homeTabInfoList = dataSet;
            diffResult.dispatchUpdatesTo(this);
        }
    }

    static class VideoHomeTabHolder extends RecyclerView.ViewHolder {

        VideoHomeTabItemBinding videoHomeTabItemBinding;

        public VideoHomeTabHolder(VideoHomeTabItemBinding videoHomeTabItemBinding) {

            super(videoHomeTabItemBinding.getRoot());
            this.videoHomeTabItemBinding = videoHomeTabItemBinding;
        }
    }
}
