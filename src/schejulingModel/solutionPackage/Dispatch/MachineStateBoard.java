/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package schejulingModel.solutionPackage.Dispatch;

import dataArchive.Machine;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 117
 */
public class MachineStateBoard {
    private Map <Machine,Boolean> stateBoard;

    public MachineStateBoard(List<Machine> machineList) {
        stateBoard = new HashMap<>();
        for (Machine machine : machineList) {
            stateBoard.put(machine, Boolean.TRUE);
        }
    }
    
    protected String getMachineState(Machine machine){
        Boolean stateBoolean= stateBoard.get(machine);
        String state = null;
        
        if(stateBoolean == true){
            state = "Free";
        }
        if(stateBoolean == false){
            state =  "Active";
        }
        return state;
    }
    
    protected void StateOff(Machine machine){
        stateBoard.put(machine, Boolean.TRUE);
        
    }
    
    protected void StateOn(Machine machine){
        stateBoard.put(machine, Boolean.FALSE);
        
    }
    
}
