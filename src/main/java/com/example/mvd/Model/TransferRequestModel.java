package com.example.mvd.Model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferRequestModel {
    @JsonProperty("@context")
    private Context context;

    @JsonProperty("@type")
    private String type;

    @JsonProperty("connectorId")
    private String connectorId;

    @JsonProperty("connectorAddress")
    private String connectorAddress;

    @JsonProperty("contractId")
    private String contractId;

    @JsonProperty("assetId")
    private String assetId;

    @JsonProperty("protocol")
    private String protocol;

    @JsonProperty("dataDestination")
    private DataDestination dataDestination;

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
    public static class DataDestination {
        private String type;
        private String baseUrl;
    }
}
