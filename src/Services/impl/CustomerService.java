package Services.impl;

import Services.TicketHandler;

/**
 * @author Peiqi Wang
 * @date 2022-08-14
 */
public class CustomerService extends Service implements TicketHandler {

    public CustomerService(int C_id, String C_type) {
        super(C_id, C_type);
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
