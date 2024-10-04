package Views;

import CommonFunctions.Common;
import Constants.Constants;
import Exceptions.InvalidMap;
import Models.*;
import org.davidmoten.text.utils.WordWrap;

import java.util.List;

/**
 * This class is primarily responsible for displaying Map Content on console.
 *
 * @author Hritik Nandanwar
 * @author Sweta Sarat
 * @author Vishav Teji
 * @author Muqaddaspreet Bhatia
 * @author Ushnish Chakravarty
 * @version 3.0.0
 */

public class ShowMap {
    /**
     * d_players stores the list of players.
     */
    List<Player> d_players;
    
    /**
     * d_gameState stores the current game state.
     */
    
    GameState d_gameState;
    
    /**
     * d_map stores the map.
     */
    Map d_map;
    
    /**
     * d_countries stores the list of countries.
     */
    List<Country> d_countries;
    
    /**
     * d_continents stores the list of continents.
     */
    List<Continent> d_continents;
    
    /**
     * Change color ANSI code.
     */
    
    public static final String ANSI_RESET = "\u001B[0m";

    /**
     * Constructs a new ShowMap object with the provided GameState and list of
     * Players.
     *
     * @param p_gameState The GameState to display on the map.
     */
    public ShowMap(GameState p_gameState) {
        d_gameState = p_gameState;
        d_players = p_gameState.getD_playerList();
        d_map = p_gameState.getD_map();
        d_countries = d_map.getD_countries();
        d_continents = d_map.getD_continents();
    }
    
    /**
     * Returns colored text by applying ANSI color code.
     *
     * @param p_color The ANSI color code to apply
     * @param p_text The input text to be colored
     * @return A coloured text string is returned
     */
    
    private String getColoredText(String p_color, String p_text) {
        if (p_color == null)
            return p_text;
        
        return p_color + p_text + ANSI_RESET;
    }
    
    /**
     * Display Centered String within a specific width.
     *
     * @param p_width The total width within which to center the text
     * @param p_s The input text to be centered
     */

    private void displayCenteredText(int p_width, String p_s) {
        String l_centeredString = String.format("%-" + p_width + "s",
                String.format("%" + (p_s.length() + (p_width - p_s.length()) / 2) + "s", p_s));
        
        System.out.format(l_centeredString + "\n");
    }
    
    /**
     * Displays a separator line in the console.
     */
    
    private void displaySeparator() {
        StringBuilder separator = new StringBuilder();
        
        for (int i = 0; i < Constants.CONSOLE_WIDTH - 2; i++) {
            separator.append("*");
        }
        
        System.out.format("*%s*%n", separator.toString());
    }
    
    /**
     * Display Continent Name on console including its control value.
     *
     * @param p_continentName The continent to be displayed
     */
    
    private void displayContinentName(String p_continentName) {
        String l_continentName = p_continentName + " ( " + Constants.CONTROL_VALUE + " : "
                + d_gameState.getD_map().getContinent(p_continentName).getD_continentValue() + " )";

        displaySeparator();
        if (d_players != null) {
            l_continentName = getColoredText(getContinentColor(p_continentName), l_continentName);
        }
        displayCenteredText(Constants.CONSOLE_WIDTH, l_continentName);
        displaySeparator();
    }
    
    /**
     * Colorizes a country name.
     *
     * @param p_index The index of the country
     * @param p_countryName The name of the country
     * @return Returns the colorized country name
     */
    
    private String formatCountryName(int p_index, String p_countryName) {
        String l_indexedString = String.format("%02d. %s", p_index, p_countryName);
        if (d_players != null) {
            String l_armies = "( " + Constants.ARMIES + " : " + getCountryArmies(p_countryName) + " )";
            l_indexedString = String.format("%02d. %s %s", p_index, p_countryName, l_armies);
        }

        return getColoredText(getCountryColor(p_countryName), String.format("%-30s", l_indexedString));
    }
    
    /**
     * Displays the name of adjacent countries in a colorized and formatted fashion.
     *
     * @param p_countryName The name of the country
     * @param p_adjCountries The list of adjacent countries
     */
    
    private void displayFormattedAdjacentCountryName(String p_countryName, List<Country> p_adjCountries) {
        StringBuilder l_commaSeparatedCountries = new StringBuilder();
        
        for (int i = 0; i < p_adjCountries.size(); i++) {
            l_commaSeparatedCountries.append(p_adjCountries.get(i).getD_countryName());
            if (i < p_adjCountries.size() - 1)
                l_commaSeparatedCountries.append(", ");
        }
        String l_adjacentCountry = Constants.CONNECTIVITY + " : "
                + WordWrap.from(l_commaSeparatedCountries.toString()).maxWidth(Constants.CONSOLE_WIDTH).wrap();
        System.out.println(getColoredText(getCountryColor(p_countryName), l_adjacentCountry));
        System.out.println();
    }
    
