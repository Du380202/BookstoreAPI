package com.bookstore.api.bookstore.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Book")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bookid")
    private Integer bookId;

    @Column(name = "bookname", nullable = false)
    private String bookName;

    @Column(name = "price")
    private BigDecimal price;
    
    @Column(name = "image")
    private String image;
    @Column(name = "description")
    private String description;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "publicationyear", nullable = false)
    private Integer publicationYear;

    @Column(name = "status")
    private Integer status;


    @Column(name = "rating")
    private Float rating;
    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
	private List<OrderDetail> orderDetails = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "publisherid", nullable = false)
    private Publisher publisher;
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "categorydetail",
			joinColumns = @JoinColumn(name = "bookid", nullable = false),
			inverseJoinColumns = @JoinColumn(name = "categoryid", nullable = false))
	private List<Category> categories = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "authordetail",
			joinColumns = @JoinColumn(name = "bookid", nullable = false),
			inverseJoinColumns = @JoinColumn(name = "authorid", nullable = false))
    private List<Author> authors = new ArrayList<>();
    
    @ManyToMany(mappedBy = "books")
    private List<Discount> discounts = new ArrayList<>();
    
    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    private List<Rate> rates = new ArrayList<>();
    
    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    private List<Cart> cart = new ArrayList<>();
    
//	public List<Cart> getCart() {
//		return cart;
//	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setCart(List<Cart> cart) {
		this.cart = cart;
	}

//	public List<Discount> getDiscounts() {
//		return discounts;
//	}

//	public List<Rate> getRates() {
//		return rates;
//	}

	public void setRates(List<Rate> rates) {
		this.rates = rates;
	}

	public void setDiscounts(List<Discount> discounts) {
		this.discounts = discounts;
	}

//	public List<OrderDetail> getOrderDetails() {
//		return orderDetails;
//	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

//	public List<Author> getAuthors() {
//		return authors;
//	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

//	public List<Category> getCategories() {
//		return categories;
//	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getPublicationYear() {
		return publicationYear;
	}

	public void setPublicationYear(Integer publicationYear) {
		this.publicationYear = publicationYear;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}



	public Float getRating() {
		return rating;
	}

	public void setRating(Float rating) {
		this.rating = rating;
	}

    
}
