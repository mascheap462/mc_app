package database.models;

public class Product extends BaseEntity{
    public String Name;
    public Product(String name){
        Name = name;
    }
    @Override
    protected String table() {
        return "Products";
    }
}
