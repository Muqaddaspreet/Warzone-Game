package Models;

import CommonFunctions.Common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * The Continent represents a continent in a geographical or game context.
 * It contains information such as the continent ID, name, value, and a list of countries belonging to the continent.
 *
 * @author Hritik Nandanwar
 * @author Sweta Sarat
 * @author Vishav Teji
 * @author Muqaddaspreet Bhatia
 * @author Ushnish Chakravarty
 * @version 3.0.0
 */
public class Continent implements Serializable{
    
    /**
     * The unique identifier for the continent.
     */
    Integer d_continentID;
    
    /**
     * The name of the continent.
     */
    String d_continentName;
    
    /**
     * The value or bonus associated with holding the entire continent.
     */
    Integer d_continentValue;
    
    /**
     * The list of countries belonging to the continent.
     */
    List<Country> d_countries;

    /**
     * default contructor to the class.
     */
    public Continent() {

    }

    /**
     * Constructs a {@code Continent} instance with the specified ID, name, and value.
     *
     * @param p_continentID    The unique identifier for the continent.
     * @param p_continentName  The name of the continent.
     * @param p_continentValue The value or bonus associated with holding the entire continent.
     */
    public Continent(Integer p_continentID, String p_continentName, int p_continentValue) {
        this.d_continentID = p_continentID;
        this.d_continentName = p_continentName;
        this.d_continentValue = p_continentValue;
    }

    /**
     * Constructs a {@code Continent} instance with the specified name.
     *
     * @param p_continentName The name of the continent.
     */
    public Continent(String p_continentName) {
        this.d_continentName = p_continentName;
    }
    
    /**
     * Retrieves the continent ID.
     *
     * @return The continent ID.
     */
    public Integer getD_continentID() {
        return d_continentID;
    }
    
    /**
     * Sets the continent ID.
     *
     * @param p_continentID The continent ID to be set.
     */
    public void setD_continentID(Integer p_continentID) {
        this.d_continentID = p_continentID;
    }
    
    /**
     * Retrieves the continent name.
     *
     * @return The continent name.
     */
    public String getD_continentName() {
        return d_continentName;
    }
    
    /**
     * Sets the continent name.
     *
     * @param p_continentName The continent name to be set.
     */
    public void setD_continentName(String p_continentName) {
        this.d_continentName = p_continentName;
    }
    
    /**
     * Retrieves the continent value or bonus.
     *
     * @return The continent value or bonus.
     */
    public Integer getD_continentValue() {
        return d_continentValue;
    }
    
    /**
     * Sets the continent value or bonus.
     *
     * @param p_continentValue The continent value or bonus to be set.
     */
    public void setD_continentValue(Integer p_continentValue) {
        this.d_continentValue = p_continentValue;
    }
    
    /**
     * Retrieves the list of countries belonging to the continent.
     *
     * @return The list of countries.
     */
    public List<Country> getD_countries() {
        return d_countries;
    }
    
    /**
     * Sets the list of countries belonging to the continent.
     *
     * @param p_countries The list of countries to be set.
     */
    public void setD_countries(List<Country> p_countries) {
        this.d_countries = p_countries;
    }
    
    /**
     * Adds a country to the list of countries belonging to the continent.
     *
     * @param p_country The country to be added.
     */
    public void addCountry(Country p_country) {
        if (d_countries != null) {
            d_countries.add(p_country);
        } else {
            d_countries = new ArrayList<Country>();
            d_countries.add(p_country);
        }
    }




    /** removes target Country from Continent.
     *
     * @param p_targetCountryId country to be removed
     */
    public void removeCountry(Country p_targetCountryId) {
        if (d_countries == null) {
            System.out.println("No such Country Exists");
        } else {
            d_countries.remove(p_targetCountryId);
        }
    }

    /**
     * Removes particular country ID from the neighbor list of all countries in
     * continent.
     * Removes particular country ID from the neighbor list of all countries in
     * continent.
     *
     * @param p_targetCountryId ID of country to be removed
     */
    public void removeCountryNeighboursFromAll(Integer p_targetCountryId) {
        if (null != d_countries && !d_countries.isEmpty()) {
            for (Country c : d_countries) {
                if (!Common.isNull(c.d_adjacentCountryIds)) {
                    if (c.getD_adjacentCountryIds().contains(p_targetCountryId)) {
                        c.removeNeighbour(p_targetCountryId);
                    }
                }
            }
        }
    }


}
