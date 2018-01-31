package com.credits.wallet.desktop.controller;

import com.credits.wallet.desktop.App;
import com.credits.wallet.desktop.AppState;
import com.credits.wallet.desktop.utils.Convertor;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Rustem.Saidaliyev on 26.01.2018.
 */
public class Form8Controller extends Controller implements Initializable {

    @FXML
    private Label toAddress;

    @FXML
    private Label amountInCs;

    @FXML
    private Label transactionFeeValue;

    @FXML
    private Label transactionFee;

    @FXML
    private Label transactionHash;

    @FXML
    private void handleSeeOnMonitor() {
        App.showForm("/fxml/form0.fxml", "Wallet");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.toAddress.setText(AppState.toAddress);
        this.amountInCs.setText(Convertor.toString(AppState.amount) + " CS");
        this.transactionFeeValue.setText(Convertor.toString(AppState.transactionFeeValue) + " CS");
        this.transactionFee.setText(Convertor.toString(AppState.transactionFeePercent) + " %");
        this.transactionHash.setText(AppState.transactionHash);
    }
}