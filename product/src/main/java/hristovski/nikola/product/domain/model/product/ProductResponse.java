//package hristovski.nikola.product.domain.model.product;
//
//import hristovski.nikola.product.domain.model.category.Category;
//import lombok.Data;
//
//import java.util.List;
//
//@Data
//public class ProductResponse {
//
//    public ProductResponse(Product product,Integer currentUserRating,Double averageRating, Integer totalRatings){
//        this.currentUserRating = currentUserRating;
//
//        this.id = product.getId();
//        this.title = product.getTitle();
//        this.imageLocation = product.getImageLocation();
//        this.description = product.getDescription();
//        this.price = product.getPrice();
//
//        this.averageRating = averageRating;
//        this.totalRatings = totalRatings;
//        this.stock = product.getStock();
//        this.categories = product.getCategories();
//    }
//
//    private Long id;
//    private String title;
//    private String imageLocation;
//    private String description;
//    private double price;
//    private double averageRating;
//    private int totalRatings;
//    private int stock;
//    public Integer currentUserRating;
//
//    List<Category> categories;
//}
