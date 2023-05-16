package database.models.callbacks;

public interface FirestoreCallback<T> {
    void onCallback(T user);
}
