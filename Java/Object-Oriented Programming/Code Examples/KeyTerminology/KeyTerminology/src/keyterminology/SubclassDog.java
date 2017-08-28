/*
 * Karin Whiting
 * COP 3330 Object Oriented Programming
 * University of Central Florida
 */
package keyterminology;

/**
 *
 * @author kwhiting
 */
public class SubclassDog extends Dog{
/* inherited from superclass Dog
    private int age;
    private String breed;
    private String name;
    private String activity;
    private boolean hungry;
*/
    public SubclassDog()
    {
        // this is calling your superclass (i.e. parent class) constructor
    }
    // extras unique to SubclassDog
    private boolean swimmer;
    private boolean barks;
    private boolean searchAndRescue;
}
