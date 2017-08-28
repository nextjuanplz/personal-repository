/*Juan Manuel Alzate Vanegas
 * COP 3330
 * DatabaseJavaFx
 * 8/1/2017
 */
package dataModel;

/**
 *
 * @author Juan Manuel Alzate Vanegas
 */

public class FilmDAO {
    //Constructors
    //Default
    public FilmDAO (){};
    //Custom
    public FilmDAO(String title, String rating, String description, Double rentalPrice) {
        //Set fields
        this.setTitle(title);
        this.setDescription(description);                    
        this.setRentalPrice(rentalPrice);
        this.setRating(rating);
    };
    
    //Fields
    private String title;
    private String rating;
    private String description;
    private Double rentalPrice;
    
    //Getters and Setters
    public final String getTitle() {
        return title;
    }
    public final void setTitle(String title) {
        this.title = title;
    }
    public final String getRating() {
        return rating;
    }
    public final void setRating(String rating) {
        this.rating = rating;
    }
    public final String getDescription() {
        return description;
    }
    public final void setDescription(String description) {
        this.description = description;
    }
    public final double getRentalPrice() {
        return rentalPrice;
    }
    public final void setRentalPrice(double rentalPrice) {
        this.rentalPrice = rentalPrice;
    }
}
