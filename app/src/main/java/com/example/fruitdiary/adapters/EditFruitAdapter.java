package com.example.fruitdiary.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fruitdiary.R;
import com.example.fruitdiary.Utils;
import com.example.fruitdiary.models.EntryFruitDetails;
import com.example.fruitdiary.models.Fruit;
import com.example.fruitdiary.presenters.FruitPresenter;

import java.util.List;

public class EditFruitAdapter extends  RecyclerView.Adapter<EditFruitAdapter.MyViewHolder> {

    private List<EntryFruitDetails> entryFruitDetails;
    private Activity activity;
    private boolean showButtons;

    public EditFruitAdapter(Activity activity, List<EntryFruitDetails> entryFruitDetails, boolean showButtons){
        this.entryFruitDetails= entryFruitDetails;
        this.activity = activity;
        this.showButtons = showButtons;

    }

    @NonNull
    @Override
    public EditFruitAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = activity.getLayoutInflater().inflate(R.layout.entry_fruit_item, parent, false);
        return new EditFruitAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EditFruitAdapter.MyViewHolder holder, int position) {
        holder.setFruitDetails(entryFruitDetails.get(position));
    }

    @Override
    public int getItemCount() {
        return entryFruitDetails.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView fruitName;
        private TextView fruitVitamins;
        private ImageView imageView;
        private Button increaseAmount;
        private Button decreaseAmout;
        private TextView amountView;
        private Fruit fruit;


        MyViewHolder(View view){
            super(view);
            fruitName = view.findViewById(R.id.entry_fruit_name);
            fruitVitamins = view.findViewById(R.id.total_vitamin_amount);
            imageView = view.findViewById(R.id.entry_fruit_picture);
            increaseAmount = view.findViewById(R.id.increase_fruit_amount);
            decreaseAmout = view.findViewById(R.id.decrease_fruit_amount);
            amountView = view.findViewById(R.id.fruit_amount);
            if(!showButtons){
                increaseAmount.setVisibility(View.GONE);
                decreaseAmout.setVisibility(View.GONE);
            }
        }

        void setFruitDetails(EntryFruitDetails entryFruitDetails){
            fruitName.setText(entryFruitDetails.getType());
            amountView.setText(activity.getString(R.string.eaten) + entryFruitDetails.getAmount());
            findFruit(entryFruitDetails);
            fruitVitamins.setText(activity.getString(R.string.vitamins) + calculateVitamins(entryFruitDetails));
            Glide.with(activity).load(Utils.BASE_URL + fruit.getImageRelativePath()).into(imageView);
        }

        void findFruit(EntryFruitDetails entryFruitDetails){

            for(Fruit fruit: FruitPresenter.FRUIT_LIST){
                if(entryFruitDetails.getId() == fruit.getId()){
                    this.fruit = fruit;
                    break;
                }
            }
        }

        int calculateVitamins(EntryFruitDetails entryFruitDetails){
            return  entryFruitDetails.getAmount() * fruit.getVitamins();
        }

        @Override
        public void onClick(View v) {

        }
    }
}
