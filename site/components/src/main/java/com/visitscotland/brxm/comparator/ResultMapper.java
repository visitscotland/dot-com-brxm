package com.visitscotland.brxm.comparator;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.visitscotland.brxm.comparator.model.Feature;
import com.visitscotland.brxm.comparator.model.Provider;
import com.visitscotland.brxm.comparator.model.ShortListModule;
import org.hippoecm.hst.container.RequestContextProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ResultMapper {

    private final ComparisonMapper comparisonMapper;

    public ResultMapper(ComparisonMapper comparisonMapper) {
        this.comparisonMapper = comparisonMapper;
    }

    private static final Logger logger = LoggerFactory.getLogger(ResultMapper.class);

    /**
     * ⚠️ Important Note: You can only read the request body once unless you cache it.
     *
     * @return
     * @throws IOException
     */
    private String getRequestBody() throws IOException {
        var request = RequestContextProvider.get().getServletRequest();
        return new BufferedReader(new InputStreamReader(request.getInputStream()))
                .lines()
                .collect(Collectors.joining(" "));
    }

    private Map<String, String> get() throws IOException {
        var parameters = RequestContextProvider.get().getServletRequest().getParameterMap();
        Map<String, String> plainParam = new HashMap<>();
        parameters.forEach((key, value) -> plainParam.put(key, value[0]));
        return plainParam;
    }

    public ShortListModule map(){
        try {
            return map(get());
        } catch (IOException e) {
            //TODO:
            throw new RuntimeException(e);
        }
    }

    private ShortListModule map(Map<String, String> body){
        ShortListModule requestBody = new ShortListModule();
        requestBody.setFunctions(comparisonMapper.getFunctions());
        requestBody.setProviders(shortlist(body));

        return requestBody;
    }

    private List<Provider> shortlist(Map<String, String> body){
        List<Provider> provider = comparisonMapper.getProviders();
        String[] allFunctions = getAllFunctions();
        List<Provider> shortlist = new ArrayList<>();

        for (Provider p : provider) {
            boolean include = true;
            for (Map.Entry<String, String> entry: body.entrySet()){
                if (entry.getValue().equals("true") &&
                        !in(entry.getKey(), p.getFunctions()) &&
                        in(entry.getKey(), allFunctions)){
                    include = false;
                    break;
                }
            }
            if (include) {
                shortlist.add(p);
            }
        }
        return shortlist;

    }

    private boolean in(String value, String[] list){
        if (list != null) {
            for (String elm : list) {
                if (value.trim().equalsIgnoreCase(elm)) {
                    return true;
                }
            }
        }

        return false;
    }

    private String[] getAllFunctions(){
        return comparisonMapper.getFunctions().stream().map(Feature::getId)
                .toArray(String[]::new);
    }
}
