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

import com.example.myapplication1.Database.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>  {
    private List<Product> productList;
    private Context context;
    private OnBoughtClickListener boughtClickListener;
    private OnItemCLickListener itemClickListener;
    private OnItemLongClickListener itemLongClickListener;

    public ProductAdapter(List<Product> productList, Context context) {
        this.productList = productList;
        this.context = context;
    }

    private LayoutInflater mInflater;

    public ProductAdapter(Context context){
        mInflater=LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       // View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_element, parent, false);
        View itemView = mInflater.inflate(R.layout.product_element,parent,false);
        // ProductViewHolder pvh = new ProductViewHolder(v);
    return new ProductViewHolder(itemView);
      //  return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        if(productList != null) {
            Product p = productList.get(position);
            holder.getName().setText(p.getName());
            holder.getPrice().setText(p.getPrice() + "");
            holder.getCount().setText(p.getCount() + "");
            holder.getBought().setChecked(p.isBought());
        }else{
            holder.getName().setText("pusto");
        }

    }

    void setProductList(List<Product> products){
        productList=products;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(productList !=null)
        return productList.size();
        else return 0;
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {//implements View.OnClickListener {

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


            bought.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(boughtClickListener !=null && position != RecyclerView.NO_POSITION){
                        boughtClickListener.OnBoughtClickListener(productList.get(position));
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int position = getAdapterPosition();
                    if(itemLongClickListener !=null && position != RecyclerView.NO_POSITION){
                        itemLongClickListener.OnItemLongClick(productList.get(position));
                    }
                    return true;
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(boughtClickListener != null && position != RecyclerView.NO_POSITION);
                        itemClickListener.onItemClick(productList.get(position));
                }
            });
        }
    }
    public interface OnBoughtClickListener {
        void OnBoughtClickListener(Product product);
    }
    public void setOnBoughtClickListener(OnBoughtClickListener onClickListener) {
        this.boughtClickListener = onClickListener;
    }
    public interface OnItemCLickListener {
        void onItemClick(Product product);
    }
    public interface OnItemLongClickListener {
        void OnItemLongClick(Product product);
    }
    public void setOnItemClickListener(OnItemCLickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener itemLongClickListener) {
        this.itemLongClickListener = itemLongClickListener;
    }

}
