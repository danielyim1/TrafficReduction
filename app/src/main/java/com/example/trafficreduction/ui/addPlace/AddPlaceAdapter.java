package com.example.trafficreduction.ui.addPlace;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.amazonaws.mobileconnectors.apigateway.ApiClientFactory;
import com.example.trafficreduction.GetPopularTimesClient;
import com.example.trafficreduction.R;
import com.example.trafficreduction.model.PopularTimesModel;
import com.example.trafficreduction.ui.home.HomeAdapter;

import java.util.ArrayList;
import java.util.List;

import static androidx.core.content.ContextCompat.startActivity;

public class AddPlaceAdapter extends RecyclerView.Adapter<AddPlaceAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;
        public TextView addressTextView;
        public ImageButton addPlaceButton;
        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.place_name);
            addressTextView = (TextView) itemView.findViewById(R.id.place_address);
            addPlaceButton = (ImageButton) itemView.findViewById(R.id.place_add);
        }
    }

    private List<Place> myPlaces;
    private HomeAdapter homeAdapter;

    public AddPlaceAdapter(HomeAdapter homeAdapter) {
        myPlaces = new ArrayList<>();
//        myPlaces.add(new Place("Place 1", "id1","hello"));
//        myPlaces.add(new Place("Place 2", "id2","hi"));
        this.homeAdapter = homeAdapter;
    }

    public void setPlaces(List<Place> places) {
        myPlaces.clear();
        myPlaces.addAll(places);
//        myPlaces = places;
        this.notifyDataSetChanged();
    }

    @Override
    public AddPlaceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View placeView = inflater.inflate(R.layout.add_place_item, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(placeView);
        viewHolder.addPlaceButton.setBackgroundResource(R.drawable.ic_add_black_24dp);
        return viewHolder;
    }

//    Place place;

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(final AddPlaceAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        final Place place = myPlaces.get(position);
        // Set item views based on your views and data model
//        TextView textView = viewHolder.nameTextView;
//        TextView addressView = viewHolder.addressTextView;
        viewHolder.nameTextView.setText(place.getName());
        viewHolder.addressTextView.setText(place.getAddress());
        viewHolder.addPlaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (place.getAdded()) {
                    return;
                }
                new GetPopularTimesTask(place).execute();
//                place.setPopTimes(getPopularTimes(place));
                homeAdapter.getMyPlaces().add(place);
                viewHolder.addPlaceButton.setBackgroundResource(R.drawable.done_black_24dp);
                place.setAdded(true);
//                PopularTimes popTimes = getPopularTimes(place);

                //openActivity()
//                Intent intent = new Intent(view.getContext(), HomeViewModel.class);
//                view.getContext().startActivity(intent);
            }
        });
//        Button button = viewHolder.messageButton;
//        button.setText(contact.isOnline() ? "Message" : "Offline");
//        button.setEnabled(contact.isOnline());
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return myPlaces.size();
    }
}
