package ch.zli.yourelate;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.time.LocalTime;

import ch.zli.yourelate.databinding.FragmentFirstBinding;

public class MainFragment extends Fragment {
    private String name;
    private double longitude;
    private double latitude;
    private LocalTime time;

    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(MainFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

        binding.buttonTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(MainFragment.this)
                        .navigate(R.id.action_FirstFragment_to_timeFragment);
            }
        });

        //Zusammenstellung von code aus chatgpt und https://stackoverflow.com/questions/4459058/alarm-manager-example
        binding.buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long timeinnano = time.toNanoOfDay();
                long timeinmilli = timeinnano / 1000000L;
                long millitime = timeinmilli - System.currentTimeMillis();
                AlarmManager am =( AlarmManager)getActivity().getSystemService(Context.ALARM_SERVICE);
                Intent i = new Intent(getActivity(), DataService.class);
                PendingIntent pi = PendingIntent.getBroadcast(getActivity(), 0, i, 0);
                am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), millitime, pi);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    public LocalTime getTime() {
        return time;
    }
    public void setTime(LocalTime time) {
        this.time = time;
    }
}