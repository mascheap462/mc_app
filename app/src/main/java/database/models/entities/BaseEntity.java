package database.models.entities;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.List;
import java.util.UUID;

public abstract class BaseEntity implements IBaseEntity {
    private String id;

    @Override
    public abstract List<BaseEntity> QueryDocument(Task<QuerySnapshot> task);

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }
}
