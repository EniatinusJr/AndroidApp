package ch.zli.yourelate;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import ch.zli.yourelate.databinding.FragmentFirstBinding;
import ch.zli.yourelate.databinding.FragmentTimeBinding;

public class TimeFragment extends Fragment {
    MainFragment mainFragment = new MainFragment();

    private FragmentTimeBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentTimeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonChangeTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocalTime newTime = LocalTime.parse(binding.changetime.getText().toString());
                mainFragment.setTime(newTime);
                NavHostFragment.findNavController(TimeFragment.this)
                        .navigate(R.id.action_timeFragment_to_FirstFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}