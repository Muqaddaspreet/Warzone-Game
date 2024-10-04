package CommonFunctions;


import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.HashMap;

import org.junit.Test;

/**
 * This class is used to test functionality of Command module.
 *
 * @author Hritik Nandanwar
 * @author Sweta Sarat
 * @author Vishav Teji
 * @author Muqaddaspreet Bhatia
 * @author Ushnish Chakravarty
 * @version 3.0.0
 */
public class CommandTest {

    /**
     * Test whether root command is fetched successfully
     */
    @Test
    public void testFetchRootCommand() {
        Command l_command = new Command("gameplayer -add Player1");
        String l_rootCommand = l_command.getRootCommand();

        assertEquals("gameplayer", l_rootCommand);
    }

    /**
     * Test method for verifying the extraction of operations and arguments from a
     * Command object.
     * This test creates a Command object with a specific command string,
     * "gameplayer -add Player1",
     * and then calls the getOperationsAndArguments method to retrieve the
     * operations and arguments.
     * It compares the actual result to the expected result to ensure the correct
     * extraction of values.
     */
    @Test
    public void testGetOperationsAndArguments() {
        Command l_command = new Command("gameplayer -add Player1");
        List<Map<String, String>> l_actualCommandList = l_command.getOperationsAndArguments();
        List<Map<String, String>> l_expectedCommandList = new ArrayList<Map<String, String>>();

        Map<String, String> l_expectedCommandVal = new HashMap<String, String>() {
            {
                put("arguments", "Player1");
                put("operation", "add");
            }
        };
        l_expectedCommandList.add(l_expectedCommandVal);
        assertEquals(l_expectedCommandList, l_actualCommandList);
    }
}
