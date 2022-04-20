package com.example.figuras;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import com.example.figuras.R;
import com.example.figuras.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((binding = ActivityMainBinding.inflate(getLayoutInflater())).getRoot());

        NavController navController = ((NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_fragment)).getNavController();
        NavigationUI.setupWithNavController(binding.bottomNavigation, navController);

        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {

            if (destination.getId() == R.id.splashFragment || destination.getId() == R.id.loginFragment || destination.getId() == R.id.insertarFiguraFragment) {
                binding.bottomNavigation.setVisibility(View.GONE);
            } else {
                binding.bottomNavigation.setVisibility(View.VISIBLE);
            }

            if (destination.getId() == R.id.figuraViewFragment  || destination.getId() == R.id.settingFragment || destination.getId() == R.id.splashFragment || destination.getId() == R.id.loginFragment || destination.getId() == R.id.insertarFiguraFragment || destination.getId()== R.id.perfilFragment || destination.getId()== R.id.buscarFragment2){
                binding.buttonAdd.setVisibility(View.GONE);
            }else {
                binding.buttonAdd.setVisibility(View.VISIBLE);
            }


        });

        binding.buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.insertarFiguraFragment);
            }
        });
    }

}