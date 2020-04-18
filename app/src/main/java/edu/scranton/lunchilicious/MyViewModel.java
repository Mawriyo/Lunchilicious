package edu.scranton.lunchilicious;
import java.util.List;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
public class MyViewModel extends ViewModel {
    public List<MenuItem> menu;
    private MutableLiveData<List<MenuItem>> menuLD;
    int NumberOfItems=1;
    int NumberOfItemsInList;
    public LiveData<List<MenuItem>> getMenuItemsLiveData() {
        if(menuLD ==null){
            menuLD = new MutableLiveData<>();
        }
        return menuLD;
    }
    public void addMenuitem(MenuItem menuItem) {
       menu.add(menuItem);
    }
}