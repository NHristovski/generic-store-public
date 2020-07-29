package hristovski.nikola.product.domain.model.product;

import hristovski.nikola.common.shared.domain.model.product.Product;
import hristovski.nikola.common.shared.domain.model.product.ProductId;
import hristovski.nikola.common.shared.domain.model.product.value.ImageURL;
import hristovski.nikola.common.shared.domain.model.product.value.ProductInformation;
import hristovski.nikola.common.shared.domain.model.product.value.RatingStatistics;
import hristovski.nikola.common.shared.domain.model.all.value.Money;
import hristovski.nikola.common.shared.domain.validator.RatingStatisticsValidator;
import hristovski.nikola.common.shared.domain.validator.Validators;
import hristovski.nikola.generic_store.base.domain.AbstractEntity;
import hristovski.nikola.generic_store.base.domain.DomainObjectId;
import hristovski.nikola.product.domain.event.ProductRatingStatisticsChangedEvent;
import hristovski.nikola.product.domain.event.provider.DomainEventPublisherProvider;
import hristovski.nikola.product.domain.model.category.CategoryEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@Getter
@Entity
@Table(name = "product")
@NoArgsConstructor
public class ProductEntity extends AbstractEntity<ProductId> {

    public static final int MIN_RATING = 1;
    public static final int MAX_RATING = 10;

    @Version
    private Long version;

    @Embedded
    private ImageURL imageLocation;

    @Embedded
    private ProductInformation information;

    private Instant createdOn;

    @Embedded
    private Money price;

    @Column
    private Boolean deleted;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "totalRatings", column = @Column(name = "total_ratings", nullable = false)),
            @AttributeOverride(name = "averageRating", column = @Column(name = "average_rating", nullable = false))
    }
    )
    private RatingStatistics ratingStatistics;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "product_categories",
            joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"))
    private Set<CategoryEntity> categories;

    public ProductEntity(ProductEntity entity) {
        super(entity.getId());
        this.version = entity.getVersion();
        this.imageLocation = entity.getImageLocation();
        this.deleted = entity.getDeleted();
        this.createdOn = entity.getCreatedOn();
        this.categories = entity.getCategories();
        this.information = entity.getInformation();
        this.price = entity.getPrice();
        this.ratingStatistics = entity.getRatingStatistics();
    }


    public ProductEntity(ImageURL imageLocation,
                         ProductInformation information,
                         Money price) {
        super(DomainObjectId.randomId(ProductId.class));
        this.version = 0L;
        this.imageLocation = imageLocation;
        this.information = information;
        this.price = price;
        this.deleted = FALSE;
        this.createdOn = Instant.now();
        this.ratingStatistics = new RatingStatistics();
        this.categories = new HashSet<>();
    }

    public ProductEntity(Product product) {
        super(product.getProductId());
        this.version = product.getVersion();
        this.imageLocation = product.getImageLocation();
        this.information = product.getInformation();
        this.price = product.getPrice();
        this.deleted = FALSE;
        this.createdOn = product.getCreatedOn();
        this.ratingStatistics = product.getRatingStatistics();
        this.categories = new HashSet<>();
    }

    public void delete() {
        this.deleted = TRUE;
    }

    public Product toProduct() {
        return new Product(
                this.getId(),
                this.getVersion(),
                this.getImageLocation(),
                this.getInformation(),
                this.getCreatedOn(),
                this.getPrice(),
                this.getRatingStatistics(),
                this.getCategories().stream()
                        .map(CategoryEntity::toCategory)
                        .collect(Collectors.toSet())
        );
    }

    public void adjustRatingStatistics(Integer rating) throws IllegalArgumentException {
        Objects.requireNonNull(rating, "Rating must not be null!");
        Validators.requireInRange(rating, MIN_RATING, MAX_RATING);

        RatingStatistics newRatingStatistics =
                this.getRatingStatistics().addRating(rating);

        this.setRatingStatistics(newRatingStatistics);

        DomainEventPublisherProvider.getInstance()
                .publish(
                        new ProductRatingStatisticsChangedEvent(this.getId(), newRatingStatistics)
                );
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public void setImageLocation(ImageURL imageLocation) {
        this.imageLocation = imageLocation;
    }

    public void setInformation(ProductInformation information) {
        this.information = information;
    }

    public void setCreatedOn(Instant createdOn) {
        this.createdOn = createdOn;
    }

    public void setPrice(Money price) {
        this.price = price;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public void setRatingStatistics(RatingStatistics ratingStatistics) throws IllegalArgumentException {

        RatingStatisticsValidator.requireValid(ratingStatistics);

        this.ratingStatistics = ratingStatistics;
    }

    public void setCategories(Set<CategoryEntity> categories) {
        this.categories = categories;
    }
}
