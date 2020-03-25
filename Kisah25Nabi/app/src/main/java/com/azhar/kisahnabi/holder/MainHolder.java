package com.azhar.kisahnabi.holder;

import android.view.View;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.azhar.kisahnabi.R;

/**
 * Created by Azhar Rivaldi on 22-12-2019.
 */

public class MainHolder extends RecyclerView.ViewHolder {

    public TextView txtName;
    public CardView cvList;

    public MainHolder(View view) {
        super(view);

        cvList = view.findViewById(R.id.cvList);
        txtName = view.findViewById(R.id.txtName);

    }
}
