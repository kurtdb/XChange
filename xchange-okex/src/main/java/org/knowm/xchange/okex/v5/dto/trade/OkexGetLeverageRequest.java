package org.knowm.xchange.okex.v5.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public class OkexGetLeverageRequest {

    @JsonProperty("instId")
    String instrument;

    @JsonProperty("mgnMode")
    String marginMode;
}
