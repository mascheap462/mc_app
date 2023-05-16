package database.models.entities;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public interface IBaseEntity {
    String getId();

    void setId(String id);

    List<BaseEntity> QueryDocument(Task<QuerySnapshot> task);

    BaseEntity Document(DocumentSnapshot document);
}
