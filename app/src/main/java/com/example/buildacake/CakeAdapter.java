package com.example.buildacake;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CakeAdapter extends ArrayAdapter<Cake> {

    private Context mContext;
    private List<Cake> cakeList;

    public CakeAdapter(@NonNull Context context, ArrayList<Cake> list) {
        super(context, 0, list);
        mContext = context;
        cakeList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);

        Cake currentCake = cakeList.get(position);

        TextView price = listItem.findViewById(R.id.text_view_price);
        float cakePriceFloat = currentCake.getCakePrice();
        int cakePriceInt = Math.round(cakePriceFloat);
        String cakePriceString = cakePriceInt + " kn";
        price.setText(cakePriceString);

        TextView size = listItem.findViewById(R.id.text_view_size);
        size.setText(currentCake.getCakeSize());

        TextView message = listItem.findViewById(R.id.text_view_message);
        message.setText(currentCake.getCakeMessage());
        // move display message text view if user doesn't write anything
        RelativeLayout.LayoutParams msgLp = (RelativeLayout.LayoutParams) message.getLayoutParams();
        String msgText = currentCake.getCakeMessage();
        if (msgText.equals("/")) {
            msgLp.addRule(RelativeLayout.BELOW, R.id.text_view_toppings);
            msgLp.addRule(RelativeLayout.RIGHT_OF, R.id.msg_label);
            message.setLayoutParams(msgLp);
        } else {
            msgLp.addRule(RelativeLayout.BELOW, R.id.msg_label);
            msgLp.removeRule(RelativeLayout.RIGHT_OF);
        }

        TextView icing = listItem.findViewById(R.id.text_view_icing);
        icing.setText(currentCake.getCakeIcing());

        TextView biscuit = listItem.findViewById(R.id.text_view_biscuit);
        biscuit.setText(currentCake.getCakeBiscuit());

        TextView filling = listItem.findViewById(R.id.text_view_filling);
        filling.setText(currentCake.getCakeFilling());

        TextView toppings = listItem.findViewById(R.id.text_view_toppings);
        toppings.setText(currentCake.getCakeToppings());
        // move display toppings text view if user doesn't write anything
        RelativeLayout.LayoutParams toppingsLp = (RelativeLayout.LayoutParams) toppings.getLayoutParams();
        String toppingsText = currentCake.getCakeToppings();
        if (toppingsText.equals("/")) {
            toppingsLp.addRule(RelativeLayout.BELOW, R.id.text_view_filling);
            toppingsLp.addRule(RelativeLayout.RIGHT_OF, R.id.toppings_label);
            toppings.setLayoutParams(toppingsLp);
        } else {
            toppingsLp.addRule(RelativeLayout.BELOW, R.id.toppings_label);
            toppingsLp.removeRule(RelativeLayout.RIGHT_OF);
        }

        TextView dairy = listItem.findViewById(R.id.text_view_dairy);
        dairy.setText(currentCake.isDairyFree());

        TextView gluten = listItem.findViewById(R.id.text_view_gluten);
        gluten.setText(currentCake.isGlutenFree());

        TextView eggs = listItem.findViewById(R.id.text_view_egg);
        eggs.setText(currentCake.isEggFree());

        TextView info = listItem.findViewById(R.id.text_view_info);
        info.setText(currentCake.getAdditionalInfo());
        // move display info text view if user doesn't write anything
        RelativeLayout.LayoutParams infoLp = (RelativeLayout.LayoutParams) info.getLayoutParams();
        String infoText = currentCake.getAdditionalInfo();
        if (infoText.equals("/")) {
            infoLp.addRule(RelativeLayout.BELOW, R.id.text_view_egg);
            infoLp.addRule(RelativeLayout.RIGHT_OF, R.id.info_label);
            info.setLayoutParams(infoLp);
        } else {
            infoLp.addRule(RelativeLayout.BELOW, R.id.info_label);
            infoLp.removeRule(RelativeLayout.RIGHT_OF);
        }
        return listItem;
    }
}
