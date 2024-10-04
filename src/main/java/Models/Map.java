package Models;

import CommonFunctions.Common;
import Exceptions.InvalidMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

/**
 * Represents a map in a map-based application, including continents, countries, and their connectivity.
 *
 * @author Hritik Nandanwar
 * @author Sweta Sarat
 * @author Vishav Teji
 * @author Muqaddaspreet Bhatia
 * @author Ushnish Chakravarty
 * @version 3.0.0
 */
public class Map {
    
    /**
     * File path of the map.
     */
    String d_mapFile;
    
    /**
     * List of continents in the map.
     */
    List<Continent> d_continents;
    
    /**
     * List of countries in the map.
     */
    List<Country> d_countries;
    
    /**
     * HashMap to track the reachability of countries in the map.
     */
    HashMap<Integer, Boolean> d_countryReach = new HashMap<>();
    
    /**
     * Gets the file path of the map.
     *
     * @return the file path of the map.
     */
    public String getD_mapFile()
    {
        return d_mapFile;
    }
    
    /**
     * Sets the file path of the map.
     *
     * @param p_mapFile the file path to set.
     */
    public void setD_mapFile(String p_mapFile)
    {
        d_mapFile = p_mapFile;
    }
    
    /**
     * Gets the list of continents in the map.
     *
     * @return the list of continents.
     */
    public List<Continent> getD_continents() {
        return d_continents;
    }
    
    /**
     * Sets the list of continents in the map.
     *
     * @param p_continents the list of continents to set.
     */
    public void setD_continents(List<Continent> p_continents)
    {
        d_continents = p_continents;
    }
    
    /**
     * Gets the list of countries in the map.
     *
     * @return the list of countries.
     */
    public List<Country> getD_countries()
    {
        return d_countries;
    }
    
    /**
     * Sets the list of countries in the map.
     *
     * @param p_countries the list of countries to set.
     */
    public void setD_countries(List<Country> p_countries)
    {
        this.d_countries = p_countries;
    }

    /**
     * adds the continent to the map.
     *
     * @param p_continent continent to add
     */
    public void addContinent(Continent p_continent) {
        d_continents.add(p_continent);
    }

    /**
     * adds the country to the map.
     *
     * @param p_country country to add
     */
    public void addCountry(Country p_country) {
        d_countries.add(p_country);
    }


    /**
     * Gets the list of continent IDs in the map.
     *
     * @return the list of continent IDs.
     */
    public List<Integer> getContinentIDs() {
        List<Integer> l_continentIDs = new ArrayList<Integer>();
        if (!d_continents.isEmpty()) {
            for (Continent c : d_continents) {
                l_continentIDs.add(c.getD_continentID());
            }
        }
        return l_continentIDs;
    }
    
    /**
     * Gets the list of country IDs in the map.
     *
     * @return the list of country IDs.
     */
    public List<Integer> getCountryIDs() {
        List<Integer> l_countryIDs = new ArrayList<Integer>();
        if (!d_countries.isEmpty()) {
            for (Country c : d_countries) {
                l_countryIDs.add(c.getD_countryId());
            }
        }
        return l_countryIDs;
    }


    /**
     * check the existing continents.
     */
    public void checkContinents() {
        for (Continent c : d_continents) {
            System.out.println(c.getD_continentID());
        }
    }

    /**
     * check the existing countries.
     */
    public void checkCountries() {
        for (Country c : d_countries) {
            System.out.println("Country Id " + c.getD_countryId());
            System.out.println("Continent Id " + c.getD_continentId());
            System.out.println("Neighbours:");
            for (int i : c.getD_adjacentCountryIds()) {
                System.out.println(i);
            }
        }
    }

    


    /**
     * Executes the "Remove Country" operation on the map.
     *
     * @param p_countryName Name of country to be Added
     * @throws InvalidMap Exception
     */
    public void removeCountry(String p_countryName) throws InvalidMap {
        if (d_countries != null && !Common.isNull(getCountryByName(p_countryName))) {
            for (Continent c : d_continents) {
                if (c.getD_continentID().equals(getCountryByName(p_countryName).getD_continentId())) {
                    c.removeCountry(getCountryByName(p_countryName));
                }
                c.removeCountryNeighboursFromAll(getCountryByName(p_countryName).getD_countryId());
            }
            removeCountryNeighboursFromAll(getCountryByName(p_countryName).getD_countryId());
            d_countries.remove(getCountryByName(p_countryName));
            System.out.println("Country has been removed successfully");
        } else {
            throw new InvalidMap("Country:  " + p_countryName + " does not exist!");
        }
    }

