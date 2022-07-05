package restapi.repository;

import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import restapi.exception.ConsumeFormulaAPIException;
import restapi.exception.FormulaNotFoundException;

import java.util.*;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class FormulaRepository {

    private final Environment env;
    private final RestTemplate restTemplate;
    public static final String FORMULA_API_URL = "formulaAPIUrl";
    public static final String FORMULA_DETAILS_TO_PUBLISH = "formulaDetailsToPublish";

    public Map<String, Object> getFormulaByName(String formulaName) {

        String urlProp = env.getProperty(FORMULA_API_URL);

        if(!StringUtils.hasLength(urlProp))
            throw new ConsumeFormulaAPIException("Formula API url is not provided", null);

        String url = String.format(urlProp, formulaName);

        Map<String, Object> formulaDetails;

        try {
            formulaDetails = restTemplate.getForObject(url, Map.class);
        } catch (Exception e) {
            throw new ConsumeFormulaAPIException(String.format("Unable to connect to formula API, URL: %s ", url), e.getCause());
        }

        if (formulaDetails == null) {
            throw new FormulaNotFoundException();
        }

        return formulaDetails.entrySet()
                .stream()
                .filter(stringObjectEntry -> filterFormulaDetail(stringObjectEntry.getKey()))
                .collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue()));
    }

    private boolean filterFormulaDetail(String value) {

        String keys = env.getProperty(FORMULA_DETAILS_TO_PUBLISH);

        if (!StringUtils.hasLength(keys))
            return true;

        String[] keysTobeFilter = keys.split(",");

        if (ObjectUtils.isEmpty(keysTobeFilter))
            return true;

        return Arrays.asList(keysTobeFilter).contains(value);
    }
}
