package com.credits.wallet.desktop.thread;

import com.credits.common.exception.CreditsCommonException;
import com.credits.common.utils.Converter;
import com.credits.leveldb.client.exception.CreditsNodeException;
import com.credits.leveldb.client.exception.LevelDbClientException;
import com.credits.wallet.desktop.AppState;
import com.credits.wallet.desktop.utils.FormUtils;
import javafx.scene.control.Label;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

public class GetBalanceUpdater implements Runnable {
    private static final String ERR_GETTING_BALANCE = "Error getting balance";

    private static Logger LOGGER = LoggerFactory.getLogger(GetBalanceUpdater.class);

    private String coin;
    private Label label;

    public GetBalanceUpdater(String coin, Label label) {
        this.coin = coin;
        this.label = label;
    }

    @Override
    public void run() {
        try {
            BigDecimal balance = AppState.apiClient.getBalance(Converter.decodeFromBASE58(AppState.account), (byte)1);
            AppState.balance = balance;
            label.setText(Converter.toString(balance));
        } catch (LevelDbClientException e) {
            label.setText("");
            LOGGER.error(AppState.NODE_ERROR + ": LevelDbClientException" + e.toString(), e);
            FormUtils.showError(AppState.NODE_ERROR + ": "+e.getMessage());
        } catch (CreditsNodeException e) {
            label.setText("");
            LOGGER.error(AppState.NODE_ERROR + ": CreditsNodeException " + e.toString(), e);
            FormUtils.showError(AppState.NODE_ERROR + ": "+e.getMessage());
        } catch (CreditsCommonException e) {
            label.setText("");
            LOGGER.error(AppState.NODE_ERROR + ": CreditsCommonException" + e.toString(), e);
            FormUtils.showError(AppState.NODE_ERROR + ": "+e.getMessage());
        }
    }
}
