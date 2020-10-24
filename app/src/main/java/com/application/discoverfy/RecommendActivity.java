package com.application.discoverfy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class RecommendActivity extends AppCompatActivity {

    // variables that will store the RecyclerView, the Adapter and the LayoutManager
    private RecyclerView recommendRecyclerView;
    private RecyclerView.Adapter recommendAdapter;
    private RecyclerView.LayoutManager recommendLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend);

        // create an array list to store the items in the RecyclerView
        ArrayList<RecommendListItem> recommendListItems = new ArrayList<>();
        recommendListItems.add(new RecommendListItem("1.", "Falling", "Harry Styles"));
        recommendListItems.add(new RecommendListItem("2.", "Perfect", "Ed Sheeran"));
        recommendListItems.add(new RecommendListItem("3.", "2002", "Anne-Marie"));
        recommendListItems.add(new RecommendListItem("4.", "What a Man Gotta Do", "Jonas Brothers"));
        recommendListItems.add(new RecommendListItem("5.", "Not About Angels", "Birdy"));
        recommendListItems.add(new RecommendListItem("6.", "Hold My Girl", "George Ezra"));
        recommendListItems.add(new RecommendListItem("7.", "Lonely", "Noah Cyrus"));
        recommendListItems.add(new RecommendListItem("8.", "Naked", "James Arthur"));
        recommendListItems.add(new RecommendListItem("9.", "Love Me Like You Do", "Ellie Goulding"));
        recommendListItems.add(new RecommendListItem("10.", "Dance Monkey", "Tones and I"));
        recommendListItems.add(new RecommendListItem("11.", "Falling", "Harry Styles"));
        recommendListItems.add(new RecommendListItem("12.", "Perfect", "Ed Sheeran"));
        recommendListItems.add(new RecommendListItem("13.", "2002", "Anne-Marie"));
        recommendListItems.add(new RecommendListItem("14.", "What a Man Gotta Do", "Jonas Brothers"));
        recommendListItems.add(new RecommendListItem("15.", "Not About Angels", "Birdy"));
        recommendListItems.add(new RecommendListItem("16.", "Hold My Girl", "George Ezra"));
        recommendListItems.add(new RecommendListItem("17.", "Lonely", "Noah Cyrus"));
        recommendListItems.add(new RecommendListItem("18.", "Naked", "James Arthur"));
        recommendListItems.add(new RecommendListItem("19.", "Love Me Like You Do", "Ellie Goulding"));
        recommendListItems.add(new RecommendListItem("20.", "Dance Monkey", "Tones and I"));

        // initialise the RecyclerView
        recommendRecyclerView = findViewById(R.id.rv_recommended_songs);
        // recommendRecyclerView will not change in size
        recommendRecyclerView.setHasFixedSize(true);
        // assign LayoutManager
        recommendLayoutManager = new LinearLayoutManager(this);
        // assign Adapter and pass in the array list of recommendations
        recommendAdapter = new RecommendAdapter(recommendListItems);

        // pass the LayoutManager to the RecyclerView
        recommendRecyclerView.setLayoutManager(recommendLayoutManager);
        // pass the Adapter to the RecyclerView
        recommendRecyclerView.setAdapter(recommendAdapter);
    }
}