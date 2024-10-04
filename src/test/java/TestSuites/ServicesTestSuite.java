package TestSuites;

import Services.MapServiceTest;
import Services.PlayerServiceTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * This class represents a test suite that includes multiple test classes
 * related to map-related functionalities.
 * Specifically, it includes tests from the MapTest and MapServiceTest classes.
 * The test suite allows for the execution of multiple related tests together.
 *
 * @author Hritik Nandanwar
 * @author Sweta Sarat
 * @author Vishav Teji
 * @author Muqaddaspreet Bhatia
 * @author Ushnish Chakravarty
 * @version 3.0.0
 */
@RunWith(Suite.class)
@SuiteClasses({ MapServiceTest.class, PlayerServiceTest.class })
public class ServicesTestSuite {

}
