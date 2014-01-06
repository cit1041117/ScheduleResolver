/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dataArchive;

import java.util.Map;
import java.util.Objects;

/**
 *
 * @author 117 [データ構造]生産スケジュールにおけるジョブを表します。 順番インデックスは1からです。
 *
 */
public class Job {

    private Map<Order, Machine> orderMachineMap;
    private Integer ID;
    private String name;
    private String viewName;
    private Integer theTimeOfDelivery;

    public Integer getTheTimeOfDelivery() {
        return theTimeOfDelivery;
    }

    public void setOrderMachineMap(Map<Order, Machine> orderMachineMap) {
        this.orderMachineMap = orderMachineMap;
       
        /*
        Map<Machine,Order> tempMap = new HashMap<>();
        
        
        Set<Order> keys = orderMachineMap.keySet();
        Collection<Machine> machines = orderMachineMap.values();
        
        for (Machine machine : machines) {
            
        }
        */
        
    }

    public Job(Integer id ,String name, String viewName, Integer theTimeOfDelivery) {
        this.ID = id;
        this.name = name;
        this.viewName = viewName;
        this.theTimeOfDelivery = theTimeOfDelivery;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    /**
     * 指定の順番のマシンを返す。
     *
     * @param order
     * @return
     */
    public Machine getMachine(Order order) {
        return orderMachineMap.get(order);
    }

    public Map<Order, Machine> getOrderMachineMap() {
        return orderMachineMap;
    }
    
    

    /**
     * このジョブの作業順番を入力する。その作業の持つ処理時間を返す。
     *
     * @param select
     * @return
     *
     *
     */
    public ProcessTime OrderToTime(Order order) {

        Machine storeMachine = orderMachineMap.get(order);

        return storeMachine.getTime(this.ID);
    }



    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.orderMachineMap);
        hash = 53 * hash + Objects.hashCode(this.name);
        return hash;
    }

    public Integer getID() {
        return ID;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Job other = (Job) obj;
        if (!Objects.equals(this.ID, other.ID)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    
}
