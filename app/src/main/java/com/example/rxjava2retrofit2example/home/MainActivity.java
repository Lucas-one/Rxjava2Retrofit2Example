package com.example.rxjava2retrofit2example.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.rxjava2retrofit2example.R;
import com.example.rxjava2retrofit2example.databinding.ActivityMainBinding;
import com.example.rxjava2retrofit2example.github.GithubClient;
import com.example.rxjava2retrofit2example.github.GithubRepo;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private static final String OWNER = "Lucas-one";
    private RepoItemAdapter adapter;
    private Disposable disposable;
    private GithubClient github;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        adapter = new RepoItemAdapter(new ArrayList<>());
        binding.recyclerView.setAdapter(adapter);

        github = new GithubClient();
        disposable = github.getApi().getRepos(OWNER)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( items -> adapter.updateItems(items));
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        disposable.dispose();
    }
}
