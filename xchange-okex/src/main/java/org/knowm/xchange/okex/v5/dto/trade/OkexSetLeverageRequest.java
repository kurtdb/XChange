package org.knowm.xchange.okex.v5.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public class OkexSetLeverageRequest {

    @JsonProperty("instId")
    String instrument;

    @JsonProperty("lever")
    String leverage;

    @JsonProperty("mgnMode")
    String marginMode;
}
