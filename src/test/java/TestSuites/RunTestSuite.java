package TestSuites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Test suit for running all test suites involved in this game project
 *
 * @author Hritik Nandanwar
 * @author Sweta Sarat
 * @author Vishav Teji
 * @author Muqaddaspreet Bhatia
 * @author Ushnish Chakravarty
 * @version 3.0.0
 */
@RunWith(Suite.class)
@SuiteClasses({ ControllerTestSuite.class, ModelTestSuite.class, ServicesTestSuite.class })
public class RunTestSuite {
}