    /**
     * Validates the map, checking for various conditions including null objects, continent connectivity,
     * and country connectivity.
     *
     * @return true if the map is valid, false otherwise.
     * @throws InvalidMap if the map is invalid, providing details about the specific issue.
     */
    public Boolean Validate() throws InvalidMap {
        return (!checkForNullObjects() && checkContinentConnectivity() && checkCountryConnectivity());
    }
    
    /**
     * Checks for null objects within the map, including continents, countries, and their neighbors.
     *
     * @return true if there are no null objects, false otherwise.
     * @throws InvalidMap if null objects are found, providing details about the specific issue.
     */
    public Boolean checkForNullObjects() throws InvalidMap {
        if (d_continents == null || d_continents.isEmpty()) {
            throw new InvalidMap("Map must possess atleast one continent!");
        }
        if (d_countries == null || d_countries.isEmpty()) {
            throw new InvalidMap("Map must possess atleast one country!");
        }
        for (Country c : d_countries) {
            if (c.getD_adjacentCountryIds().size() < 1) {
                throw new InvalidMap(c.getD_countryName() + " does not possess any neighbour, hence isn't reachable!");
            }
        }
        return false;
    }
    
    /**
     * Checks connectivity within continents, ensuring each continent forms a connected subgraph.
     *
     * @return true if continent connectivity is valid, false otherwise.
     * @throws InvalidMap if there are issues with continent connectivity.
     */
    public Boolean checkContinentConnectivity() throws InvalidMap {
        boolean l_flagConnectivity = true;
        for (Continent c : d_continents) {
            if (null == c.getD_countries() || c.getD_countries().size() < 1) {
                throw new InvalidMap(c.getD_continentName() + " has no countries, it must possess atleast 1 country");
            }
            if (!subGraphConnectivity(c)) {
                l_flagConnectivity = false;
            }
        }
        return l_flagConnectivity;
    }
    
    /**
     * Performs depth-first search (DFS) on a subgraph within a continent to check connectivity.
     *
     * @param p_continent the continent to check for connectivity.
     * @return true if the subgraph is connected, false otherwise.
     * @throws InvalidMap if there are issues with the subgraph connectivity.
     */
    public boolean subGraphConnectivity(Continent p_continent) throws InvalidMap {
        HashMap<Integer, Boolean> l_continentCountry = new HashMap<Integer, Boolean>();
        
        for (Country c : p_continent.getD_countries()) {
            l_continentCountry.put(c.getD_countryId(), false);
        }
        dfsSubgraph(p_continent.getD_countries().get(0), l_continentCountry, p_continent);
        
        // Iterates Over Entries to locate unreachable countries in continent
        for (Entry<Integer, Boolean> entry : l_continentCountry.entrySet()) {
            if (!entry.getValue()) {
                Country l_country = getCountry(entry.getKey());
                String l_messageException = l_country.getD_countryName() + " in Continent "
                        + p_continent.getD_continentName() + " is not reachable";
                throw new InvalidMap(l_messageException);
            }
        }
        return !l_continentCountry.containsValue(false);
    }
    /**
     * Performs depth-first search (DFS) on a subgraph within a continent to determine connectivity.
     *
     * @param p_c                the starting country for DFS.
     * @param p_continentCountry a map to track visited countries within the subgraph.
     * @param p_continent        the continent containing the subgraph.
     */
    public void dfsSubgraph(Country p_c, HashMap<Integer, Boolean> p_continentCountry, Continent p_continent) {
        p_continentCountry.put(p_c.getD_countryId(), true);
        for (Country c : p_continent.getD_countries()) {
            if (p_c.getD_adjacentCountryIds().contains(c.getD_countryId())) {
                if (!p_continentCountry.get(c.getD_countryId())) {
                    dfsSubgraph(c, p_continentCountry, p_continent);
                }
            }
        }
    }

    /**
     * Carries out the "Remove Continent" operation on the map, which involves
     * deleting the specified continent along with its countries and associated data
     * from the map.
     *
     * @param p_continentName Continent Name to be found
     * @throws InvalidMap Exception
     */
    public void removeContinent(String p_continentName) throws InvalidMap {
        if (d_continents != null) {
            if (!Common.isNull(getContinent(p_continentName))) {

                // Deletes the continent and updates neighbour as well as country objects
                if (getContinent(p_continentName).getD_countries() != null) {
                    for (Country c : getContinent(p_continentName).getD_countries()) {
                        removeCountryNeighboursFromAll(c.getD_countryId());
                        updateNeighboursCont(c.getD_countryId());
                        d_countries.remove(c);
                    }
                }
                d_continents.remove(getContinent(p_continentName));
                System.out.println("Continent has been removed successfully");
            } else {
                throw new InvalidMap("No such Continent exists!");
            }
        } else {
            throw new InvalidMap("No continents in the Map to remove!");
        }
    }

