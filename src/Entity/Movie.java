package Entity;

import java.sql.Timestamp;

/**
 * @author Peiqi Wang
 * @date 2022-08-14
 */
public class Movie {

    private int M_id;
    private String M_name;
    private double M_price;
    private int M_durTime;
    private Timestamp M_startTime;
    private Timestamp M_endTime;


    public int getM_id() {
        return M_id;
    }

    public void setM_id(int m_id) {
        M_id = m_id;
    }

    public String getM_name() {
        return M_name;
    }

    public void setM_name(String m_name) {
        M_name = m_name;
    }

    public double getM_price() {
        return M_price;
    }

    public void setM_price(double m_price) {
        M_price = m_price;
    }

    public int getM_durTime() {
        return M_durTime;
    }

    public void setM_durTime(int m_durTime) {
        M_durTime = m_durTime;
    }

    public Timestamp getM_startTime() {
        return M_startTime;
    }

    public void setM_startTime(Timestamp m_startTime) {
        M_startTime = m_startTime;
    }

    public Timestamp getM_endTime() {
        return M_endTime;
    }

    public void setM_endTime(Timestamp m_endTime) {
        M_endTime = m_endTime;
    }
}
