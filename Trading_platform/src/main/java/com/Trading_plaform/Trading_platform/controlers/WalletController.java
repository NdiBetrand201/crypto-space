package com.Trading_plaform.Trading_platform.controlers;

import com.Trading_plaform.Trading_platform.models.Order;
import com.Trading_plaform.Trading_platform.models.User;
import com.Trading_plaform.Trading_platform.models.Wallet;
import com.Trading_plaform.Trading_platform.models.WalletTransaction;
import com.Trading_plaform.Trading_platform.services.OrderService;
import com.Trading_plaform.Trading_platform.services.UserService;
import com.Trading_plaform.Trading_platform.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {
    @Autowired
    private WalletService walletService;

    @Autowired
    private UserService userService;

    @Autowired
    OrderService orderService;

    public ResponseEntity<Wallet> getUserWallet(@RequestHeader("Authorization") String jwt) throws Exception {
        User user=userService.findUserProfileByJwt(jwt);
        Wallet wallet=walletService.getUserWallet(user);

        return new ResponseEntity<>(wallet, HttpStatus.OK);
    }



    @PutMapping("/api/wallet/${walletId}/transfer")
    public ResponseEntity<Wallet> walletToWalletTransfer(@RequestHeader("Authorization" )String jwt,
                                                         @PathVariable Long walletId,
                                                         @RequestBody WalletTransaction request
                                                         )throws Exception{
        User senderuser= userService.findUserProfileByJwt(jwt);
        Wallet receiverWallet=walletService.findWalletById(walletId);
        Wallet wallet=walletService.walletToWalletTransfer(senderuser,receiverWallet, request.getAmount());


 return new ResponseEntity<Wallet>(wallet,HttpStatus.ACCEPTED);
    }

    @PutMapping("/api/wallet/order/{orderId}/pay")
    public  ResponseEntity<Wallet>payOrderPayment(
            @RequestHeader("Authorization") String jwt,
            @PathVariable Long orderId
    ) throws Exception {
        User user=userService.findUserProfileByJwt(jwt);
        Order order=orderService.getOrderById(orderId);

        Wallet wallet =walletService.payOrderPayment(order,user);
        return  new ResponseEntity<Wallet>(wallet,HttpStatus.ACCEPTED);
    }



}
