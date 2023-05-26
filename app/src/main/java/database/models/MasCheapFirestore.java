package database.models;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

import database.models.callbacks.FirestoreCallback;
import database.models.callbacks.FirestoreCallbackList;
import database.models.entities.BaseEntity;
import database.models.entities.IBaseEntity;


public class MasCheapFirestore {

    protected static FirebaseFirestore mDatabase;
    private static MasCheapFirestore db;

    private MasCheapFirestore() {
    }

    public static MasCheapFirestore getInstance() {
        if (db == null) {
            mDatabase = FirebaseFirestore.getInstance();
            db = new MasCheapFirestore();
        }
        return db;
    }

    public void Add(IBaseEntity entity) {
        entity.setId(UUID.randomUUID().toString());
        mDatabase.collection(getName(entity))
                .document(entity.getId())
                .set(entity);
    }

    public void Add(IBaseEntity entity, String id) {
        entity.setId(id);
        mDatabase.collection(getName(entity))
                .document(entity.getId())
                .set(entity);
    }
    public void Update(IBaseEntity entity) {
        mDatabase.collection(getName(entity))
                .document(entity.getId())
                .set(entity);
    }

    public void Delete(IBaseEntity entity) {
        mDatabase.collection(getName(entity)).document(entity.getId())
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully deleted!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error deleting document", e);
                    }
                });
    }

    public void GetAll(FirestoreCallbackList myCallback, IBaseEntity entity ) {
        mDatabase.collection(getName(entity))
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<BaseEntity> entities = entity.QueryDocument(task);
                        myCallback.onCallback(entities);
                    }
                });
    }

    public void GetById(FirestoreCallback myCallback, IBaseEntity entity, String id) {
        mDatabase.collection(getName(entity))
                .document(id)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                       // if (document.exists()) {
                            myCallback.onCallback(entity.Document(document));
                       // }
                    } else {
                        Log.d(TAG, "get failed with ", task.getException());
                    }
                });
    }



    @NonNull
    public String getName(IBaseEntity entity) {
        return entity.getClass().getSimpleName().toLowerCase(Locale.ROOT);
    }
}
