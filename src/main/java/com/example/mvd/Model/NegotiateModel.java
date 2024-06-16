package com.example.mvd.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NegotiateModel {

    private String protocol;

    @JsonProperty("@context")
    private Context context;

    private String connectorAddress;
    private Offer offer;
    private String connectorId;

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
    public static class Offer {
        private String offerId;
        private String assetId;
        private Policy policy;

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        public static class Policy {
            @JsonProperty("@type")
            private String type;

            @JsonProperty("@context")
            private String context;

            private String uid;
            private List<Object> obligation;
            private List<Permission> permission;
            private List<Object> prohibition;
            private String target;

            @Data
            @AllArgsConstructor
            @NoArgsConstructor
            public static class Permission {
                @JsonProperty("odrl:target")
                private String target;

                @JsonProperty("odrl:action")
                private Action action;

                @JsonProperty("odrl:constraint")
                private Constraint constraint;

                @Data
                @AllArgsConstructor
                @NoArgsConstructor
                public static class Action {
                    @JsonProperty("odrl:type")
                    private String type;
                }

                @Data
                @AllArgsConstructor
                @NoArgsConstructor
                public static class Constraint {
                    @JsonProperty("odrl:leftOperand")
                    private String leftOperand;

                    @JsonProperty("odrl:operator")
                    private Operator operator;

                    @JsonProperty("odrl:rightOperand")
                    private String rightOperand;

                    @Data
                    @AllArgsConstructor
                    @NoArgsConstructor
                    public static class Operator {
                        @JsonProperty("@id")
                        private String id;
                    }
                }
            }
        }
    }
}
