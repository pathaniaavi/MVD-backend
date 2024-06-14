package com.example.mvd.Model;

import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContractDefinitionModel {

    @JsonProperty("@context")
    private Map<String, String> context;

    @JsonProperty("@id")
    private String id;

    @JsonProperty("accessPolicyId")
    private String accessPolicyId;

    @JsonProperty("contractPolicyId")
    private String contractPolicyId;

    @JsonProperty("assetsSelector")
    private List<AssetSelector> assetsSelector; // Using Object for simplicity, you can define a specific type if needed
}
