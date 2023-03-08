package ch.zli.yourelate;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import ch.zli.yourelate.databinding.FragmentAddPlaceBinding;
import ch.zli.yourelate.databinding.FragmentSecondBinding;

public class PlaceFragment extends Fragment  {

    private HashMap<String, Double> xcoordinates = new HashMap<>();
    private HashMap<String, Double> ycoordinates = new HashMap<>();

    private FragmentSecondBinding binding;
    private ListView listView;
    private SharedPreferences sharedPreferences;
    private ListAdapter adapter;
    private MainFragment mainFragment;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSecondBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        listView = view.findViewById(R.id.listview);
        sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        // combine "longitude" and "latitude" from shared preferences to one string
        String gpsLocationsString = sharedPreferences.getString("names", "");
        // split string to get individual coordinates
        String[] names = gpsLocationsString.split("\\$");
        System.out.println(Arrays.toString(names));

        // add gps locations to list view
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, names);
        listView.setAdapter(adapter);

        // set click listener for list items
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NavHostFragment.findNavController(PlaceFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
        return view;
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
    }

    public void addData(String name, double longitude, double latitude) {
        xcoordinates.put(name, longitude);
        ycoordinates.put(name,latitude);
    }

    public String getData(String name) {
        double longitude = xcoordinates.get(name);
        double latitude = ycoordinates.get(name);

        String coordinate = longitude + "," + latitude;

        return coordinate;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}