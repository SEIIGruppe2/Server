package at.aau.se2.utils;

/**
 * The JsonSerializable interface represents objects that can be converted to JSON format.
 * Classes implementing this interface must provide a method to convert the object to a JSON string.
 */
public interface JsonSerializable {

    /**
     * Converts the object to a JSON string representation.
     *
     * @return a JSON string representing the object
     */
    String convertToJson();
}
