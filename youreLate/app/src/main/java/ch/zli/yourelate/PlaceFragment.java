package ch.zli.yourelate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.util.ArrayList;
import java.util.HashMap;

import ch.zli.yourelate.databinding.FragmentSecondBinding;

public class PlaceFragment extends Fragment {

    private HashMap<String, Double> xcoordinates = new HashMap<>();
    private HashMap<String, Double> ycoordinates = new HashMap<>();

    private FragmentSecondBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(PlaceFragment.this)
                        .navigate(R.id.action_SecondFragment_to_addPlaceFragment);
            }
        });

        binding.buttonPlace1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(PlaceFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
    }

    public void addData(String name, double longitude, double latitude) {
        xcoordinates.put(name, longitude);
        ycoordinates.put(name,latitude);
    }

    public String getData(String name) {
        double longitude = xcoordinates.get(name);
        double latitude = ycoordinates.get(name);

        String coordinate = longitude + ", " + latitude;

        return coordinate;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}