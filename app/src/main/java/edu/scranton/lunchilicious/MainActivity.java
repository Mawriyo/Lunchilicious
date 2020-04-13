package edu.scranton.lunchilicious;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
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
    MyViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        if (savedInstanceState == null) {
            viewModel.menu = viewModel.getMenuItems();
        }
        if(findViewById(R.id.container)!=null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, MenuFragment.newInstance()).commit();
        }
        else{
            getSupportFragmentManager().beginTransaction().replace(R.id.Horizontal, MenuFragment.newInstance()).commit();
        }

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
