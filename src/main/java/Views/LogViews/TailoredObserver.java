package Views.LogViews;

import java.io.Serializable;

/**
 * Custom observer interface.
 *
 * @author Hritik Nandanwar
 * @author Sweta Sarat
 * @author Vishav Teji
 * @author Muqaddaspreet Bhatia
 * @author Ushnish Chakravarty
 * @version 3.0.0
 */
public abstract class TailoredObserver implements Serializable{
    /**
     * This method is called when the observed object is changed.
     * 
     * @param observable The object that has changed and is being observed.
     */
    public abstract void update(Object observable);
}