    /**
     * Gets the country color based on player.
     *
     * @param p_countryName The name of the country
     * @return Color of the country
     */
    private String getCountryColor(String p_countryName) {
        if (getCountryOwner(p_countryName) != null) {
            return getCountryOwner(p_countryName).getD_playerColor();
        } else {
            return null;
        }
    }

    /**
     * Get the Color of continent based on Player.
     *
     * @param p_continentName Continent Name to be rendered.
     * @return Color of continent.
     */
    private String getContinentColor(String p_continentName) {
        if (getContinentOwner(p_continentName) != null) {
            return getContinentOwner(p_continentName).getD_playerColor();
        } else {
            return null;
        }
    }

    /**
     * Get the player who owns the country.
     *
     * @param p_countryName Name of country
     * @return the player object
     */
    private Player getCountryOwner(String p_countryName) {
        if (d_players != null) {
            for (Player p : d_players) {
                if (p.getCountryList().contains(p_countryName)) {
                    return p;
                }
            }
        }
        return null;
    }

    /**
     * Display the Player Information in formatted settings.
     *
     * @param p_index  Index of the Player
     * @param p_player Player Object
     */
    private void displayPlayerInfo(Integer p_index, Player p_player) {
        String l_playerInfo = String.format("%02d. %-8s %s", p_index, p_player.getD_playerName(),
                " -> " + getColoredText(p_player.getD_playerColor(), " COLOR "));
        System.out.println(l_playerInfo);
    }

    /**
     * Displays the Players in Formatted Settings.
     *
     */
    private void displayPlayers() {
        int l_counter = 0;

        displaySeparator();
        displayCenteredText(Constants.CONSOLE_WIDTH, "GAME PLAYERS");
        displaySeparator();

        for (Player p : d_players) {
            l_counter++;
            displayPlayerInfo(l_counter, p);
            displayCardsOwnedByPlayers(p);
        }
    }
    
    /**
     * Function that visually presents the player's card count.
     *
     * @param p_player Player Instance
     */
    private void displayCardsOwnedByPlayers(Player p_player){
        StringBuilder l_cards = new StringBuilder();
        
        for(int i=0; i<p_player.getD_playerOwnedCards().size(); i++) {
            l_cards.append(p_player.getD_playerOwnedCards().get(i));
            if(i<p_player.getD_playerOwnedCards().size()-1)
                l_cards.append(", ");
        }
        
        String l_cardsOwnedByPlayer = "Cards Owned : "+ WordWrap.from(l_cards.toString()).maxWidth(Constants.CONSOLE_WIDTH).wrap();
        System.out.println(getColoredText(p_player.getD_playerColor(),l_cardsOwnedByPlayer));
        System.out.println();
    }

    /**
     * Gets the continent owner.
     *
     * @param p_continentName continent name
     * @return player object
     */
    private Player getContinentOwner(String p_continentName) {
        if (d_players != null) {
            for (Player p : d_players) {
                if (!Common.isNull(p.getContinentList()) && p.getContinentList().contains(p_continentName)) {
                    return p;
                }
            }
        }
        return null;
    }

    /**
     * Gets the number of armies for a country.
     *
     * @param p_countryName name of the country
     * @return number of armies
     */
    private Integer getCountryArmies(String p_countryName) {
        Integer l_armies = d_gameState.getD_map().getCountryByName(p_countryName).getD_armyCount();

        if (l_armies == null)
            return 0;
        return l_armies;
    }

    /**
     * This method displays the list of continents and countries present in the .map
     * files alongside current state of the game.
     */
    public void showMap() {

        if (d_players != null) {
            displayPlayers();
        }
        
        // renders the continent if any
        if (!Common.isNull(d_continents)) {
            d_continents.forEach(l_continent -> {
                displayContinentName(l_continent.getD_continentName());
                
                List<Country> l_continentCountries = l_continent.getD_countries();
                final int[] l_countryIndex = { 1 };
                
                // renders the country if any
                if (!Common.isCollectionEmpty(l_continentCountries)) {
                    l_continentCountries.forEach((l_country) -> {
                        String l_formattedCountryName = formatCountryName(l_countryIndex[0]++,
                                l_country.getD_countryName());
                        System.out.println(l_formattedCountryName);
                        try {
                            List<Country> l_adjCountries = d_map.getAdjacentCountry(l_country);

                            displayFormattedAdjacentCountryName(l_country.getD_countryName(), l_adjCountries);
                        } catch (InvalidMap l_invalidMap) {
                            System.out.println(l_invalidMap.getMessage());
                        }
                    });
                } else {
                    System.out.println("No countries are present in the continent!");
                }
            });
        } else {
            System.out.println("There are no continents to display!");
        }
    }
    
}
