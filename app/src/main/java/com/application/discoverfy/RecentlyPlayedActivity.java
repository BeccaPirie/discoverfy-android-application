package com.application.discoverfy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class RecentlyPlayedActivity extends AppCompatActivity {

    // variables that will store the RecyclerView, the Adapter and the LayoutManager
    private RecyclerView recentRecyclerView;
    private RecyclerView.Adapter recentAdapter;
    private RecyclerView.LayoutManager recentLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recently_played);

        // create an array list to store the items in the RecyclerView
        ArrayList<RecentlyPlayedListItem> recentlyPlayedListItems = new ArrayList<>();
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("1.", "Someone You Loved", "Lewis Capaldi"));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("2.", "Wonder", "Shawn Mendes"));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("3.", "SCAR", "Ashton Irwin"));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("4.", "Careless", "Ella Eyre"));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("5.", "Black and White", "Niall Horan"));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("6.", "No Time To Die", "Billie Eilish"));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("7.", "Wildflower", "5 Seconds of Summer"));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("8.", "Moral of the Story", "Ashe"));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("9.", "Stranger", "Vistas"));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("10.", "Elephants", "The Snuts"));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("11.", "feel something", "Bea Miller"));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("12.", "Gimme Love", "Joji"));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("13.", "Electric Love", "BORNS"));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("14.", "Blinding Lights", "The Weeknd"));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("15.", "Golden", "Harry Styles"));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("16.", "Brick By Brick", "American Authors"));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("17.", "Painkiller", "Ruel"));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("18.", "Crash My Car", "COIN"));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("19.", "Someone To You", "BANNERS"));
        recentlyPlayedListItems.add(new RecentlyPlayedListItem("20.", "In the End", "Kodaline"));

        // initialise the RecyclerView
        recentRecyclerView = findViewById(R.id.rv_recently_played);
        // recommendRecyclerView will not change in size
        recentRecyclerView.setHasFixedSize(true);
        // assign LayoutManager
        recentLayoutManager = new LinearLayoutManager(this);
        // assign Adapter and pass in the array list of recently played songs
        recentAdapter = new RecentlyPlayedAdapter(recentlyPlayedListItems);

        // pass the LayoutManager to the RecyclerView
        recentRecyclerView.setLayoutManager(recentLayoutManager);
        // pass the Adapter to the RecyclerView
        recentRecyclerView.setAdapter(recentAdapter);
    }
}