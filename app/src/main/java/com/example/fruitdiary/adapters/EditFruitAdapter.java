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
import com.example.fruitdiary.models.Entry;
import com.example.fruitdiary.models.EntryFruitDetails;
import com.example.fruitdiary.models.Fruit;
import com.example.fruitdiary.presenters.EntryPresenter;
import com.example.fruitdiary.presenters.FruitPresenter;
import com.example.fruitdiary.server.ServerSync;

import java.util.List;

import retrofit2.Response;

public class EditFruitAdapter extends  RecyclerView.Adapter<EditFruitAdapter.MyViewHolder> {

    private List<EntryFruitDetails> entryFruitDetails;
    private Activity activity;
    private boolean showButtons;
    private Entry entry;

    public EditFruitAdapter(Activity activity, Entry entry, boolean showButtons){
        this.entry = entry;
        this.entryFruitDetails= entry.getFruitList();
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

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, ServerSync {

        private TextView fruitName;
        private TextView fruitVitamins;
        private ImageView imageView;
        private Button increaseAmount;
        private Button decreaseAmout;
        private TextView amountView;
        private int amount;
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
            }else{
                increaseAmount.setOnClickListener(this);
                decreaseAmout.setOnClickListener(this);
            }

        }

        void setFruitDetails(EntryFruitDetails entryFruitDetails){
            fruitName.setText(entryFruitDetails.getType());
            amount = entryFruitDetails.getAmount();
            amountView.setText(activity.getString(R.string.eaten) + amount);
            findFruit(entryFruitDetails);
            fruitVitamins.setText(activity.getString(R.string.vitamins) + calculateVitamins(entryFruitDetails.getAmount()));
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


        int calculateVitamins(int amount){
            return amount * fruit.getVitamins();
        }

        @Override
        public void onClick(View v) {
            if(v.getId() == increaseAmount.getId()){
                increaseFruitNumber();
            }else if(v.getId() == decreaseAmout.getId()){
                decreaseFruitNumber();
            }
        }

        private void increaseFruitNumber(){
            amount += 1;
            new EntryPresenter(this).addFruitToEntry(entry.getId(), fruit.getId(), amount);
        }

        private void decreaseFruitNumber(){
            if(amount == 0){
                return;
            }
            amount -= 1;
            new EntryPresenter(this).addFruitToEntry(entry.getId(), fruit.getId(), amount);
        }

        @Override
        public void sync(Object object) {
            if(object != null){
                amountView.setText(activity.getString(R.string.eaten) + amount);
                fruitVitamins.setText(activity.getString(R.string.vitamins) + calculateVitamins(amount));
            }
        }

        @Override
        public void sync(Response response) {

        }

        @Override
        public void syncEntries(List<Entry> entries) {

        }
    }
}
