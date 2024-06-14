package com.example.mvd.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssetModel {
    @JsonProperty("@context")
    private Context context;

    @JsonProperty("@id")
    private String id;

    private Properties properties;
    private DataAddress dataAddress;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Context {
        @JsonProperty("@vocab")
        private String vocab;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Properties {
        private String name;
        private String contenttype;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DataAddress {
        private String type;
        private String name;
        private String baseUrl;
        private String proxyPath;
    }
}
