package database.models.callbacks;

import java.util.List;

public interface FirestoreCallbackList<T> {
    void onCallback(List<T> users);
}

