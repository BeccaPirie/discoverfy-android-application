package com.application.discoverfy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class RecentlyPlayedActivity extends AppCompatActivity {

    private RecyclerView recentRecyclerView;
    private RecyclerView.Adapter recentAdapter;
    private RecyclerView.LayoutManager recentLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recently_played);

        ArrayList<RecentlyPlayedListItem> recentlyPlayedListItems = new ArrayList<>();
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("1.", "Someone You Loved", "Lewis Capaldi"));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("2.", "Someone You Loved", "Lewis Capaldi"));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("3.", "Someone You Loved", "Lewis Capaldi"));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("4.", "Someone You Loved", "Lewis Capaldi"));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("5.", "Someone You Loved", "Lewis Capaldi"));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("6.", "Someone You Loved", "Lewis Capaldi"));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("7.", "Someone You Loved", "Lewis Capaldi"));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("8.", "Someone You Loved", "Lewis Capaldi"));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("9.", "Someone You Loved", "Lewis Capaldi"));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("10.", "Someone You Loved", "Lewis Capaldi"));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("11.", "Someone You Loved", "Lewis Capaldi"));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("12.", "Someone You Loved", "Lewis Capaldi"));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("13.", "Someone You Loved", "Lewis Capaldi"));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("14.", "Someone You Loved", "Lewis Capaldi"));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("15.", "Someone You Loved", "Lewis Capaldi"));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("16.", "Someone You Loved", "Lewis Capaldi"));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("17.", "Someone You Loved", "Lewis Capaldi"));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("18.", "Someone You Loved", "Lewis Capaldi"));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("19.", "Someone You Loved", "Lewis Capaldi"));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("20.", "Someone You Loved", "Lewis Capaldi"));


        recentRecyclerView = findViewById(R.id.rv_recently_played);
        recentRecyclerView.setHasFixedSize(true);
        recentLayoutManager = new LinearLayoutManager(this);
        recentAdapter = new RecentlyPlayedAdapter(recentlyPlayedListItems);
        recentRecyclerView.setLayoutManager(recentLayoutManager);
        recentRecyclerView.setAdapter(recentAdapter);
    }
}