    /**
     * Checks connectivity among all countries in the map.
     *
     * @return true if country connectivity is valid, false otherwise.
     * @throws InvalidMap if there are issues with country connectivity.
     */
    public boolean checkCountryConnectivity() throws InvalidMap {
        for (Country c : d_countries) {
            d_countryReach.put(c.getD_countryId(), false);
        }
        dfsCountry(d_countries.get(0));
        
        // Iterates over entries to locate the unreachable country
        for (Entry<Integer, Boolean> entry : d_countryReach.entrySet()) {
            if (!entry.getValue()) {
                String l_exceptionMessage = getCountry(entry.getKey()).getD_countryName() + " country is not reachable";
                throw new InvalidMap(l_exceptionMessage);
            }
        }
        return !d_countryReach.containsValue(false);
    }
    
    /**
     * Performs depth-first search (DFS) on the entire country graph to check connectivity.
     *
     * @param p_c the starting country for DFS.
     * @throws InvalidMap if there are issues with country connectivity.
     */
    public void dfsCountry(Country p_c) throws InvalidMap {
        d_countryReach.put(p_c.getD_countryId(), true);
        for (Country l_nextCountry : getAdjacentCountry(p_c)) {
            if (!d_countryReach.get(l_nextCountry.getD_countryId())) {
                dfsCountry(l_nextCountry);
            }
        }
    }
    
    /**
     * Gets a list of adjacent countries for a given country.
     *
     * @param p_country the country to retrieve adjacent countries for.
     * @return a list of adjacent countries.
     * @throws InvalidMap if the specified country doesn't have any adjacent countries.
     */
    public List<Country> getAdjacentCountry(Country p_country) throws InvalidMap {
        List<Country> l_adjCountries = new ArrayList<Country>();
        
        if (p_country.getD_adjacentCountryIds().size() > 0) {
            for (int i : p_country.getD_adjacentCountryIds()) {
                l_adjCountries.add(getCountry(i));
            }
        } else {
            throw new InvalidMap(p_country.getD_countryName() + " doesn't have any adjacent countries");
        }
        return l_adjCountries;
    }
    
    /**
     * Gets a country by its ID.
     *
     * @param p_targetCountryId the ID of the target country.
     * @return the country with the specified ID, or null if not found.
     */
    public Country getCountry(Integer p_targetCountryId) {
        return d_countries.stream().filter(l_country -> l_country.getD_countryId().equals(p_targetCountryId))
                .findFirst()
                .orElse(null);
    }
    
    /**
     * Gets a country by its name.
     *
     * @param p_countryName the name of the target country.
     * @return the country with the specified name, or null if not found.
     */
    public Country getCountryByName(String p_countryName) {
        return d_countries.stream().filter(l_country -> l_country.getD_countryName().equals(p_countryName)).findFirst()
                .orElse(null);
    }

    /**
     * Returns Country Object for a country ID.
     *
     * @param p_countryID Country Id to be found
     * @return country object
     */
    public Country getCountryByID(Integer p_countryID) {
        return d_countries.stream().filter(l_country -> l_country.getD_countryId().equals(p_countryID)).findFirst()
                .orElse(null);
    }

    /**
     * Gets a continent by its name.
     *
     * @param p_continentName the name of the target continent.
     * @return the continent with the specified name, or null if not found.
     */
    
    public Continent getContinent(String p_continentName) {
        return d_continents.stream().filter(l_continent -> l_continent.getD_continentName().equals(p_continentName))
                .findFirst().orElse(null);
    }

    /**
     * Returns the Continent object corresponding to a continent ID.
     *
     * @param p_continentID Continent Id to be found
     * @return continent object
     */
    public Continent getContinentByID(Integer p_continentID) {
        return d_continents.stream().filter(l_continent -> l_continent.getD_continentID().equals(p_continentID))
                .findFirst().orElse(null);
    }

