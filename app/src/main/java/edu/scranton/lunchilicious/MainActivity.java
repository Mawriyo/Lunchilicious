package edu.scranton.lunchilicious;
//IMPLEMENT VIEWMODEL HERE.
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;

import android.os.Bundle;

import java.text.Format;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    public Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    MyViewModel viewModel;
    int mID;
    String currentType;
    String currentName;
    String currentDescription;
    String currentUnitPrice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    mRecyclerView = findViewById(R.id.RecycleView);
    viewModel = new ViewModelProvider(this).get(MyViewModel.class);
    mRecyclerView.setHasFixedSize(true);
    mLayoutManager=new LinearLayoutManager(this);
    mAdapter = new Adapter(viewModel.getMenuItems());
    mRecyclerView.setLayoutManager(mLayoutManager);
    mRecyclerView.setAdapter(mAdapter);


   mAdapter.OnItemClick(new Adapter.OnItemClick() {
        @Override
        public void OnItemClick(int position) {
            MenuItem currentItem = viewModel.getMenuItems().get(position);
            currentType=viewModel.getMenuItems().get(position).getFoodType();
            currentName=viewModel.getMenuItems().get(position).getFoodName();
            currentDescription=viewModel.getMenuItems().get(position).getDescription();
            currentUnitPrice=viewModel.getMenuItems().get(position).getPrice();
            openDialog(currentType,currentName, currentDescription, currentUnitPrice);
        }
    });
    }
    public void openDialog(String Type, String Name,String Description,String UnitPrice){
    itemDialog dialog = new itemDialog();
    Bundle args = new Bundle();
    args.putString("TYPE",Type);
    args.putString("NAME",Name);
    args.putString("DESCRIPTION",Description);
    args.putString("UNITPRICE",UnitPrice);
    dialog.setArguments(args);
    dialog.show(getSupportFragmentManager(), "ITEM");
    }
    }
