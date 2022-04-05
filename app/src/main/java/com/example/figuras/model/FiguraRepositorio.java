package com.example.figuras.model;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class FiguraRepositorio { Executor executor = Executors.newSingleThreadExecutor();
    BaseDeDatos.FiguraDao figuraDao;

    public FiguraRepositorio(Application application) {
        figuraDao = BaseDeDatos.getInstance(application).obetenerFiguraDao();
    }

    public LiveData<List<Figura>> obtenerFigura() {
        return figuraDao.obtenerFiguras();
    }

    public LiveData<List<Figura>> obtenerFigurasIniciales() {
        return figuraDao.obtenerFigurasIniciales();
    }

    public void insertarFigura(String titulo, String descripcion, String portada) {
        executor.execute(() -> {
            figuraDao.insertarFigura(new Figura(titulo, descripcion, portada));
        });
    }

    public void eliminarFigura(Figura figura) {
        executor.execute(() -> {
            figuraDao.eliminarFigura(figura);
        });
    }
}
