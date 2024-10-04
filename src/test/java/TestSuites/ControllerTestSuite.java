package TestSuites;

import Controllers.GameEngineControllerTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;



/**
 * Test suite for testing issue and execution of order functionality and
 * various player services of adding players, assigning armies and countries
 *
 * @author Hritik Nandanwar
 * @author Sweta Sarat
 * @author Vishav Teji
 * @author Muqaddaspreet Bhatia
 * @author Ushnish Chakravarty
 * @version 3.0.0
 */
@RunWith(Suite.class)
@SuiteClasses({ GameEngineControllerTest.class })
public class ControllerTestSuite {
}