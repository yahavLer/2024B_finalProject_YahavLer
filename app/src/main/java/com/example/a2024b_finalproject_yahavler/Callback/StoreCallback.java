package com.example.a2024b_finalproject_yahavler.Callback;

import com.example.a2024b_finalproject_yahavler.Model.Store;
import java.util.List;

public interface StoreCallback {
    void favoriteButtonClicked(Store store, int position);
    void onStoreClick(Store store);
    void onFavoriteStoresFetched(List<String> favoriteStores); // הוספת המתודה החדשה
}
