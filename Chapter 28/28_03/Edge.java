/*
 * Author: Kaden Payne
 * Date: 10/14/2020
 * 
 * Edge class for making edge objects
 */

/**
 *
 * @author kjpay
 */
public class Edge {
    int u;
    int v;
    
    public Edge(int u, int v) {
        this.u = u;
        this.v = v;
    }
    
    @Override
    public boolean equals(Object o) {
        return (u == ((Edge)o).u && v == ((Edge)o).v);
    }
}
