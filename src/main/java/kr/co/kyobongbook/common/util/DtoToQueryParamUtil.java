package kr.co.kyobongbook.common.util;

import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class DtoToQueryParamUtil {

    public static String convertToQueryParams(Object dto) throws IllegalAccessException {
        Map<String, String> queryParamsMap = new HashMap<>();

        for (Field field : dto.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value = field.get(dto);
            if (value != null) {
                queryParamsMap.put(field.getName(), URLEncoder.encode(value.toString(), StandardCharsets.UTF_8));
            }
        }

        StringBuilder queryParams = new StringBuilder();
        for (Map.Entry<String, String> entry : queryParamsMap.entrySet()) {
            queryParams.append("&").append(entry.getKey()).append("=").append(entry.getValue());
        }

        // 첫 번째 & 제거
        return queryParams.substring(1);
    }
}
