package database.models.entities;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

@IgnoreExtraProperties
public class User extends BaseEntity {

    public String username;
    public String email;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    @Override
    public List<BaseEntity> QueryDocument(@NonNull Task<QuerySnapshot> task) {
        List<BaseEntity> users = new ArrayList<>();
        for (QueryDocumentSnapshot document : task.getResult()) {
            User user = document.toObject(User.class);
            user.setId(document.getId());
            users.add(user);
        }
        return users;
    }

    @Override
    public BaseEntity Document(DocumentSnapshot document) {
        return document.toObject(User.class);
    }

}