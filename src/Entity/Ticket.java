package Entity;

import java.sql.Timestamp;

/**
 * @author Peiqi Wang
 * @date 2022-08-14
 */
public class Ticket {

//    private int S_id;//场次ID
    private int C_id;//订票用户ID
    private int M_id;//电影ID
    private int H_id;//放映厅ID
    private double price;//票价
    private int H_line;//排
    private int H_row;//列
    private Timestamp T_time;//放映时间
//    public int getS_id() {
//        return S_id;
//    }
//
//    public void setS_id(int s_id) {
//        S_id = s_id;
//    }

//    public int getU_id() {
//        return U_id;
//    }

//    public void setU_id(int u_id) {
//        U_id = u_id;
//    }
//
//    public int getM_id() {
//        return M_id;
//    }
//
//    public void setM_id(int m_id) {
//        M_id = m_id;
//    }
//
//    public int getH_id() {
//        return H_id;
//    }
//
//    public void setH_id(int h_id) {
//        H_id = h_id;
//    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getH_Line() {
        return H_line;
    }

    public void setH_Line(int h_Line) {
        H_line = h_Line;
    }

    public int getH_Row() {
        return H_row;
    }

    public void setH_Row(int h_Row) {
        H_row = h_Row;
    }
}
