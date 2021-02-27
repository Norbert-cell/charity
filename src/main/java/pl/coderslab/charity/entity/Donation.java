package pl.coderslab.charity.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Entity
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Min(value = 1, message = "javax.validation.constraints.Min.message.DonationQuantity")
    private int quantity;
    @ManyToMany
    @Valid
    private List<Category> categories;
    @ManyToOne
    @Valid
    private Institution institution;
    @ManyToOne
    private User user;
    @NotBlank
    private String street;
    @NotBlank
    private String city;
    @Pattern(regexp = "^[0-9]{2}-[0-9]{3}$", message = "javax.validation.constraints.Pattern.message.zipCode")
    @NotBlank
    private String zipCode;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future(message = "javax.validation.constraints.Future.message.pickUpDate")
    private LocalDate pickUpDate;
    private LocalTime pickUpTime;
    private String pickUpComment;
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(cascade = CascadeType.MERGE)
    private Set<Archives> archives;

    private LocalDateTime createdDateTime;

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public Set<Archives> getArchives() {
        return archives;
    }

    public void setArchives(Set<Archives> archives) {
        this.archives = archives;
    }

    public void setCreatedDateTime(LocalDateTime createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Institution getInstitution() {
        return institution;
    }

    public void setInstitution(Institution institution) {
        this.institution = institution;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public LocalDate getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(LocalDate pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public LocalTime getPickUpTime() {
        return pickUpTime;
    }

    public void setPickUpTime(LocalTime pickUpTime) {
        this.pickUpTime = pickUpTime;
    }

    public String getPickUpComment() {
        return pickUpComment;
    }

    public void setPickUpComment(String pickUpComment) {
        this.pickUpComment = pickUpComment;
    }

    @Override
    public String toString() {
        return "Donation{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", categories=" + categories +
                ", institution=" + institution +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", pickUpDate=" + pickUpDate +
                ", pickUpTime=" + pickUpTime +
                ", pickUpComment='" + pickUpComment + '\'' +
                '}';
    }
}
