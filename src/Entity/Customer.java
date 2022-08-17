package Entity;

/**
 * @author Peiqi Wang
 * @date 2022-08-14
 */
public class Customer {

    private int C_id;//id primary-key
    private String C_name;
    private String C_password;
    private String C_type;

    public Customer(){

    }
    /**
     *
     * @param C_id
     * @param C_name
     * @param C_password
     * @param C_type
     */
    public Customer(int C_id, String C_name, String C_password, String C_type){
        this.C_id = C_id;
        this.C_name = C_name;
        this.C_password = C_password;
        this.C_type = C_type;
    }

    public String getC_password() {
        return C_password;
    }

    public void setC_password(String c_password) {
        C_password = c_password;
    }

    public String getC_type() {
        return C_type;
    }

    public void setC_type(String c_type) {
        C_type = c_type;
    }

    public int getC_id() {
        return C_id;
    }

    public void setC_id(int c_id) {
        C_id = c_id;
    }

    public String getC_name() {
        return C_name;
    }

    public void setC_name(String c_name) {
        C_name = c_name;
    }
}
