package CommonFunctions;

import Constants.Constants;

import java.io.File;
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

/**
 * The Common class consists of commonly used utility functions for string, collection, and file operations.
 * It provides methods for checking string emptiness, object nullity, collection and map emptiness, and
 * constructing file paths.
 *
 *
 * @author Hritik Nandanwar
 * @author Sweta Sarat
 * @author Vishav Teji
 * @author Muqaddaspreet Bhatia
 * @author Ushnish Chakravarty
 * @version 3.0.0
 */
public class Common implements Serializable{
    
    /**
     * Checks whether the given string is empty or null.
     *
     * @param p_str The string to be checked.
     * @return {@code true} if the string is empty or null, otherwise {@code false}.
     */
    public static boolean isEmpty(String p_str) {
        return (p_str == null || p_str.trim().isEmpty());
    }
    
    /**
     * Checks whether the given string is not empty.
     *
     * @param p_str The string to be checked.
     * @return {@code true} if the string is not empty, otherwise {@code false}.
     */
    public static boolean isNotEmpty(String p_str) {
        return !isEmpty(p_str);
    }
    
    /**
     * Checks whether the given object is null.
     *
     * @param p_object The object to be checked.
     * @return {@code true} if the object is null, otherwise {@code false}.
     */
    public static boolean isNull(Object p_object) {
        return (p_object == null);
    }
    
    /**
     * Checks whether the given collection is empty or null.
     *
     * @param p_collection The collection to be checked.
     * @return {@code true} if the collection is empty or null, otherwise {@code false}.
     */
    public static boolean isCollectionEmpty(Collection<?> p_collection) {
        return (p_collection == null || p_collection.isEmpty());
    }
    
    /**
     * Checks whether the given map is empty or null.
     *
     * @param p_map The map to be checked.
     * @return {@code true} if the map is empty or null, otherwise {@code false}.
     */
    public static boolean isMapEmpty(Map<?, ?> p_map) {
        return (p_map == null || p_map.isEmpty());
    }
    
    /**
     * Constructs the absolute file path for a map file based on the provided file name.
     *
     * @param p_fileName The name of the map file.
     * @return The absolute file path for the map file.
     */
    public static String getMapFilePath(String p_fileName) {
        String l_absolutePath = new File("").getAbsolutePath();
        return l_absolutePath + File.separator + Constants.WARZONE_SRC_MAIN_RESOURCES + File.separator + p_fileName
                + Constants.EXTENSION_FOR_MAP_FILES;
    }
}
