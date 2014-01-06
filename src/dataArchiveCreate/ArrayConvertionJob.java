/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dataArchiveCreate;

import dataArchive.Job;
import dataArchive.JobContainer;
import dataArchive.JobsContainer;
import dataArchive.Machine;
import dataArchive.Order;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author 117
 */
public class ArrayConvertionJob implements I_convertionToJobData {

    private Integer jobTotal;
    private Integer machineTotal;
    private List<List<Integer>> jobMachineProcessTimeList;
    private List<List<Integer>> jobMachineOrderList;
    private List<Integer> jobTimeOfDeliveryList;
    private List<String> jobNamesList;
    private List<String> machineNamesList;
    private String reasonCheckfalse;
    

    @Override
    public String getReasonCheckfalse() {
        return reasonCheckfalse;
    }

    public ArrayConvertionJob(Integer jobTotal, Integer machineTotal, List<List<Integer>> jobMachineProcessTimeList, List<List<Integer>> jobMachineOrderList, List<Integer> jobTimeOfDeliveryList,List<String> jobNameList,List<String> machineNameList) {
        this.jobTotal = jobTotal;
        this.machineTotal = machineTotal;
        this.jobMachineProcessTimeList = jobMachineProcessTimeList;
        this.jobMachineOrderList = jobMachineOrderList;
        this.jobTimeOfDeliveryList = jobTimeOfDeliveryList;
        this.reasonCheckfalse = "まだ未チェックです。";
        this.jobNamesList = jobNameList;
        this.machineNamesList = machineNameList;

    }

    @Override
    public JobsContainer getJobContainer() {
        List<Job> allJobList;
        List<Machine> allMachineList;


        {//ジョブを必要な数（jobTotal)だけ作り、リストをもらう。
            JobCreater jobCreater = new JobCreater();

            for (int i = 0; i < jobTotal; i++) {
                jobCreater.JobCreate(jobTimeOfDeliveryList.get(i),jobNamesList.get(i));
            }

            allJobList = jobCreater.returnJobList();

            jobCreater = null;
        }


        {//マシン（ジョブ/プロセスタイムハッシュマップが完成している）が全て入ったリストを受け取る。


            MachineCreater machineCreater;
            machineCreater = new MachineCreater(allJobList, jobTotal, machineTotal, jobMachineProcessTimeList,machineNamesList);
            


            allMachineList = machineCreater.returnMachineList();

            machineCreater = null;
        }

        {//全てのジョブに順番/マシンハッシュマップをセットする。
            

            //すべてのジョブに対して、ハッシュマップ生成→登録を行う。 
            for (int jobIndex = 0; jobIndex < jobTotal; jobIndex++) {

                HashMap<Order, Machine> orderMachineMap = new HashMap<>();


                //すべてのマシンの分のOrder情報をOrderクラスに変換して、ハッシュマップに登録。
                for (int machineIndex = 0; machineIndex < machineTotal; machineIndex++) {
                    List<Integer> machineOrderList = jobMachineOrderList.get(jobIndex);

                    Integer orderInteger = machineOrderList.get(machineIndex);

                    Order order = new Order(orderInteger);

                    Machine machine = allMachineList.get(machineIndex);


                    orderMachineMap.put(order, machine);


                }
                Job jobSelected = allJobList.get(jobIndex);
                jobSelected.setOrderMachineMap(orderMachineMap);
            }
        }
        
        JobContainer jobContainer = new JobContainer(allJobList,allMachineList);
        return jobContainer;
    }

    @Override
    public boolean checkMemberRight() {
        Boolean returnFlag = true;
        reasonCheckfalse = "true、正常です！";

        //リストの要素数のチェック

        if (jobMachineOrderList.size() == jobTotal) {

            for (List<Integer> list : jobMachineOrderList) {
                if (list.size() == machineTotal) {
                } else {
                    returnFlag = false;
                    reasonCheckfalse = "ジョブ/順番リストのマシンの数が異なる";
                    return returnFlag;
                }
            }

        } else {
            returnFlag = false;
            reasonCheckfalse = "ジョブ/順番リストのジョブの数が異なる";
            return returnFlag;
        }


        if (jobMachineProcessTimeList.size() == jobTotal) {

            for (List<Integer> list : jobMachineProcessTimeList) {
                if (list.size() == machineTotal) {
                } else {
                    returnFlag = false;
                    reasonCheckfalse = "マシン/時間リストのマシンの数が異なる";
                    return returnFlag;
                }
                
                
            }

        } else {
            returnFlag = false;
            reasonCheckfalse = "マシン/時間リストのジョブの数が異なる";
            return returnFlag;
        }

        if (jobTimeOfDeliveryList.size() != jobTotal) {
            returnFlag = false;
            reasonCheckfalse = "納期の数がジョブと対応していない（数が異なる）";
            return returnFlag;
        }

        for (Integer jobTimeOfDelivery : jobTimeOfDeliveryList) {
            if (jobTimeOfDelivery < 0) {
                returnFlag = false;
                reasonCheckfalse = "ジョブの納期に不正な値がある";
                return returnFlag;
            }
        }


        for (List<Integer> list : jobMachineProcessTimeList) {
            for (Integer ProcessTime : list) {
                if (ProcessTime < 0) {
                    returnFlag = false;
                    reasonCheckfalse = "処理時間の値に不正な要素がある";
                    return returnFlag;
                }
            }
        }

        Set<Integer> checkerOrderSet;

        for (List<Integer> list : jobMachineOrderList) {
            checkerOrderSet = new HashSet<>();
            for (Integer Order : list) {


                if (Order <= 0) {
                    returnFlag = false;
                    reasonCheckfalse = "順番の値に不正な要素がある";
                    return returnFlag;
                }
                
                if (Order > machineTotal) {
                    returnFlag = false;
                    reasonCheckfalse = "順番の値に不正な要素がある（順番を飛ばしてしまっている）";
                    return returnFlag;
                }

                checkerOrderSet.add(Order);

            }
            if (checkerOrderSet.size() != machineTotal) {
                returnFlag = false;
                reasonCheckfalse = "順番にダブりがある";
                return returnFlag;
            }


        }
        
        if (!jobTotal.equals(jobNamesList.size())) {
            returnFlag = false;
                reasonCheckfalse = "ジョブの名前の数がジョブの数と違う";
                return returnFlag;
        }
        
        if (!machineTotal.equals(machineNamesList.size())) {
            returnFlag = false;
                reasonCheckfalse = "マシンの名前の数がマシンの数と違う";
                return returnFlag;
        }
        
        
        return returnFlag;
    }
}
