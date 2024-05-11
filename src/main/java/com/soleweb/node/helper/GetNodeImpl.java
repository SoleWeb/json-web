package com.soleweb.node.helper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.soleweb.exception.FieldNotFoundException;
import com.soleweb.i.node.helper.IGetNode;

public class GetNodeImpl implements IGetNode {
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

    public String getValueFromJson(JsonNode jsonNode, String path) {
        String[] pathElements = path.split("\\.", -1);
        JsonNode currentNode = jsonNode;
        for (String element : pathElements) {
            currentNode = currentNode.get(element);
            if (null == currentNode) return null; // Path doesn't exist
        }
        return currentNode.asText();
    }

    public ObjectNode constructJsonArrayOfObjects(ObjectNode json, String[] keys, String[] data) {
        json.put("key", null == data[0] ? "" : data[0]);
        ArrayNode ovd1Array = json.putArray("data");

        for (int i = 1; i < data.length; i++) {
            ObjectNode objectNode = ovd1Array.addObject();
            String[] values = data[i].split(",", -1);
            if (values.length < 3) {
                throw new FieldNotFoundException("Insufficient data for element \" + keys[i + 1] + \"::\" + i + \" in the data string.");
            }
            for (int j = 1; j < keys.length; j++) {
                String[] nestedKeys = keys[j].split("\\.", -1);
                objectNode.put(nestedKeys[1], values[j - 1]);
            }
        }
        return json;
    }
}
