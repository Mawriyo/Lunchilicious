package edu.scranton.lunchilicious;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
public class MainActivity extends AppCompatActivity {
    MyViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(MyViewModel.class);
        if (savedInstanceState == null) {
            viewModel.menu = MenuFragment.newInstance().getMenuItems();
        }
        if (findViewById(R.id.container) != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, MenuFragment.newInstance()).commit();
        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.Horizontal, MenuFragment.newInstance()).commit();
        }
    }
    public void openDialog(String Type, String Name, String Description, String UnitPrice) {
        itemDialog dialog = new itemDialog();
        Bundle args = new Bundle();
        args.putString("TYPE", Type);
        args.putString("NAME", Name);
        args.putString("DESCRIPTION", Description);
        args.putString("UNITPRICE", UnitPrice);
        dialog.setArguments(args);
        dialog.show(getSupportFragmentManager(), "ITEM");
    }
    public void NewItemDialog() {
        NewItemDialog newItem = new NewItemDialog();
        newItem.show(getSupportFragmentManager(), "NEWITEMDIALOG");
    }
    public void addMenuitem(MenuItem menuItem) {
        viewModel.addMenuitem(menuItem);
    }
}