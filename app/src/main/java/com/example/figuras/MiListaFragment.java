package com.example.figuras;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;


import com.example.figuras.databinding.FragmentMiListaBinding;
import com.example.figuras.databinding.ViewholderFiguraBinding;
import com.example.figuras.model.Figura;

import java.util.List;

public class MiListaFragment extends Fragment {
    NavController navController;

    private FragmentMiListaBinding binding;
    private FiguraViewModel figuraViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentMiListaBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        figuraViewModel = new ViewModelProvider(requireActivity()).get(FiguraViewModel.class);
        navController = Navigation.findNavController(view);

        FiguraAdapter figuraAdapter = new FiguraAdapter();
        binding.listaFigura.setAdapter(figuraAdapter);

        figuraViewModel.obtenerFigura().observe(getViewLifecycleOwner(), figura ->{
            figuraAdapter.setFiguraList(figura);
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                ItemTouchHelper.RIGHT  | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int posicion = viewHolder.getAdapterPosition();
                Figura figura = figuraAdapter.obtenerElemento(posicion);
                figuraViewModel.eliminarFigura(figura);
            }
        }).attachToRecyclerView(binding.listaFigura);
    }

    class FiguraAdapter extends RecyclerView.Adapter<FiguraViewHolder>{

        List<Figura> figuraList;

        @NonNull
        @Override
        public FiguraViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new FiguraViewHolder(ViewholderFiguraBinding.inflate(getLayoutInflater(),parent,false));

        }

        @Override
        public void onBindViewHolder(@NonNull FiguraViewHolder holder, int position) {
            Figura figura = figuraList.get(position);
            holder.binding.titulo.setText(figura.titulo);

            Glide.with(holder.itemView).load(figura.portada).into(holder.binding.portada);

            // CUANDO DAS CLICL EN LA FIGURA
            holder.itemView.setOnClickListener(v -> {
                figuraViewModel.seleccionar(figura);
                navController.navigate(R.id.action_miListaFragment_to_miFiguraViewFragment);
            });

        }

        @Override
        public int getItemCount() {
            return figuraList == null ? 0 : figuraList.size();
        }

        void setFiguraList(List<Figura> figuraList){
            this.figuraList = figuraList;
            notifyDataSetChanged();
        }

        public Figura obtenerElemento(int posicion){
            return figuraList.get(posicion);
        }
    }

    class FiguraViewHolder extends RecyclerView.ViewHolder{
        ViewholderFiguraBinding binding;

        public FiguraViewHolder(@NonNull ViewholderFiguraBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}