    /**
     * Executes the "Add Continent" operation on the map.
     *
     * @param p_continentName Name of the Continent to be Added
     * @param p_controlValue  Control value of the continent to be added
     * @throws InvalidMap to handle Invalid addition
     */
    public void addContinent(String p_continentName, Integer p_controlValue) throws InvalidMap {
        int l_continentId;

        if (d_continents != null) {
            l_continentId = d_continents.size() > 0 ? Collections.max(getContinentIDs()) + 1 : 1;
            if (Common.isNull(getContinent(p_continentName))) {
                d_continents.add(new Continent(l_continentId, p_continentName, p_controlValue));
                System.out.println("Continent has been added successfully");
            } else {
                throw new InvalidMap("Continent cannot be added! It already exists!");
            }
        } else {
            d_continents = new ArrayList<Continent>();
            d_continents.add(new Continent(1, p_continentName, p_controlValue));
        }
    }

    /**
     * Executes the "Add Country" operation on the map.
     *
     * @param p_countryName   Name of Country to be Added
     * @param p_continentName Name of Continent to be added in
     * @throws InvalidMap Exception
     */
    public void addCountry(String p_countryName, String p_continentName) throws InvalidMap {
        int l_countryId;
        if (d_countries == null) {
            d_countries = new ArrayList<Country>();
        }
        if (Common.isNull(getCountryByName(p_countryName))) {
            l_countryId = d_countries.size() > 0  ? Collections.max(getCountryIDs()) + 1 : 1;
            if (d_continents != null && getContinent(p_continentName) != null
                    && getContinentIDs().contains(getContinent(p_continentName).getD_continentID())) {
                Country l_country = new Country(l_countryId, p_countryName,
                        getContinent(p_continentName).getD_continentID());
                d_countries.add(l_country);
                for (Continent c : d_continents) {
                    if (c.getD_continentName().equals(p_continentName)) {
                        c.addCountry(l_country);
                        System.out.println("Country has been added successfully");
                    }
                }
            } else {
                throw new InvalidMap("Cannot add Country to a Continent that doesn't exist!");
            }
        } else {
            throw new InvalidMap("Country with name " + p_countryName + " already Exists!");
        }
    }
    /**
     * Executes the "Add Neighbor" operation.
     *
     * @param p_countryName   Country whose neighbours are to be updated
     * @param p_neighbourName Country to be added as neighbour
     * @throws InvalidMap Exception
     */
    public void addCountryNeighbour(String p_countryName, String p_neighbourName) throws InvalidMap {
        if (d_countries != null) {
            if (!Common.isNull(getCountryByName(p_countryName))
                    && !Common.isNull(getCountryByName(p_neighbourName))) {
                d_countries.get(d_countries.indexOf(getCountryByName(p_countryName)))
                        .addNeighbour(getCountryByName(p_neighbourName).getD_countryId());
                System.out.println("Neighbor has been added successfully");
            } else {
                throw new InvalidMap("Invalid Neighbour Pair! Either of the Countries Doesn't exist!");
            }
        }
    }

    /**
     * Executes the "Remove Neighbor" operation.
     *
     * @param p_countryName   Country whose neighbors are to be updated
     * @param p_neighbourName Country to be removed as neighbor
     * @throws InvalidMap Exception
     */
    public void removeCountryNeighbour(String p_countryName, String p_neighbourName) throws InvalidMap {
        if (d_countries != null) {
            if (!Common.isNull(getCountryByName(p_countryName))
                    && !Common.isNull(getCountryByName(p_neighbourName))) {
                d_countries.get(d_countries.indexOf(getCountryByName(p_countryName)))
                        .removeNeighbour(getCountryByName(p_neighbourName).getD_countryId());
                System.out.println("Neighbor has been removed successfully");
            } else {
                throw new InvalidMap("Invalid Neighbour Pair! Either of the Countries Doesn't exist!");
            }
        }
    }
    /**
     * Removes a particular country as a neighbor from all associated countries
     * within continent objects. This operation is typically used when deleting a
     * country object.
     *
     * @param p_targetCountryId Country to be removed
     */
    public void updateNeighboursCont(Integer p_targetCountryId) {
        for (Continent c : d_continents) {
            c.removeCountryNeighboursFromAll(p_targetCountryId);
        }
    }
    /**
     * Eliminates a particular country as a neighbor from all associated countries
     * within the map's country list. This operation is typically employed when
     * deleting a country object.
     *
     * @param p_targetCountryId Country to be removed
     */
    public void removeCountryNeighboursFromAll(Integer p_targetCountryId) {
        for (Country c : d_countries) {
            if (!Common.isNull(c.getD_adjacentCountryIds())) {
                if (c.getD_adjacentCountryIds().contains(p_targetCountryId)) {
                    c.removeNeighbour(p_targetCountryId);
                }
            }
        }
    }
}
