package Entity;

import java.sql.Timestamp;

/**
 * @author Peiqi Wang
 * @date 2022-08-14
 */
public class Scence {//场次

    private int M_id;
    private int H_id;
    private Timestamp S_time;
//    private int S_id;

    public int getM_id() {
        return M_id;
    }

    public void setM_id(int m_id) {
        M_id = m_id;
    }

    public int getH_id() {
        return H_id;
    }

    public void setH_id(int h_id) {
        H_id = h_id;
    }

    public Timestamp getS_time() {
        return S_time;
    }

    public void setS_time(Timestamp s_time) {
        S_time = s_time;
    }

}
