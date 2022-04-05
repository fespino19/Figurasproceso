package com.example.figuras;

import android.app.Application;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.figuras.model.Figura;
import com.example.figuras.model.FiguraRepositorio;

import java.util.List;

public class FiguraViewModel extends AndroidViewModel {

    public MutableLiveData<Uri> imagenSeleccionada = new MutableLiveData<>();

    MutableLiveData<Figura> figuraSeleccionado = new MutableLiveData<>();

    FiguraRepositorio figuraRepositorio;

    public FiguraViewModel(@NonNull Application application) {
        super(application);

        figuraRepositorio = new FiguraRepositorio(application);
    }

    public LiveData<List<Figura>> obtenerFigura() {
        return figuraRepositorio.obtenerFigura();
    }

    public LiveData<List<Figura>> obtenerFigurasIniciales() {
        return figuraRepositorio.obtenerFigurasIniciales();
    }

    public void insertarFigura(String titulo, String descripcion, String portada) {
        figuraRepositorio.insertarFigura(titulo, descripcion, portada);
    }

    public void eliminarFigura(Figura figura){
        figuraRepositorio.eliminarFigura(figura);
    }

    public void establecerImagenSeleccionada(Uri uri){
        imagenSeleccionada.setValue(uri);
    }

    void seleccionar(Figura figura){
        figuraSeleccionado.setValue(figura);
    }

    MutableLiveData<Figura> seleccionado(){
        return figuraSeleccionado;
    }

}
