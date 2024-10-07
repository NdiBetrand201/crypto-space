package com.Trading_plaform.Trading_platform.implementations;

import com.Trading_plaform.Trading_platform.domain.OrderType;
import com.Trading_plaform.Trading_platform.models.Order;
import com.Trading_plaform.Trading_platform.models.User;
import com.Trading_plaform.Trading_platform.models.Wallet;
import com.Trading_plaform.Trading_platform.repository.WalletRepository;
import com.Trading_plaform.Trading_platform.services.WalletService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class WalletServiceImpl implements WalletService {
    private WalletRepository walletRepository;




    @Override
    public Wallet getUserWallet(User user) {
        Wallet wallet=walletRepository.findByUserId(user.getId());
        if (wallet==null){
             wallet=new Wallet();
             wallet.setUser(user);
             walletRepository.save(wallet);
        }
        return wallet;
    }

    @Override
    public Wallet addBalance(Wallet wallet, Long money) {
        BigDecimal balance=wallet.getBalance();
        BigDecimal newBalance=balance.add(BigDecimal.valueOf(money));
        wallet.setBalance(newBalance);
        return walletRepository.save(wallet) ;
    }

    @Override
    public Wallet findWalletById(Long id) throws Exception {
        Optional<Wallet> wallet=walletRepository.findById(id);
        if (wallet.isPresent()){
            return wallet.get();

        }
        throw  new Exception("Wallet not found");
    }

    @Override
    public Wallet walletToWalletTransfer(User sender, Wallet receiverWallet, Long amount) throws Exception {
        Wallet senderWallet=walletRepository.findByUserId(sender.getId());
        if (senderWallet.getBalance().compareTo(BigDecimal.valueOf(amount))<0){
            throw new Exception("Your balance is insufficient");
        }
        senderWallet.setBalance(senderWallet.getBalance().subtract(BigDecimal.valueOf(amount)));
        receiverWallet.setBalance(receiverWallet.getBalance().add(BigDecimal.valueOf(amount)));
        walletRepository.save(senderWallet);
        walletRepository.save(receiverWallet);

        return senderWallet;
    }

    @Override
    public Wallet payOrderPayment(Order order, User user) {
        Wallet wallet=getUserWallet(user);

        if (order.getOrderType().equals(OrderType.BUY)){

        }
        return null;
    }
}
