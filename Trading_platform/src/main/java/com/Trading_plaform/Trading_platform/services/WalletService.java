package com.Trading_plaform.Trading_platform.services;


import com.Trading_plaform.Trading_platform.models.Order;
import com.Trading_plaform.Trading_platform.models.User;
import com.Trading_plaform.Trading_platform.models.Wallet;

public interface WalletService {
    Wallet getUserWallet( User user);
    Wallet addBalance(Wallet wallet,Long money);
    Wallet findWalletById(Long id) throws Exception;
    Wallet walletToWalletTransfer(User sender,Wallet receiverWallet, Long amount) throws Exception;
    Wallet payOrderPayment(Order order, User user);
}
