package edu.scranton.lunchilicious;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
public class NewItemDialog extends AppCompatDialogFragment {
    String selectedType;
    String name;
    String description;
    String price;
    RadioButton side;
    RadioButton pizza;
    RadioButton hoggie;
    EditText descriptionInput;
    EditText priceInput;
    EditText nameString;
    ViewModel viewModel;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v =inflater.inflate(R.layout.newitem_layout,null);
        viewModel = new ViewModelProvider(this.requireActivity()).get(MyViewModel.class);
        side=v.findViewById(R.id.sideButton);
        pizza=v.findViewById(R.id.pizzaButton);
        hoggie=v.findViewById(R.id.hoggieButton);
        descriptionInput=v.findViewById(R.id.descriptionET);
        priceInput=v.findViewById(R.id.priceET);
        nameString=v.findViewById(R.id.nameET);
        side.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedType="side";
            }
        });
        pizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedType="Pizza";
            }
        });
        hoggie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedType="hoggie";
            }
        });
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(v).setTitle("New Item").setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        }).setPositiveButton("Add To Menu", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                name=nameString.getText().toString();
                description=descriptionInput.getText().toString();
                price=priceInput.getText().toString();
                MenuItem NewItem = new MenuItem(100,selectedType,name,description,Double.valueOf(price));
                Toast.makeText(getContext(), NewItem.mName + " Was Added to the Menu!" , Toast.LENGTH_SHORT).show();
                ((MainActivity)getActivity()).addMenuitem(NewItem);
            }
        });
        return builder.create();
    }
}