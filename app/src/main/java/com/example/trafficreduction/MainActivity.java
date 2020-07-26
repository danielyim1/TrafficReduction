package com.example.trafficreduction;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.trafficreduction.ui.addPlace.Place;
import com.example.trafficreduction.ui.addPlace.AddPlaceAdapter;
import com.example.trafficreduction.ui.home.HomeAdapter;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.model.AutocompleteSessionToken;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsResponse;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private PlacesClient placesClient;
    private AddPlaceAdapter addPlaceAdapter;
    private HomeAdapter homeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_add_place, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        // Initialize the SDK
        Places.initialize(getApplicationContext(), "AIzaSyCaPB2KEhHOA6JBchhiJw5w-fZtezQZ_NA");

        // Create a new Places client instance
        placesClient = Places.createClient(this);

        homeAdapter = new HomeAdapter(this);
        addPlaceAdapter = new AddPlaceAdapter(homeAdapter);

    }

    public HomeAdapter getHomeAdapter() {
        return homeAdapter;
    }

    public AddPlaceAdapter getAddPlaceAdapter() {
        return addPlaceAdapter;
    }

    public void autocomplete(String query, final AddPlaceAdapter adapter) {
        // Create a new token for the autocomplete session. Pass this to FindAutocompletePredictionsRequest,
        // and once again when the user makes a selection (for example when calling fetchPlace()).
        AutocompleteSessionToken token = AutocompleteSessionToken.newInstance();

//        // Create a RectangularBounds object.
//        RectangularBounds bounds = RectangularBounds.newInstance(
//                new LatLng(-33.880490, 151.184363),
//                new LatLng(-33.858754, 151.229596));
        // Use the builder to create a FindAutocompletePredictionsRequest.
        FindAutocompletePredictionsRequest request = FindAutocompletePredictionsRequest.builder()
                // Call either setLocationBias() OR setLocationRestriction().
//                .setLocationBias(bounds)
                //.setLocationRestriction(bounds)
//                .setOrigin(new LatLng(-33.8749937,151.2041382))
//                .setCountries("AU", "NZ")
//                .setTypeFilter(TypeFilter.ADDRESS)
                .setSessionToken(token)
                .setQuery(query)
                .build();

        placesClient.findAutocompletePredictions(request).addOnSuccessListener(new OnSuccessListener<FindAutocompletePredictionsResponse>() {
            @Override
            public void onSuccess(FindAutocompletePredictionsResponse response) {
                List<Place> placesResult = new ArrayList<>();
                for (AutocompletePrediction prediction : response.getAutocompletePredictions()) {
//                    Log.i("My app", prediction.getPlaceId());
//                    Log.i("My app", prediction.getPrimaryText(null).toString());
                    placesResult.add(new Place(prediction.getPrimaryText(null).toString(),prediction.getPlaceId(),prediction.getSecondaryText(null).toString()));

                }
                adapter.setPlaces(placesResult);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                if (exception instanceof ApiException) {
                    ApiException apiException = (ApiException) exception;
                    Log.e("My app", "Place not found: " + apiException.getStatusCode());
                }
            }
        });
    }


}
