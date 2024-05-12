package io.github.soleweb.i.node.helper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Interface: IGetNode
 * <p>
 * Description: This interface provides methods for extracting values from JSON nodes and constructing JSON arrays of objects.
 * <p>
 * Author: M. Danish
 * <p>
 * Email: [Author Email]
 * <p>
 * Date: 11-05-2024
 */
public interface IGetNode {

    /**
     * Method: getValueFromJson
     * @param jsonNode  JsonNode, the JSON node from which the value needs to be extracted
     * @param path  String, the path to the desired value
     * @return String, the value extracted from the JSON node
     */
    String getValueFromJson(JsonNode jsonNode, String path);

    /**
     * Method: getDefaultValueFromJsonForFieldType <p>
     * Description:
     * Retrieves a default value from the JSON node for the specified field type.
     *
     * @param jsonNode JsonNode, the JSON node from which the default value needs to be extracted
     * @param path  String, the path to the desired value
     * @param fieldType  Class of any Type(required DataType), the type of the field for which the default value is required
     * @return String, the default value extracted from the JSON node for the specified field type
     */
    String getDefaultValueFromJsonForFieldType(JsonNode jsonNode, String path, Class<?> fieldType);

    /**
     * Method: constructJsonArrayOfObjects
     * <p>
     * Description: Constructs a JSON array of objects based on the provided keys and data.
     *
     * @param json  ObjectNode, the JSON node in which the array needs to be added
     * @param keys  String[], comma-separated key structure for each element, where index 0 always represents the root key
     * @param data  String[], array of data elements, where each iteration is separated by '|' and elements within an iteration are separated by comma
     * @return ObjectNode, the JSON array of objects constructed based on the provided keys and data
     *
     * Example:
     * String keysString = "rootKey,ovd1.ExpiryDate,ovd1.IDVerificationDocument,ovd1.DocumentNumber";
     * String dataString = "ovd1|null,,9876|null,1234,";
     * Returned JSON: {"key":"ovd1","data":[{"ExpiryDate":null,"IDVerificationDocument":"","DocumentNumber":"9876"},{"ExpiryDate":null,"IDVerificationDocument":"1234","DocumentNumber":""}]}
     */
    ObjectNode constructJsonArrayOfObjects(ObjectNode json, String[] keys, String[] data);
}
