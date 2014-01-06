/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mainPackage.testerPack;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author shirai-Lab
 */
public class TestTekitouListPacker {
   List<List<Integer>> jobMachineList;
   List<Integer> machineList;

    public TestTekitouListPacker() {
        this.jobMachineList = new ArrayList<>();
        
    }
    
    protected void setArrayColumn(Integer[] column){
        machineList = new ArrayList<>();
        for (int i = 0; i < column.length; i++) {
            Integer essence = column[i];
            machineList.add(essence);
            
        }
        jobMachineList.add(machineList);
    }
    
    protected List<List<Integer>> getList(){
        return jobMachineList;
    }
    
    
    
    
    
    
}
