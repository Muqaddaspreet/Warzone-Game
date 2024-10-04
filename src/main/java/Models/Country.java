package Models;

import java.util.ArrayList;
import java.util.List;

/**
 * The Country class represents a country in a geographical or game context.
 * It contains information such as the country ID, name, continent ID, army count, and a list of adjacent country IDs.
 *
 * @author Hritik Nandanwar
 * @author Sweta Sarat
 * @author Vishav Teji
 * @author Muqaddaspreet Bhatia
 * @author Ushnish Chakravarty
 * @version 3.0.0
 */
public class Country {
    
    /**
     * The count of armies present in the country.
     */
    Integer d_armyCount;
    
    /**
     * The unique identifier for the country.
     */
    Integer d_countryId;
    
    /**
     * The continent to which the country belongs.
     */
    Integer d_continentId;
    
    /**
     * The name of the country.
     */
    String d_countryName;
    
    /**
     * The list of unique identifiers for countries adjacent to this country.
     */
    List<Integer> d_adjacentCountryIds = new ArrayList<Integer>();
    
    /**
     * Constructs a {@code Country} instance with the specified ID, name, and continent ID.
     *
     * @param p_countryId    The unique identifier for the country.
     * @param p_countryName  The name of the country.
     * @param p_continentId  The continent ID to which the country belongs.
     */
    public Country(int p_countryId, String p_countryName, int p_continentId) {
        d_countryId = p_countryId;
        d_countryName = p_countryName;
        d_continentId = p_continentId;
    }
    
    /**
     * Constructs a {@code Country} instance with the specified ID and continent ID.
     *
     * @param p_countryId   The unique identifier for the country.
     * @param p_continentId The continent ID to which the country belongs.
     */
    public Country(int p_countryId, int p_continentId) {
        d_countryId = p_countryId;
        d_continentId = p_continentId;
    }
    
    /**
     * Constructs a {@code Country} instance with the specified name.
     *
     * @param p_countryName The name of the country.
     */
    public Country(String p_countryName) {
        d_countryName = p_countryName;
    }
    
    /**
     * Retrieves the count of armies present in the country.
     *
     * @return The army count.
     */
    public Integer getD_armyCount() {
        if (d_armyCount == null) {
            return 0;
        }
        return d_armyCount;
    }
    
    /**
     * Retrieves the unique identifier for the country.
     *
     * @return The country ID.
     */
    public Integer getD_countryId() {
        return d_countryId;
    }
    
    /**
     * Sets the unique identifier for the country.
     *
     * @param p_countryId The country ID to be set.
     */
    public void setD_countryId(Integer p_countryId) {
        this.d_countryId = p_countryId;
    }
    
    /**
     * Retrieves the continent ID to which the country belongs.
     *
     * @return The continent ID.
     */
    public Integer getD_continentId() {
        return d_continentId;
    }
    
    /**
     * Sets the continent ID to which the country belongs.
     *
     * @param p_continentId The continent ID to be set.
     */
    public void setD_continentId(Integer p_continentId) {
        this.d_continentId = p_continentId;
    }
    
    /**
     * Retrieves the list of unique identifiers for countries adjacent to this country.
     *
     * @return The list of adjacent country IDs.
     */
    public List<Integer> getD_adjacentCountryIds() {
        if (d_adjacentCountryIds == null) {
            d_adjacentCountryIds = new ArrayList<Integer>();
        }
        
        return d_adjacentCountryIds;
    }
    
    /**
     * Sets the list of unique identifiers for countries adjacent to this country.
     *
     * @param p_adjacentCountryIds The list of adjacent country IDs to be set.
     */
    public void setD_adjacentCountryIds(List<Integer> p_adjacentCountryIds) {
        this.d_adjacentCountryIds = p_adjacentCountryIds;
    }
    
    /**
     * Retrieves the name of the country.
     *
     * @return The country name.
     */
    public String getD_countryName() {
        return d_countryName;
    }

    /**
     * establish a setter method to set the name of the country.
     *
     * @param p_countryName name of the country
     */
    public void setD_countryName(String p_countryName) {
        this.d_countryName = p_countryName;
    }


    /**
     * Set the army count.
     *
     * @param p_armyCount armies
     */
    public void setD_armyCount(Integer p_armyCount) {
        this.d_armyCount = p_armyCount;
    }

    /**
     * Append the country ID to the list of neighbors.
     *
     * @param p_countryNeighbourId Id of country to be added
     */
    public void addNeighbour(Integer p_countryNeighbourId) {
        if (!d_adjacentCountryIds.contains(p_countryNeighbourId))
            d_adjacentCountryIds.add(p_countryNeighbourId);
    }
    /**
     * Delete the country ID from the neighbor list.
     *
     * @param p_countryNeighbourId Id of country to be removed
     */
    public void removeNeighbour(Integer p_countryNeighbourId) {
        if (d_adjacentCountryIds.contains(p_countryNeighbourId)) {
            d_adjacentCountryIds.remove(d_adjacentCountryIds.indexOf(p_countryNeighbourId));
        } else {
            System.out.println("No Such Neighbour Exists");
        }
    }
}
