package org.knowm.xchange.okex.v5.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OkexGetLeverageResponse {

    @JsonProperty("instId")
    String instrumentId;

    @JsonProperty("mgnMode")
    String marginMode;

    @JsonProperty("posSide")
    String positionSide;

    @JsonProperty("lever")
    String leverage;
}
