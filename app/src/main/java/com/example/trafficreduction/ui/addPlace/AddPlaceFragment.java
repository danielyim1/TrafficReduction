package com.example.trafficreduction.ui.addPlace;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trafficreduction.MainActivity;
import com.example.trafficreduction.R;

public class AddPlaceFragment extends Fragment {

    private AddPlaceViewModel addPlaceViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        addPlaceViewModel =
                ViewModelProviders.of(this).get(AddPlaceViewModel.class);
        View root = inflater.inflate(R.layout.fragment_add_place, container, false);
//        final TextView textView = root.findViewById(R.id.text_add_place);
//        addPlaceViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        SearchView search = root.findViewById(R.id.searchView);
        RecyclerView scroll = root.findViewById(R.id.recyclerView);
//        final AddPlaceAdapter adapter = new AddPlaceAdapter();
        final AddPlaceAdapter adapter = ((MainActivity) getActivity()).getAddPlaceAdapter();
        scroll.setAdapter(adapter);
        scroll.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
//                adapter.onQuerySubmit(query);
                ((MainActivity) getActivity()).autocomplete(query,adapter);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
//                adapter.onQueryChange(newText);
                ((MainActivity) getActivity()).autocomplete(newText,adapter);
                return false;
            }
        });

        return root;
    }
}
