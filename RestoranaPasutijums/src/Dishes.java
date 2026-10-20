public class Dishes {
    private String nameDish;
    private Double price;
    private String description;
    private String category;

    public Dishes(String nameDish, Double price, String description, String category) {
        this.nameDish = nameDish;
        this.price = price;
        this.description = description;
        this.category = category;
    }

    public String getName() {
        return nameDish;
    }

    public Double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }


    public void setName(String nameDish) {
        this.nameDish = nameDish;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    }

