package org.knowm.xchange.okex.v5.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import org.knowm.xchange.client.ResilienceRegistries;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.okex.v5.OkexAdapters;
import org.knowm.xchange.okex.v5.OkexExchange;
import org.knowm.xchange.okex.v5.dto.OkexException;
import org.knowm.xchange.okex.v5.dto.OkexResponse;
import org.knowm.xchange.okex.v5.dto.account.OkexAssetBalance;
import org.knowm.xchange.okex.v5.dto.account.OkexWalletBalance;
import org.knowm.xchange.okex.v5.dto.trade.OkexGetLeverageRequest;
import org.knowm.xchange.okex.v5.dto.trade.OkexGetLeverageResponse;
import org.knowm.xchange.okex.v5.dto.trade.OkexSetLeverageRequest;
import org.knowm.xchange.okex.v5.dto.trade.OkexSetLeverageResponse;
import org.knowm.xchange.service.account.AccountService;
import org.knowm.xchange.utils.DateUtils;

import static org.knowm.xchange.okex.v5.OkexAuthenticated.balancePath;

/** Author: Max Gao (gaamox@tutanota.com) Created: 08-06-2021 */
public class OkexAccountService extends OkexAccountServiceRaw implements AccountService {
  public OkexAccountService(OkexExchange exchange, ResilienceRegistries resilienceRegistries) {
    super(exchange, resilienceRegistries);
  }

  public AccountInfo getAccountInfo() throws IOException {
    // null to get assets (with non-zero balance), remaining balance, and available amount in the
    // account.
    OkexResponse<List<OkexWalletBalance>> tradingBalances = getWalletBalances(null);
    OkexResponse<List<OkexAssetBalance>> assetBalances = getAssetBalances(null);
    return new AccountInfo(
        OkexAdapters.adaptOkexBalances(tradingBalances.getData()),
        OkexAdapters.adaptOkexAssetBalances(assetBalances.getData()));
  }

  public OkexResponse<OkexGetLeverageResponse> getLeverageInformation(OkexGetLeverageRequest request) throws IOException {
    try {
      return decorateApiCall(
              () ->
                      okexAuthenticated.getLeverageInformation(
                              exchange.getExchangeSpecification().getApiKey(),
                              signatureCreator,
                              DateUtils.toUTCISODateString(new Date()),
                              (String)
                                      exchange
                                              .getExchangeSpecification()
                                              .getExchangeSpecificParametersItem("passphrase"),
                              (String)
                                      exchange
                                              .getExchangeSpecification()
                                              .getExchangeSpecificParametersItem("simulated"),
                              request))
              .withRateLimiter(rateLimiter(balancePath))
              .call();
    } catch (OkexException e) {
      throw handleError(e);
    }
  }

  public OkexResponse<OkexSetLeverageResponse> setLeverageInformation(OkexSetLeverageRequest request) throws OkexException, IOException {
    try {
      return decorateApiCall(
            () ->
                    okexAuthenticated.setLeverageInformation(
                            exchange.getExchangeSpecification().getApiKey(),
                            signatureCreator,
                            DateUtils.toUTCISODateString(new Date()),
                            (String)
                                    exchange
                                            .getExchangeSpecification()
                                            .getExchangeSpecificParametersItem("passphrase"),
                            (String)
                                    exchange
                                            .getExchangeSpecification()
                                            .getExchangeSpecificParametersItem("simulated"),
                            request))
            .withRateLimiter(rateLimiter(balancePath))
            .call();
    } catch (OkexException e) {
      throw handleError(e);
    }
  }
}
