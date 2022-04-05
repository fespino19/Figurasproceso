package com.example.figuras;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.figuras.databinding.FragmentFiltroBinding;


import java.util.ArrayList;
import java.util.List;

import me.originqiu.library.EditTag;


public class FiltroFragment extends DialogFragment {

    private FragmentFiltroBinding binding;
    private List<String> tagStrings = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentFiltroBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        //Set tag add callback before set tag list
        binding.editTagView.setTagAddCallBack(new EditTag.TagAddCallback() {
            @Override
            public boolean onTagAdd(String tagValue) {
                if ("test1".equals(tagValue)) {
                    return false;
                } else {
                    return true;
                }
            }
        });
        binding.editTagView.setTagDeletedCallback(new EditTag.TagDeletedCallback() {
            @Override
            public void onTagDelete(String deletedTagValue) {
                Toast.makeText(requireContext(), deletedTagValue, Toast.LENGTH_SHORT).show();
            }
        });
        binding.editTagView.setTagList(tagStrings);

        binding.editTagView.addTag("Receta");

    }

}