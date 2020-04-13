package edu.scranton.lunchilicious;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MenuFragment extends Fragment {


    private RecyclerView mRecyclerView;
    public Adapter mAdapter;
    MyViewModel viewModel;
    String currentType;
    String currentName;
    String currentUnitPrice;
    String currentDescription;
    public static MenuFragment newInstance() {
        return new MenuFragment();
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menu_fragment,container,false);
        mRecyclerView = view.findViewById(R.id.RecyclerView);
        LinearLayoutManager layoutManager =new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(layoutManager);
        viewModel = new ViewModelProvider(this.requireActivity()).get(MyViewModel.class);
        mAdapter = new Adapter(this.getActivity(),viewModel.getMenuItems());
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.OnItemClick(new Adapter.OnItemClick() {
            @Override
            public void OnItemClick(int position) {
                MenuItem currentItem = viewModel.getMenuItems().get(position);
                currentType=viewModel.menu.get(position).getFoodType();
                currentName=viewModel.menu.get(position).getFoodName();
                currentDescription=viewModel.menu.get(position).getDescription();
                currentUnitPrice=viewModel.menu.get(position).getPrice();
                ((MainActivity)getActivity()).openDialog(currentType,currentName, currentDescription, currentUnitPrice);
            }
        });
        return view;
    }


}

