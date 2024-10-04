package Models.LogModels;

import java.io.Serializable;

import Constants.Constants;
import Views.LogViews.*;

/**
 * Records and manages logs for different stages within the game.
 * Extends a custom observable class to manage observers for log updates.
 *
 * @author Hritik Nandanwar
 * @author Sweta Sarat
 * @author Vishav Teji
 * @author Muqaddaspreet Bhatia
 * @author Ushnish Chakravarty
 * @version 3.0.0
 */
public class LogEntryBuffer extends TailoredObservable implements Serializable {

    /** Stores the log message. */
    private String d_logMessage;
    
    /**
     * Constructor to initialize LogEntryBuffer and add default observers.
     */
    public LogEntryBuffer() {
        Logger defaultLogger = new Logger();
        this.addObserver(defaultLogger);
    }
    
    /**
     * Retrieves the stored log message.
     *
     * @return Log Message
     */
    public String getD_logMessage() {
        return d_logMessage;
    }
    /**
     * Sets the log message and notifies the log observer object.
     *
     * @param p_messageToUpdate Log message to set
     * @param p_logType         Type of Log: Command, Order, Effect, Phase, etc.
     */
    public void setLogMsg(String p_messageToUpdate, String p_logType) {
        switch (p_logType) {
            case Constants.HANDLE_COMMAND:
                d_logMessage = System.lineSeparator() + "Command Entered: " + p_messageToUpdate
                        + System.lineSeparator();
                break;
            case Constants.ISSUE_ORDERS:
                d_logMessage = System.lineSeparator() + "Order Issued: " + p_messageToUpdate + System.lineSeparator();
                break;
            case Constants.GAMEPLAY_PHASE:
                d_logMessage = System.lineSeparator() + "=======" + p_messageToUpdate + "======="
                        + System.lineSeparator() + System.lineSeparator();
                break;
            case Constants.ORDER_EFFECT:
                d_logMessage = "Log: " + p_messageToUpdate + System.lineSeparator();
                break;
            case Constants.START_GAME:
            case Constants.END_GAME:
                d_logMessage = p_messageToUpdate + System.lineSeparator();
                break;
        }
        notifyObservers(); // Notifies all registered observers about the log message update.
    }
}
