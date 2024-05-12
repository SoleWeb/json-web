package io.github.soleweb.node.helper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.github.soleweb.exception.FieldNotFoundException;
import io.github.soleweb.i.node.helper.IGetNode;

/**
 * Class: GetNodeImpl <p>
 * Description:
 * This class implements the IGetNode interface and provides methods for extracting values from JSON nodes and constructing JSON arrays of objects.
 */
public class GetNodeImpl implements IGetNode {

    /**
     * Default Constructor
     */
    public GetNodeImpl() {
    }

    /**
     * Method: getDefaultValueFromJsonForFieldType <p>
     * Description:
     * Retrieves a default value from the JSON node for the specified field type.
     *
     * @param jsonNode   JsonNode, the JSON node from which the default value needs to be extracted
     * @param path       String, the path to the desired value
     * @param fieldType  Class of any Type(required DataType), the type of the field for which the default value is required
     * @return String, the default value extracted from the JSON node for the specified field type
     */
    public String getDefaultValueFromJsonForFieldType(JsonNode jsonNode, String path, Class<?> fieldType) {
        String value = getValueFromJson(jsonNode, path);

        if (null == value || value.isEmpty()) {
            if (fieldType == Integer.class || fieldType == int.class || fieldType == Long.class || fieldType == long.class || fieldType == Double.class || fieldType == double.class || fieldType == Float.class || fieldType == float.class) {
                return "0";
            }
            if (fieldType == Boolean.class || fieldType == boolean.class) return "false";
        }

        return value;
    }

    /**
     * Method: getValueFromJson <p>
     * Description:
     * Retrieves a value from the JSON node based on the specified path.
     *
     * @param jsonNode  JsonNode, the JSON node from which the value needs to be extracted
     * @param path      String, the path to the desired value
     * @return String, the value extracted from the JSON node
     */
    public String getValueFromJson(JsonNode jsonNode, String path) {
        String[] pathElements = path.split("\\.", -1);
        JsonNode currentNode = jsonNode;
        for (String element : pathElements) {
            currentNode = currentNode.get(element);
            if (null == currentNode) return null; // Path doesn't exist
        }
        return currentNode.asText();
    }

    /**
     * Method: constructJsonArrayOfObjects<p>
     * Description:
     * Constructs a JSON array of objects based on the provided keys and data.
     *
     * @param json  ObjectNode, the JSON node in which the array needs to be added
     * @param keys  String[], comma-separated key structure for each element, where index 0 always represents the root key
     * @param data  String[], array of data elements, where each iteration is separated by '|' and elements within an iteration are separated by comma
     * @return ObjectNode, the JSON array of objects constructed based on the provided keys and data
     */
    public ObjectNode constructJsonArrayOfObjects(ObjectNode json, String[] keys, String[] data) {
        json.put("key", null == data[0] ? "" : data[0]);
        ArrayNode dataArray = json.putArray("data");

        for (int i = 1; i < data.length; i++) {
            ObjectNode objectNode = dataArray.addObject();
            String[] values = data[i].split(",", -1);
            if (values.length < 3) {
                throw new FieldNotFoundException("Insufficient data for element " + keys[i + 1] + "::" + i + " in the data string.");
            }
            for (int j = 1; j < keys.length; j++) {
                String[] nestedKeys = keys[j].split("\\.", -1);
                objectNode.put(nestedKeys[1], values[j - 1]);
            }
        }
        return json;
    }
}
