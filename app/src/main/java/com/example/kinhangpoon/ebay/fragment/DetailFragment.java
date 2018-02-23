package com.example.kinhangpoon.ebay.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kinhangpoon.ebay.R;
import com.example.kinhangpoon.ebay.model.Category;
import com.squareup.picasso.Picasso;

/**
 * Created by KinhangPoon on 22/2/2018.
 */

public class DetailFragment extends Fragment {
    ImageView imageViewDetail;
    TextView textViewDetailName, textViewDetailDescription;
    Animation animation;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_fragment,container,false);

        imageViewDetail = view.findViewById(R.id.imageView_detail);
        textViewDetailName = view.findViewById(R.id.textView_detail_name);
        textViewDetailDescription = view.findViewById(R.id.textView_detail_description);

        int position = getArguments().getInt("position");
        textViewDetailName.setText(Category.categoryList.get(position).getCategoryName());
        textViewDetailDescription.setText(Category.categoryList.get(position).getCategoryDescription());
        Picasso.with(getContext()).load(Category.categoryList.get(position).getCategoryImageUrl()).into(imageViewDetail);

        //font
        Typeface typefaceName = Typeface.createFromAsset(getContext().getAssets(),"THE_JACATRA.otf");
        textViewDetailName.setTypeface(typefaceName);

        Typeface typefaceDes = Typeface.createFromAsset(getContext().getAssets(),"Sugar & Vinegar.ttf");
        textViewDetailDescription.setTypeface(typefaceDes);

        //animation
        animation = AnimationUtils.loadAnimation(getContext(),R.anim.fade_in);
        imageViewDetail.startAnimation(animation);
        return view;
    }
}
