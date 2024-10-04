package Models;

/**
 * This model class checks the player's card ownership and management.
 *
 * @author Hritik Nandanwar
 * @author Sweta Sarat
 * @author Vishav Teji
 * @author Muqaddaspreet Bhatia
 * @author Ushnish Chakravarty
 * @version 3.0.0
 */
public interface Card extends Order {

	/**
	 * 
	 * Pre-checking the order of card types.
	 * 
	 * @param p_gameState Gamestate
	 * @return true or false
	 */
	public Boolean checkOrderValidity(GameState p_gameState);

}