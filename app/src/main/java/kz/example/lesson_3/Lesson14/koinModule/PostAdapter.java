package kz.example.lesson_3.Lesson14.koinModule;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import kz.example.lesson_3.Lesson14.model.Post;
import kz.example.lesson_3.R;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {
    private List<Post> postList = new ArrayList<>();

    public PostAdapter(List<Post> postList) {
        if (postList != null) {
            this.postList = postList;
        }
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.post_item, viewGroup, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int i) {
        Post post = postList.get(i);
        holder._tvTitle.setText(post.getTitle());
        holder._tvBody.setText(post.getBody());
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public void setPostList(List<Post> posts) {
        this.postList = posts;
        notifyDataSetChanged();
    }

    static class PostViewHolder extends RecyclerView.ViewHolder {
        TextView _tvTitle;
        TextView _tvBody;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            _tvTitle = itemView.findViewById(R.id.tvTitle);
            _tvBody = itemView.findViewById(R.id.tvBody);

        }
    }
}
