package edu.ap.spring.controller;


import edu.ap.spring.service.Block;
import edu.ap.spring.service.BlockChain;
import edu.ap.spring.service.Wallet;
import edu.ap.spring.transaction.Transaction;
import edu.ap.spring.transaction.TransactionForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.xml.ws.RequestWrapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Controller
public class BlockChainController {

    @Autowired
    private BlockChain bChain;
    @Autowired
    private Wallet coinbase, walletA, walletB;
    private Transaction genesisTransaction;

    private Map<String, Wallet> wallets;

    public BlockChainController() {
        this.wallets = new HashMap<String, Wallet>();
    }

    @PostConstruct
    public void setupWallet() {
            bChain.setSecurity();
            coinbase.generateKeyPair();
            walletA.generateKeyPair();
            walletB.generateKeyPair();
            wallets.put("walletA", walletA);
            wallets.put("walletB", walletB);
            wallets.put("coinbase", coinbase);

            //create genesis transaction, which sends 100 coins to walletA:
            genesisTransaction = new Transaction(coinbase.getPublicKey(), walletA.getPublicKey(), 100f);
            genesisTransaction.generateSignature(coinbase.getPrivateKey());	 // manually sign the genesis transaction
            genesisTransaction.transactionId = "0"; // manually set the transaction id

            //creating and Mining Genesis block
            Block genesis = new Block();
            genesis.setPreviousHash("0");
            genesis.addTransaction(genesisTransaction, bChain);
            bChain.addBlock(genesis);
        }


    @RequestMapping(path="/balance/{name}", method = RequestMethod.GET)
    public ModelAndView getBalance(@PathVariable("name") String name) {
        Wallet wallet = wallets.get(name);
        if (wallet != null) {
            return new ModelAndView("balanceView", "balance", wallet.getBalance());
        }
        else {
            return new ModelAndView("balanceView", "error", "an error has occured");
        }
    }

    @RequestMapping(path="/transaction", method = RequestMethod.GET)
    public ModelAndView getTransactionForm() {
        TransactionForm form = new TransactionForm();
        Set<String> keys = wallets.keySet();
        if(keys.size() != 0) for(Object o: keys) {
            form.addToWalletList(o.toString());
        }
        else {
            return new ModelAndView("error", "error", "an error has occured");
        }

        return new ModelAndView("transactionForm", "transactionForm", form);
    }

    @RequestMapping(path="/transaction", method = RequestMethod.POST)
    public ModelAndView createTransaction(@ModelAttribute("transactionForm") TransactionForm form) {

        Wallet wallet1 = wallets.get(form.selectedWallet1);
        Wallet wallet2 = wallets.get(form.selectedWallet2);

        Transaction transaction = new Transaction(wallet1.getPublicKey(), wallet2.getPublicKey(), (float)form.inputAmount);
        transaction.generateSignature(coinbase.getPrivateKey());

        Block block = new Block();
        block.addTransaction(transaction, bChain);

        return new ModelAndView("transactionForm");
    }

}
