package com.example.rxjava2retrofit2example.home;


import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rxjava2retrofit2example.databinding.ItemRepoBinding;
import com.example.rxjava2retrofit2example.github.GithubRepo;

import java.util.List;

public class RepoItemAdapter extends RecyclerView.Adapter<RepoItemAdapter.ViewHolder> {
    private final List<GithubRepo> repos;

    RepoItemAdapter(List<GithubRepo> repos){
        this.repos = repos;
    }

    void updateItems(List<GithubRepo> items){
        this.repos.clear();
        this.repos.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public RepoItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RepoItemAdapter.ViewHolder(ItemRepoBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RepoItemAdapter.ViewHolder holder, int position) {
        holder.bind(repos.get(position));
    }

    @Override
    public int getItemCount() {
        return repos.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private final ItemRepoBinding binding;

        ViewHolder(ItemRepoBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }

        private void bind(GithubRepo repo){
            binding.ownerView.setText(repo.owner);
            binding.nameView.setText(repo.name);
            binding.urlView.setText(repo.url);
        }
    }
}
