package org.knowm.xchange.okex.v5.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public class OkexClosePositionRequest {

    @JsonProperty("instId")
    String instrument;

    @JsonProperty("posSide")
    String position;

    @JsonProperty("mgnMode")
    String marginMode;
}
