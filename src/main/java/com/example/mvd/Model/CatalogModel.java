package com.example.mvd.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatalogModel {

    @JsonProperty("@context")
    private Context context;

    @JsonProperty("counterPartyAddress")
    private String counterPartyAddress;

    @JsonProperty("protocol")
    private String protocol;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Context {
        @JsonProperty("@vocab")
        private String vocab;
    }
}
