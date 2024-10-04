package Exceptions;

import java.io.Serializable;

/**
 * InvalidMap class created that extends Exception class.
 *
 * @author Hritik Nandanwar
 * @author Sweta Sarat
 * @author Vishav Teji
 * @author Muqaddaspreet Bhatia
 * @author Ushnish Chakravarty
 * @version 3.0.0
 */
public class InvalidMap extends Exception implements Serializable{
    
    /**
     * InvalidMap constructor is used to print message when exception is caught in
     * case map is invalid.
     *
     * @param p_invalidMapMessage message to print when map is invalid.
     */
    public InvalidMap(String p_invalidMapMessage)
    {
        super(p_invalidMapMessage);
    }
}
