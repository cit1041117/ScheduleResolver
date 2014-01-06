/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package schejulingModel.solutionPackage.Dispatch.Rules;

import dataArchive.Order;
import dataArchive.ProcessTime;
import schejulingModel.solutionPackage.Dispatch.DispatchJobWlapper;

/**
 *残り総加工時間
 * @author 117
 */
public class FunctionSumWorkTime {
    protected Integer getSumWorTime(DispatchJobWlapper job){
    
        Integer sumWorkTime = 0;
            for (Integer i = job.nextOrder().get(); i <= job.getFinalOrderInteger(); i++) {
                Order order = new Order(i);
                ProcessTime processTime = job.OrderToTime(order);

                sumWorkTime += processTime.get();
            }
            
            return sumWorkTime;
    }
    
}
