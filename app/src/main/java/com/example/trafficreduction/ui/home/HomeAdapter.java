package com.example.trafficreduction.ui.home;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trafficreduction.R;
import com.example.trafficreduction.ui.addPlace.Place;
import com.example.trafficreduction.ui.addPlace.PopularTimes;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import  java.util.Date;
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;
        public TextView addressTextView;
        public TextView popularityTextView;
        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.place_name);
            addressTextView = (TextView) itemView.findViewById(R.id.place_address);
            popularityTextView = (TextView) itemView.findViewById(R.id.popularity);

        }
    }

    private List<Place> myPlaces;
    AppCompatActivity myActivity;
    public HomeAdapter(AppCompatActivity activity) {
        myPlaces = new ArrayList<>();
        myActivity = activity;
//        myPlaces.add(new Place("Place 1", "id1","hello"));
//        myPlaces.add(new Place("Place 2", "id2","hi"));
    }

    public void addPlace(Place place) {
//        myPlaces.clear();
//        myPlaces.addAll(places);
//        myPlaces = places;
//        this.notifyDataSetChanged();
        myPlaces.add(place);
    }

    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View placeView = inflater.inflate(R.layout.home_place_item, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(placeView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(final HomeAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Place place = myPlaces.get(position);
        // Set item views based on your views and data model
//        TextView textView = viewHolder.nameTextView;
//        TextView addressView = viewHolder.addressTextView;
        viewHolder.nameTextView.setText(place.getName());
        viewHolder.addressTextView.setText(place.getAddress());
        Date date = new Date();
        final Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        final Observer<PopularTimes> observer = new Observer<PopularTimes>() {
            @Override
            public void onChanged(@Nullable final PopularTimes popTimes) {
                // Update the UI, in this case, a TextView.
                if (popTimes == null) {
                    viewHolder.popularityTextView.setText("");
                } else {
                    String popularity = Integer.toString(popTimes.getPopularity(calendar.get(Calendar.DAY_OF_WEEK) - 1, calendar.get(Calendar.HOUR_OF_DAY)));
                    viewHolder.popularityTextView.setText(popularity);
                }
            }
        };
       place.getPopTimes().observe(myActivity, observer);
//        Button button = viewHolder.messageButton;
//        button.setText(contact.isOnline() ? "Message" : "Offline");
//        button.setEnabled(contact.isOnline());
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return myPlaces.size();
    }

    public List<Place> getMyPlaces() {return myPlaces; }

}
