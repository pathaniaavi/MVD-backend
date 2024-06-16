package com.example.mvd.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IdResponseModel {

    @JsonProperty("@type")
    private String type;

    @JsonProperty("@id")
    private String id;

    @JsonProperty("edc:createdAt")
    private long createdAt;

    @JsonProperty("@context")
    private Context context;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Context {
        private String dct;
        private String edc;
        private String dcat;
        private String odrl;
        private String dspace;
    }
}
