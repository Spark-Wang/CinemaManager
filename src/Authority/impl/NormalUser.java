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
    public CustomerService customerService = new CustomerService(C_id,C_type);

    public NormalUser(int C_id){
        this.C_id = C_id;
    }

    @Override
    public void search() {

    }

    @Override
    public void order() {

    }

    @Override
    public void refund() {

    }

    @Override
    public void changeTicket() {

    }
}
