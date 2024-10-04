package Models.LogModels;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Views.LogViews.*;

/**
 * Custom subject (observable) class responsible for managing observers.
 *
 * @author Hritik Nandanwar
 * @author Sweta Sarat
 * @author Vishav Teji
 * @author Muqaddaspreet Bhatia
 * @author Ushnish Chakravarty
 * @version 3.0.0
 */
public class TailoredObservable implements Serializable{
    /**
     * List to maintain registered observers subscribed to this observable subject.
     */
    private List<TailoredObserver> observers = new ArrayList<>();
    
    /**
     * Adds an observer to the list of registered observers.
     *
     * @param observer The observer to be added.
     */
    public void addObserver(TailoredObserver observer) {
        observers.add(observer);
    }
    /**
     * Removes a specific observer from the list of registered observers.
     *
     * @param observer The observer to be removed.
     */
    public void removeObserver(TailoredObserver observer) {
        observers.remove(observer);
    }
    /**
     * Notifies all registered observers about changes in the observable.
     */
    public void notifyObservers() {
        for (TailoredObserver observer : observers) {
            observer.update(this);
        }
    }
}
