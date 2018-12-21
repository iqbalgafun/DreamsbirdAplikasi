package com.example.iqbalpriansyah.iqbalfauzi;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class BodyPartFragment extends Fragment{
    //variabel Global
    private List<Integer> imageid;
    private Integer indexImg;
    private static String TAG="BodyPartsFragment";
    public static final String IMAGE_ID_LIST = "image_ids";
    public static final String LIST_INDEX = "list_index";

    public BodyPartFragment() {

    }
    //penyimpanan sementara variable array
    public void onSaveInstanceState(@NonNull Bundle currentState) {
        currentState.putIntegerArrayList(IMAGE_ID_LIST, (ArrayList<Integer>) imageid);
        currentState.putInt(LIST_INDEX,indexImg);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //menjalankan bundle ArrayList
        if(savedInstanceState!=null){
            imageid=savedInstanceState.getIntegerArrayList(IMAGE_ID_LIST);
            indexImg=savedInstanceState.getInt(LIST_INDEX);
        }
        //return super.onCreateView(inflater, container, savedInstanceState);

        //membuat tampilan fragment dari fragment layout
        View rootView = inflater.inflate(R.layout.fragment_layout, container,  false);
        //menyatakan bahwa gambat akan diletakkan pada id ImageViewFragment
        final ImageView imageViewVar = (ImageView)rootView.findViewById(R.id.ImageViewFragment);
        //mengisi id ImageView dengan gambar resource
        BodyImageAsset varAssets = new BodyImageAsset();
        //get 0 menandakan mengambil data pertama
        //imageViewVar.setImageResource(varAssets.getHeads().get(0));
        imageViewVar.setImageResource(imageid.get(indexImg));
        imageViewVar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick (View v){

                if (indexImg < imageid.size() - 1) {
                    indexImg++;
                } else {
                    indexImg = 0;
                }
                imageViewVar.setImageResource(imageid.get(indexImg));
            }
        });

        return rootView;
    }

    //memanggil imageid
    public void setImageid(List<Integer> imageid) {
        this.imageid = imageid;
    }

    //memanggil setindeximg
    public void setIndexImg(Integer indexImg) {
        this.indexImg = indexImg;
    }
}
