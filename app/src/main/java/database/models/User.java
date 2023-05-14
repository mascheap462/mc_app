package database.models;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User extends BaseEntity {

    public String username;
    public String email;

    @Override
    protected String table() {
        return "user";
    }

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, String email) {
        super();
        this.username = username;
        this.email = email;
    }


}