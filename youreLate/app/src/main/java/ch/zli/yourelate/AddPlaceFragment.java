package ch.zli.yourelate;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ch.zli.yourelate.databinding.FragmentAddPlaceBinding;
import ch.zli.yourelate.databinding.FragmentSecondBinding;

public class AddPlaceFragment extends Fragment {
    PlaceFragment placeFragment = new PlaceFragment();

    private FragmentAddPlaceBinding binding;
    private SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentAddPlaceBinding.inflate(inflater, container, false);
        sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonAddPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                placeFragment.addData(binding.name.getText().toString(), Double.parseDouble(binding.xcoordinates.getText().toString()), Double.parseDouble(binding.ycoordinates.getText().toString()));
                String names = sharedPreferences.getString("names", "");
                System.out.println(names);
                names.concat("$").concat(binding.name.getText().toString());
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("names", names);
                editor.apply();
                NavHostFragment.findNavController(AddPlaceFragment.this)
                        .navigate(R.id.action_addPlaceFragment_to_SecondFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}