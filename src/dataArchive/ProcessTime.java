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
public class ProcessTime {
    private Integer settime;

    public ProcessTime(Integer settime) {
        
        assert settime >= 0: "工程時間の値が不正" + settime;
        this.settime = settime;
    }

    public Integer get() {
        return settime;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.settime);
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
        final ProcessTime other = (ProcessTime) obj;
        if (!Objects.equals(this.settime, other.settime)) {
            return false;
        }
        return true;
    }
    
}
