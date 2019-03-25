package edu.ap.spring.transaction;

import edu.ap.spring.service.Wallet;

import java.util.ArrayList;

public class TransactionForm {

    public TransactionForm() {
        this.transaction = new Transaction();
        this.walletList = new ArrayList<String>();
    }

    private Transaction transaction;
    private edu.ap.spring.service.Wallet wallet1;
    private Wallet wallet2;

    public String selectedWallet1;
    public String selectedWallet2;
    public String inputAmount;
    public ArrayList<String> walletList;

    public ArrayList<String> getWalletList() {
        return walletList;
    }

    public void setWalletList(ArrayList<String> walletList) {
        this.walletList = walletList;
    }

    public void addToWalletList(String name) {
        walletList.add(name);
    }

    public String getSelectedWallet1() {
        return selectedWallet1;
    }

    public void setSelectedWallet1(String selectedWallet1) {
        this.selectedWallet1 = selectedWallet1;
    }

    public String getSelectedWallet2() {
        return selectedWallet2;
    }

    public void setSelectedWallet2(String selectedWallet2) {
        this.selectedWallet2 = selectedWallet2;
    }

    public String getInputAmount() {
        return inputAmount;
    }

    public void setInputAmount(String inputAmount1) {
        this.inputAmount = inputAmount1;
    }






    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Wallet getWallet1() {
        return wallet1;
    }

    public void setWallet1(Wallet wallet1) {
        this.wallet1 = wallet1;
    }

    public Wallet getWallet2() {
        return wallet2;
    }

    public void setWallet2(Wallet wallet2) {
        this.wallet2 = wallet2;
    }





}
