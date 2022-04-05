package com.example.figuras;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;



import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SplashFragment extends Fragment {
    Executor executor = Executors.newSingleThreadExecutor();

    NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        MutableLiveData<Boolean> finishedLoading = new MutableLiveData<>();

        finishedLoading.observe(getViewLifecycleOwner(), aBoolean -> navController.navigate(R.id.action_splashFragment_to_loginFragment));

        executor.execute(() -> {
            try {
                // simula la carga de la app
                Thread.sleep(2000);
                finishedLoading.postValue(true);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

    }
}