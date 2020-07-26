package com.example.trafficreduction.ui.addPlace;

import android.os.AsyncTask;
import android.util.Log;

import com.amazonaws.mobileconnectors.apigateway.ApiClientFactory;
import com.example.trafficreduction.GetPopularTimesClient;
import com.example.trafficreduction.model.PopularTimesModel;

public class GetPopularTimesTask extends AsyncTask<Void, Void, Void> {
    private Place place;
    private PopularTimes times;
    public GetPopularTimesTask (Place place) {
        this.place = place;
    }
    @Override
    protected Void doInBackground(Void... voids) {
        ApiClientFactory factory = new ApiClientFactory();
        // create a client
        final GetPopularTimesClient client = factory.build(GetPopularTimesClient.class);

        // Invoke your placesGet method
        PopularTimesModel output = client.placesGet("ChIJ3RoD-z3AQIYRNT2H5RsjeTI");
        times = new PopularTimes(output);
        return null;
    }
    @Override
    public void onPostExecute(Void var)
    {
        place.setPopTimes(times);
    }
}
