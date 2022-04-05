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
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;



import com.example.figuras.databinding.FragmentHomeBinding;
import com.example.figuras.databinding.ViewholderFiguraBinding;
import com.example.figuras.model.Figura;
import com.yalantis.pulltomakesoup.PullToRefreshView;

import java.util.List;


public class HomeFragment extends Fragment {

    private PullToRefreshView mPullToRefreshView;
    private static final int REFRESH_DELAY = 4000;


    private FragmentHomeBinding binding;
    private FiguraViewModel figuraViewModel;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentHomeBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

        figuraViewModel = new ViewModelProvider(requireActivity()).get(FiguraViewModel.class);

       // mPullToRefreshView = (PullToRefreshView) binding.pullToRefresh;
       // mPullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
           // @Override
            //public void onRefresh() {
              //  mPullToRefreshView.postDelayed(new Runnable() {
               //     @Override
               //     public void run() {
                 //       mPullToRefreshView.setRefreshing(false);
                 //   }
               // }, REFRESH_DELAY);
           // }
       // });

    }


    //class FiguraAdapter2 extends RecyclerView.Adapter<FiguraViewHolder2>{
        List<Figura> figuraList;

        //@NonNull
       // @Override
        //public FiguraViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
          //  return new FiguraViewHolder2(ViewholderFiguraBinding.inflate(getLayoutInflater(),parent,false));
        //}
       // @Override
        public void onBindViewHolder(@NonNull FiguraViewHolder2 holder, int position) {
            Figura figura = figuraList.get(position);
            holder.binding.titulo.setText(figura.titulo);

            Glide.with(holder.itemView).load(figura.portada).into(holder.binding.portada);



            // CUANDO DAS CLICL EN LA RECETA
            holder.itemView.setOnClickListener(v -> {
                figuraViewModel.seleccionar(figura);
                navController.navigate(R.id.action_homeFragment_to_recetaViewFragment);
            });


        }

    }

    class FiguraViewHolder2 extends RecyclerView.ViewHolder{
        ViewholderFiguraBinding binding;

        public FiguraViewHolder2(@NonNull ViewholderFiguraBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

