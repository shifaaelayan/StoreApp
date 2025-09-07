package com.example.store.adapter;
import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

//import com.bumptech.glide.Glide;
//import com.bumptech.glide.request.RequestOptions;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.store.R;
import com.example.store.model.Product;

import java.util.ArrayList;
import java.util.List;

public class productAdapter extends ArrayAdapter<Product> {

    private List<Product> productList;
    private boolean flag = false ;

    //the context object
    private Context mCtx;

    public productAdapter(Context context, List<Product> products){

        super(context,R.layout.list_item,products);
        this.productList = products;
        this.mCtx = context;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //get products data at certain position using Product class
        Product product = getItem(position);

        if(convertView == null){

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }


        ImageView img = (ImageView) convertView.findViewById(R.id.imageView2);
        TextView name = (TextView) convertView.findViewById(R.id.productName);
        TextView price = (TextView) convertView.findViewById(R.id.productPrice);

        // update the code to display the items
        name.setText(productList.get(position).getName());
        price.setText(productList.get(position).getPrice());

        // must to use Glide library: it is to retrieve images from the server and then show them in the ImageView
        Glide.with(getContext())
                .load("http://10.0.2.2/Store/uploads/"+productList.get(position).getImg())
                .apply(new RequestOptions().override(600, 600)) // resize for all images
                .error(R.drawable.notfound) // if an error is happen for some image instead it show another one
                .into(img); // to show images in ImageView

        return convertView;
    }
}
