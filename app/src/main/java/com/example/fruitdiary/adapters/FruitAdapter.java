package com.example.fruitdiary.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fruitdiary.R;
import com.example.fruitdiary.Utils;
import com.example.fruitdiary.models.Fruit;
import com.example.fruitdiary.presenters.FruitPresenter;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.MyViewHolder> {

    private Activity activity;
    private boolean pickMode;
    private AdapterGlue adapterGlue;

    public FruitAdapter(Activity activity, boolean pickMode, AdapterGlue adapterGlue) {
        this.activity = activity;
        this.pickMode = pickMode;
        this.adapterGlue = adapterGlue;
    }


    @NonNull
    @Override
    public FruitAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (pickMode) {
            view = activity.getLayoutInflater().inflate(R.layout.fruit_item_small, parent, false);
        } else {
            view = activity.getLayoutInflater().inflate(R.layout.fruit_item, parent, false);
        }
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FruitAdapter.MyViewHolder holder, int position) {
        Fruit fruit = FruitPresenter.FRUIT_LIST.get(position);
        holder.fruitName.setText(fruit.getType());
        holder.fruitVitamins.setText(activity.getString(R.string.vitamins) + fruit.getVitamins());
        holder.fruit = fruit;
        Glide.with(activity).load(Utils.BASE_URL + fruit.getImageRelativePath()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return FruitPresenter.FRUIT_LIST.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView fruitName;
        private TextView fruitVitamins;
        private ImageView imageView;
        private Fruit fruit;


        MyViewHolder(View view) {
            super(view);
            fruitName = view.findViewById(R.id.fruit_name);
            fruitVitamins = view.findViewById(R.id.vitamin_amount);
            imageView = view.findViewById(R.id.fruit_picture);
            if (pickMode)
                view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            adapterGlue.attachFruit(fruit);
        }
    }
}
