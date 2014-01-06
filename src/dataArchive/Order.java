/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dataArchive;

import java.util.Objects;

/**
 *
 * @author 117
 */
public class Order {

    private Integer order;

    public Order(Integer order) {

        assert order > 0 : "順番の値が不正" + order;
        this.order = order;
    }

    public Integer get() {
        return order;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.order);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Order other = (Order) obj;
        if (!Objects.equals(this.order, other.order)) {
            return false;
        }
        return true;
    }
}
