package com.my.project;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class JsonArrayReader {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 读取JSON数组中的指定字段，并将这些字段的值存储到一个集合中。
     *
     * @param jsonArray JSON数组字符串
     * @param fieldName 要读取的字段名
     * @param symbol    要添加的符号（可选，可以为null）
     * @return 用逗号隔开的字符串
     * @throws Exception 如果解析JSON时发生错误
     */
    public static String readFieldFromJsonArray(String jsonArray, String fieldName, String symbol) throws Exception {
        JsonNode rootNode = objectMapper.readTree(jsonArray);
        List<String> fieldValues = new ArrayList<>();

        for (JsonNode node : rootNode) {
            if (node.has(fieldName)) {
                String fieldValue = node.get(fieldName).asText();
                if (symbol != null && !symbol.isEmpty()) {
                    fieldValue = symbol + fieldValue + symbol;
                }
                fieldValues.add(fieldValue);
            }
        }

        return String.join(",", fieldValues);
    }

    public static void main(String[] args) {
        String jsonArray = "[{\"name\":\"Alice\",\"age\":25},{\"name\":\"Bob\",\"age\":30},{\"name\":\"Charlie\",\"age\":35}]";
        try {
            String result = readFieldFromJsonArray(jsonArray, "age", null);
            System.out.println(result);  // 输出: "Alice","Bob","Charlie"
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}