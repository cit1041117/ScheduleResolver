/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dataArchive;

import java.util.Map;
import java.util.Objects;

/**
 *
 * @author 117
 * [データ構造]生産スケジュールにおけるマシンを表します。
 */
public class Machine {
    private Map<Integer,ProcessTime> setTimeMap;
    private String viewName;
    private String Name;

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public Machine(Map<Integer, ProcessTime> setTimeMap,String name) {
        this.setTimeMap = setTimeMap;
        this.Name = name;
    }
    
    
    
    protected ProcessTime getTime(Integer jobID){
        return setTimeMap.get(jobID);
    }
    /**
     *
     * @return
     */
    protected Map<Integer,ProcessTime> getTimeMap(){
        return this.setTimeMap;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.setTimeMap);
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
        final Machine other = (Machine) obj;
        if (!Objects.equals(this.setTimeMap, other.setTimeMap)) {
            return false;
        }
        return true;
    }
    
}
