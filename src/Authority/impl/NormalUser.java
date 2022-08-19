package Authority.impl;

import Authority.Normal;
import Services.impl.CustomerService;

/**
 * @author Peiqi Wang
 * @date 2022-08-14
 */
public class NormalUser implements Normal {
    private int C_id;
    private String C_type = "User";
    public CustomerService customerService;

    public NormalUser(int C_id){
        this.C_id = C_id;
        this.customerService = new CustomerService(C_id,C_type);
    }

    @Override
    public void search() {
        customerService.search();
    }

    @Override
    public void order() {
        customerService.order();
    }

    @Override
    public void refund() {
        customerService.refund();
    }

    @Override
    public void changeTicket() {
        customerService.changeTicket();
    }
}
