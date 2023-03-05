package cat.tecnocampus.product.domain;

public class Product {
    private long id;
    private String name;
    private String description;
    private long weight;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getWeight(){return weight;}

    public void setWeight(long weight){this.weight = weight;}
}
