package org.knowm.xchange.okex.v5.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OkexClosePositionResponse {

    @JsonProperty("instId")
    String instrument;

    @JsonProperty("posSide")
    String position;

    @JsonProperty("tag")
    String orderTag;
}
