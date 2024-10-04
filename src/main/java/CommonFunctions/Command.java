package CommonFunctions;

import java.io.Serializable;
import java.util.*;

/**
 * The Command class represents a command with its associated operations and arguments.
 * It provides methods to extract the root command, obtain a list of operations and their corresponding arguments,
 * and check for the presence of required keys in a given input map
 *
 * @author Hritik Nandanwar
 * @author Sweta Sarat
 * @author Vishav Teji
 * @author Muqaddaspreet Bhatia
 * @author Ushnish Chakravarty
 * @version 3.0.0
 */
public class Command implements Serializable {
    
    /**
     * The original command string.
     */
    public String d_command;
    
    /**
     * Constructs a {@code Command} instance with the provided command string.
     * Trims leading and trailing spaces and ensures single spaces between words.
     *
     * @param p_command The command string to be processed.
     */
    public Command(String p_command)
    {
        this.d_command = p_command.trim().replaceAll(" +", " ");
    }
    
    /**
     * Retrieves the root command from the original command string.
     *
     * @return The root command.
     */
    public String getRootCommand()
    {
        return d_command.split(" ")[0];
    }
    
    /**
     * Parses the command string to extract a list of operations and their corresponding arguments.
     *
     * @return A list of maps where each map represents an operation and its associated arguments.
     */
    public List<Map<String, String>> getOperationsAndArguments() {
        String l_rootCommand = getRootCommand();
        String l_operationsString = d_command.replace(l_rootCommand, "").trim();
        
        if (null == l_operationsString || l_operationsString.isEmpty()) {
            return new ArrayList<Map<String, String>>();
        }
        boolean l_isFlagLessCommand = !l_operationsString.contains("-") && !l_operationsString.contains(" ");
        
        // handle commands to load files, ex: loadmap filename
        if (l_isFlagLessCommand) {
            l_operationsString = "-filename " + l_operationsString;
        }
        
        List<Map<String, String>> l_operations_list = new ArrayList<Map<String, String>>();
        String[] l_operations = l_operationsString.split("-");
        
        Arrays.stream(l_operations).forEach((operation) -> {
            if (operation.length() > 1) {
                l_operations_list.add(getOperationAndArgumentsMap(operation));
            }
        });
        
        return l_operations_list;
    }
    
    /**
     * This function maps operation to argument.
     *
     * @param p_operation User command
     * @return Returns mapping of operation to argument
     */
    private Map<String, String> getOperationAndArgumentsMap(String p_operation) {
        Map<String, String> l_operationMap = new HashMap<String, String>();

        String[] l_splitOperation = p_operation.split(" ");
        String l_arguments = "";

        l_operationMap.put("operation", l_splitOperation[0]);

        if (l_splitOperation.length > 1) {
            String[] l_argumentValues = Arrays.copyOfRange(l_splitOperation, 1, l_splitOperation.length);
            l_arguments = String.join(" ", l_argumentValues);
        }

        l_operationMap.put("arguments", l_arguments);

        return l_operationMap;
    }
    
    /**
     * Checks whether a required key is present in the given input map and its associated value is not empty.
     *
     * @param p_key       The key to check for in the input map.
     * @param p_inputMap  The input map to check against.
     * @return {@code true} if the key is present and its associated value is not empty, otherwise {@code false}.
     */
    public boolean checkRequiredKeysPresent(String p_key, Map<String, String> p_inputMap) {
        if (p_inputMap.containsKey(p_key) && null != p_inputMap.get(p_key)
                && !p_inputMap.get(p_key).isEmpty())
            return true;
        return false;
    }

        /**
     * Getter for the command.
     *
     * @return command in string
     */
    public String getD_command() {
        return d_command;
    }
}
