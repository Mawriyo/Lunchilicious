package edu.scranton.lunchilicious;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.lifecycle.ViewModelProvider;

public class itemDialog extends AppCompatDialogFragment {
    int mID;
    String mType;
    String mName;
    String mDescription;
    String mUnitPrice;
    TextView type;
    TextView name;
    TextView description;
    TextView price;
    TextView totalAmount;
    Button add;
    Button minus;
    int numberOfItems=1;
    MyViewModel viewModel;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if(getArguments() !=null) {
            mType = getArguments().getString("TYPE");
            mName = getArguments().getString("NAME");
            mDescription = getArguments().getString("DESCRIPTION");
            mUnitPrice = getArguments().getString("PRICE");
          //  numberOfItems=viewModel.NumberOfItems;
           // Toast.makeText(getContext(), numberOfItems, Toast.LENGTH_LONG).show();
        }


        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v =inflater.inflate(R.layout.menu_item_layout,null);
        viewModel = new ViewModelProvider(this).get(MyViewModel.class);
        type= v.findViewById(R.id.foodTypeTV);
        name=v.findViewById(R.id.foodNameTV);
        description=v.findViewById(R.id.descriptionTV);
        price=v.findViewById(R.id.priceTV);
        totalAmount= v.findViewById(R.id.TotalamountTV);
        minus=v.findViewById(R.id.minusOne);
        add=v.findViewById(R.id.addOne);
        price.setText(mUnitPrice);
        description.setText(mDescription);
        type.setText(mType);
        totalAmount.setText(String.valueOf(numberOfItems));
        numberOfItems=viewModel.NumberOfItems;
        numberOfItems=viewModel.NumberOfItems;
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                viewModel.NumberOfItems+=1;
                numberOfItems=viewModel.NumberOfItems;
                totalAmount.setText(""+numberOfItems);

            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.NumberOfItems-=1;
                numberOfItems=viewModel.NumberOfItems;
                totalAmount.setText(String.valueOf(numberOfItems));
            }
        });
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(v).setTitle(mName).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).setPositiveButton("Add To Cart", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            Toast.makeText(getContext(), "Added to Cart", Toast.LENGTH_LONG).show();
            numberOfItems= Integer.valueOf(totalAmount.getText().toString());
            }
        });
        return builder.create();
    }

    @Override
    public void onResume() {
        super.onResume();
       // totalAmount.setText(""+numberOfItems);
      //  Toast.makeText(getContext(), "SAVE# " + numberOfItems, Toast.LENGTH_LONG).show();
    }
}
