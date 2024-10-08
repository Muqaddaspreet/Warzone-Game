package CommonFunctions;

import java.io.Serializable;

import Constants.Constants;
import Models.GameState;

/**
 * Class to Add Exception to Logs that are not caught using try/catch.
 *
 * @author Hritik Nandanwar
 * @author Sweta Sarat
 * @author Vishav Teji
 * @author Muqaddaspreet Bhatia
 * @author Ushnish Chakravarty
 * @version 3.0.0
 */
public class ExceptionLogHandler implements Thread.UncaughtExceptionHandler, Serializable {

    /**
     * GameState to which Exception Log Belongs to.
     */
    GameState d_gameState;

    /**
     * Constructor to set the GameState Object.
     *
     * @param p_gameState Current GameState
     */
    public ExceptionLogHandler(GameState p_gameState) {
        d_gameState = p_gameState;
    }

    /**
     * Updates the Log in the GameState.
     *
     * @param p_t Thread of Exception.
     * @param p_e Throwable Instance of Exception
     */
    @Override
    public void uncaughtException(Thread p_t, Throwable p_e) {
        d_gameState.updateLog(p_e.getMessage(), Constants.ORDER_EFFECT);
    }
}
