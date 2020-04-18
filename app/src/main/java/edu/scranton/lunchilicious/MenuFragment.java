package edu.scranton.lunchilicious;


import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MenuFragment extends Fragment {
    private RecyclerView mRecyclerView;
    public Adapter mAdapter;
    MyViewModel viewModel;
    String currentType;
    String currentName;
    String currentUnitPrice;
    String currentDescription;
    FloatingActionButton newItem;
    public static MenuFragment newInstance() {
        return new MenuFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menu_fragment,container,false);
        mRecyclerView = view.findViewById(R.id.RecyclerView);
        newItem=view.findViewById(R.id.NewItemButton);
        LinearLayoutManager layoutManager =new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(layoutManager);
        viewModel = new ViewModelProvider(this.requireActivity()).get(MyViewModel.class);
        mAdapter = new Adapter(this.getActivity(),viewModel.menu);
        mRecyclerView.setAdapter(mAdapter);
        viewModel.getMenuItemsLiveData().observe(this.getActivity(), new Observer<List<MenuItem>>() {
            @Override
            public void onChanged(@Nullable List<MenuItem> menuItems) {
                mAdapter.setMenuItems(menuItems);
            }
        });
        mAdapter.OnItemClick(new Adapter.OnItemClick() {
            @Override
            public void OnItemClick(int position) {
                MenuItem currentItem = viewModel.menu.get(position);
                currentType=viewModel.menu.get(position).getFoodType();
                currentName=viewModel.menu.get(position).getFoodName();
                currentDescription=viewModel.menu.get(position).getDescription();
                currentUnitPrice=viewModel.menu.get(position).getPrice();
                ((MainActivity)getActivity()).openDialog(currentType,currentName, currentDescription, currentUnitPrice);
            }
        });
        newItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).NewItemDialog();
            }
        });
        return view;
    }
    public List<MenuItem> getMenuItems() {
        List<MenuItem> items = new ArrayList<>();
        items.add(new MenuItem(1, "Hoagie", "BLT Hoagie", "Cold, Onion, lettuce, tomato", (float) 6.95));
        items.add(new MenuItem(2, "Hoagie", "Cheese Hoagie", "Cheese, mayos, lettuce, tomato", (float) 6.95));
        items.add(new MenuItem(3, "Hoagie", "Combo Hoagies", "Cold, Onion, lettuce, tomato", (float) 6.95));
        items.add(new MenuItem(4, "Hoagie", "Ham & Cheese", "Cold, union, lettuce, tomato", (float) 6.95));
        items.add(new MenuItem(5, "Hoagie", "Italian Hoagie", "Cheese, ham, hot pepper lettuce, tomato", (float) 6.95));
        items.add(new MenuItem(6, "Pizza", "Plain", "cheese and tomato", (float) 9.50));
        items.add(new MenuItem(7, "Pizza", "Tomato Pizza", "Cheese and a lot of tomato", (float) 6.95));
        items.add(new MenuItem(8, "Pizza", "House Special Pizza", "mushroom, green pepper, tomato", (float) 7.95));
        items.add(new MenuItem(9, "Pizza", "Round White Pizza", "American cheese, lettuce, tomato", (float) 9.95));
        items.add(new MenuItem(10, "Pizza", "Hot Wing Pizza", "chicken, hot sauce, lettuce, tomato", (float) 4.95));
        items.add(new MenuItem(11, "Side", "Fries", "large hot fries", (float) 2.95));
        items.add(new MenuItem(12, "Side", "Gravy Fries",  "Fries with gravy on top", (float) 3.95));
        items.add(new MenuItem(13, "Side", "Cheese Fries", "Fries with melt cheese", (float) 4.95));
        items.add(new MenuItem(14, "Side", "Onion Rings", "Deep fried onion rings", (float) 3.95));
        items.add(new MenuItem(15, "Side", "Cheese Sticks", "Mozzarella cheese sticks", (float) 5.95));
        return items;
    }
    }