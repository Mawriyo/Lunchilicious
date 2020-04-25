package edu.scranton.lunchilicious;


import android.provider.ContactsContract;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface MenuItemDao {

    @Insert
    void insertMenuList(List<MenuItem> menuitem);

    @Insert
    void insertMenuItem(MenuItem... menuitem);

    @Delete
    void delete(MenuItem... menuitem);

    @Query("DELETE FROM item")
    void deleteAllItems();

    @Query("SELECT * FROM item ORDER BY type AND name")
    LiveData<List<MenuItem>> getAllMenuItems();

    @Query("Select * FROM item Where id = :id")
    MenuItem getMenuItemById(int id);

    @Query("SELECT MAX(id) FROM item")
    public int findMaxMenuItemID();
}
