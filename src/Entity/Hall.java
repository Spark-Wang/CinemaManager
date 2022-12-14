package Entity;

/**
 * @author Peiqi Wang
 * @date 2022-08-14
 */
public class Hall {

    private int H_id;
    private String H_name;
    private int H_capacity;
    private int H_line;
    private int H_row;

    public Hall(){
    }

    public Hall(int H_id, String H_name, int H_capacity, int H_line, int H_row){
        this.H_id = H_id;
        this.H_name = H_name;
        this.H_capacity = H_capacity;
        this.H_line = H_line;
        this.H_row = H_row;
    }

    public int getH_capacity() {
        return H_capacity;
    }

    public void setH_capacity(int h_capacity) {
        H_capacity = h_capacity;
    }

    public int getH_row() {
        return H_row;
    }

    public void setH_row(int h_row) {
        H_row = h_row;
    }

    public int getH_line() {
        return H_line;
    }

    public void setH_line(int h_line) {
        H_line = h_line;
    }

    public int getH_id() {
        return H_id;
    }

    public void setH_id(int h_id) {
        H_id = h_id;
    }

    public String getH_name() {
        return H_name;
    }

    public void setH_name(String h_name) {
        H_name = h_name;
    }

}
