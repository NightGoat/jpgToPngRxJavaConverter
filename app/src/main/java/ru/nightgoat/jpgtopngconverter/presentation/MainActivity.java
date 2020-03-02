package ru.nightgoat.jpgtopngconverter.presentation;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import ru.nightgoat.jpgtopngconverter.domain.Interactor;
import ru.nightgoat.jpgtopngconverter.data.Model;
import ru.nightgoat.jpgtopngconverter.R;
import ru.nightgoat.jpgtopngconverter.data.RepositoryImpl;
import ru.nightgoat.jpgtopngconverter.ResourceHolder;
import ru.nightgoat.jpgtopngconverter.ViewModelFactory;


public class MainActivity extends AppCompatActivity {

    Button convertBtn;
    MainViewModel mainViewModel;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Injection
        ResourceHolder resourceHolder = new ResourceHolder(getApplicationContext());
        Model model = new Model(resourceHolder);
        RepositoryImpl repository = new RepositoryImpl(model);
        Interactor interactor = new Interactor(repository, resourceHolder);
        ViewModelFactory viewModelFactory = new ViewModelFactory(interactor);

        mainViewModel = new ViewModelProvider(this, viewModelFactory).get(MainViewModel.class);
        mainViewModel.isSuccess.observe(this, data -> {
            if (data) {
                Toast.makeText(this, "Конвертация прошла успешно", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(this, "Конвертация не прошла", Toast.LENGTH_LONG).show();
            }
            alertDialog.dismiss();
        });

        convertBtn = findViewById(R.id.convertBtn);
        convertBtn.setOnClickListener(v -> {
            Log.d("MainActivity", "onCreate: button pressed, start converting...");
            mainViewModel.convert();
            ProgressBar progressBar = new ProgressBar(this);
            alertDialog = new AlertDialog.Builder(this)
                    .setMessage("Выполняется конвертация")
                    .setNegativeButton("Отменить",
                            (dialog, which) -> mainViewModel.interrupt()).setView(progressBar).create();
            alertDialog.show();
        });
    }

}
