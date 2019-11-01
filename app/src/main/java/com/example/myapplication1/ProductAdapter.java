package com.example.myapplication1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>  {
    private List<Product> productList;
    private Context context;

    public ProductAdapter(List<Product> productList, Context context) {
        this.productList = productList;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_element, parent, false);

        ProductViewHolder pvh = new ProductViewHolder(v);

        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product p = productList.get(position);
        holder.getName().setText(p.getName());
        holder.getPrice().setText(p.getPrice()+"");
        holder.getCount().setText(p.getCount()+"");
        holder.getBought().setChecked(p.isBought());

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView name;
        private TextView price;
        private TextView count;
        private CheckBox bought;

        public TextView getName() {
            return name;
        }

        public void setName(TextView name) {
            this.name = name;
        }

        public TextView getPrice() {
            return price;
        }

        public void setPrice(TextView price) {
            this.price = price;
        }

        public TextView getCount() {
            return count;
        }

        public void setCount(TextView count) {
            this.count = count;
        }

        public CheckBox getBought() {
            return bought;
        }

        public void setBought(CheckBox bought) {
            this.bought = bought;
        }

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            this.name= itemView.findViewById(R.id.tvName);
            this.price=itemView.findViewById(R.id.tvPrice);
            this.count=itemView.findViewById(R.id.tvCount);
            this.bought=itemView.findViewById(R.id.cbBought);

            itemView.setOnClickListener(this);

            bought.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        Toast.makeText(ProductAdapter.this.context,
                                name.getText()+" kupione.",
                                Toast.LENGTH_LONG).show();
                    }else
                    {

                    }
                }
            });
        }


        @Override
        public void onClick(View v) {
            Toast.makeText(ProductAdapter.this.context,
                    "zaznaczono "+ name.getText(),
                    Toast.LENGTH_LONG).show();

        }
    }
}
