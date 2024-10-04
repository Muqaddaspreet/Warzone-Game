package Exceptions;

import java.io.Serializable;

/**
 * InvalidCommand class created that extends Exception class.
 *
 * @author Hritik Nandanwar
 * @author Sweta Sarat
 * @author Vishav Teji
 * @author Muqaddaspreet Bhatia
 * @author Ushnish Chakravarty
 * @version 3.0.0
 */
public class InvalidCommand extends Exception implements Serializable{
    
    /**
     * InvalidCommand constructor is used to print message when exception is caught
     * in case command is invalid.
     *
     * @param p_errorMessage message to print when command is invalid.
     */
    public InvalidCommand(String p_errorMessage)
    {
        super(p_errorMessage);
    }
}
