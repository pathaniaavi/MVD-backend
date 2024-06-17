package com.example.mvd.Model;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PolicyModel {

    @JsonProperty("@context")
    private Map<String, String> context;

    @JsonProperty("@id")
    private String id;

    @JsonProperty("policy")
    private EdcPolicy edcPolicy;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EdcPolicy {

        @JsonProperty("@context")
        private String context;

        private List<Permission> permission;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Permission {

        private String action;
        private Constraint constraint;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Constraint {

        @JsonProperty("@type")
        private String type;

        private String leftOperand;
        private String operator;
        private String rightOperand;
    }
}
