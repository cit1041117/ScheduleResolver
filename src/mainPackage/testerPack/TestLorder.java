/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mainPackage.testerPack;

import dataArchive.Job;
import dataArchive.JobsContainer;
import dataArchive.Machine;
import dataArchive.Order;
import dataArchiveCreate.ArrayConvertionJob;
import dataArchiveCreate.I_convertionToJobData;
import java.util.ArrayList;
import java.util.List;
import result.newpackage.I_AnswerReportContainer;
import schejulingModel.SchejulingModel;
import schejulingModel.Solutions;

/**
 * テスト用のクラスです。弄るべきなのはtestBoardメソッド内だけです。
 *
 * @author 117
 */
public class TestLorder {

    TestTekitouListPacker packer;
    I_AnswerReportContainer answer;
    Solutions member;

    public TestLorder() {
        this.packer = new TestTekitouListPacker();
    }

    public JobsContainer testBoard() {
        //I_convertionToJobData aaa = new ArrayConvertionJob();
        //I_SchejulingModel ooo = new SchejulingModel(); 

        /*テストケース１⇒SPTは正しい
         *         Integer jobTotal = 4;
         Integer machineTotal = 3;


         Integer[] job1 = {5, 5, 2};
         listpack(job1);
         Integer[] job2 = {2, 6, 4};
         listpack(job2);
         Integer[] job3 = {4, 3, 4};
         listpack(job3);
         Integer[] job4 = {2, 5, 4};
         listpack(job4);

         List<List<Integer>> jobMachineTimeList = packer.getList();

         resetPacker();

         Integer[] jobo1 = {1, 2, 3};
         listpack(jobo1);
         Integer[] jobo2 = {1, 3, 2};
         listpack(jobo2);
         Integer[] jobo3 = {2, 1, 3};
         listpack(jobo3);
         Integer[] jobo4 = {3, 2, 1};
         listpack(jobo4);

         List<List<Integer>> jobMachineOrderList = packer.getList();


         Integer[] nouki = {20, 20, 20, 20};

         */


        I_convertionToJobData convertion;
        JobsContainer container = null;


        //例題の内容を構築してみようArrayConvertionJobのテスト。

        //テストケース２→MWKRとSLACKの動作確認
        member = Solutions.Dispatch_MWKR;

        Integer jobTotal = 4;
        Integer machineTotal = 3;

        List<String> jobNames = new ArrayList<>();
        jobNames.add("a");
        jobNames.add("b");
        jobNames.add("シー");
        jobNames.add("たかし");
        List<String> machineNames = new ArrayList<>();
        machineNames.add("ける");
        machineNames.add("なぐる");
        machineNames.add("暴力");
        

        Integer[] job1 = {140, 40, 80};
        listpack(job1);
        Integer[] job2 = {40, 100, 100};
        listpack(job2);
        Integer[] job3 = {40, 60, 160};
        listpack(job3);
        Integer[] job4 = {80, 100, 40};
        listpack(job4);

        List<List<Integer>> jobMachineTimeList = packer.getList();

        resetPacker();

        Integer[] jobo1 = {1, 2, 3};
        listpack(jobo1);
        Integer[] jobo2 = {1, 3, 2};
        listpack(jobo2);
        Integer[] jobo3 = {3, 1, 2};
        listpack(jobo3);
        Integer[] jobo4 = {2, 1, 3};
        listpack(jobo4);

        List<List<Integer>> jobMachineOrderList = packer.getList();


        Integer[] nouki = {520, 480, 500, 420};


        List<Integer> noukiList = new ArrayList<>();
        for (int i = 0; i < nouki.length; i++) {
            Integer atai = nouki[i];
            noukiList.add(atai);
        }


        convertion = new ArrayConvertionJob(jobTotal, machineTotal, jobMachineTimeList, jobMachineOrderList, noukiList,jobNames,machineNames);

        if (convertion.checkMemberRight()) {
            container = convertion.getJobContainer();
        } else {
            assert false : convertion.getReasonCheckfalse();
        }


        System.out.println(convertion.checkMemberRight());
        System.out.println(convertion.getReasonCheckfalse());

        return container;

    }

    public void testMethod() {

        SchejulingModel schejulingModel;
        JobsContainer container = testBoard();



        schejulingModel = new SchejulingModel();
        Solutions[] enumList = schejulingModel.getSolutionEnum();

        System.out.println("-------解法一覧------");
        //Solutionリストを全表示します。
        for (Solutions solutions : enumList) {
            System.out.println(solutions);
        }
        System.out.println("");



        if (schejulingModel.checkAbleSolution(member, container)) {

            answer = schejulingModel.Excecute(container, member);
            
            
            System.out.println("");
            System.out.print("ジョブ:");
            for (Job job : answer.getJobList()) {
                System.out.print(job.getViewName()+",");
            }
            
            System.out.println("");
            System.out.print("マシン:");
            for (Machine machine : answer.getMachineList()) {
                System.out.print(machine.getViewName()+",");
            }
            
            System.out.println("");
            
            System.out.println("解法:" + member.toString());
            System.out.println(member.getExplain());

            System.out.println("");
            System.out.println("最終リードタイム" + answer.getReadTime());

            expressionJobReadTime(answer);

            expressionTableJobOrder(answer);

            expressionTableJobMachine(answer);
            
            List<Order> odlist = answer.getOrderList();
            
            System.out.println("order");
            for (Order order : odlist) {
                System.out.print(order.get()+ ",");
            }
            
            List<Job> joblis = answer.getJobList();
            System.out.println("");
            System.out.println("job");
            for (Job job : joblis) {
                System.out.print(job.getViewName()+ "=name&"+ job.getID()+"=ID,");
            }
            
            List<Machine> machinelis = answer.getMachineList();
            System.out.println("");
            System.out.println("machine");
            for (Machine machine : machinelis) {
                System.out.print(machine.getViewName()+",");
            }
            
            
        } else {
            assert false : "そのデータはこの解法に適用できない";
        }













    }

    public void listpack(Integer[] column) {
        packer.setArrayColumn(column);
    }

    private void resetPacker() {
        packer = new TestTekitouListPacker();
    }

    private void expressionJobReadTime(I_AnswerReportContainer answerContainer) {
        List<Job> jobList = answerContainer.getJobList();

        System.out.println("ジョブ毎のリードタイム");
        for (int i = 0; i < jobList.size(); i++) {
            Job selectedJob = jobList.get(i);
            System.out.print(selectedJob.getViewName() + " ");

        }
        System.out.println("");

        for (int i = 0; i < jobList.size(); i++) {
            System.out.print(answerContainer.getJobReadtime(jobList.get(i)) + " ");
        }
        System.out.println("");




    }

    private void expressionTableJobOrder(I_AnswerReportContainer answerContainer) {

        List<Job> jobList = answerContainer.getJobList();
        List<Order> orderList = answerContainer.getOrderList();

        //ジョブ毎のアクション

        System.out.println("開始時間テーブル　ジョブ/順番");
        for (int i = 0; i < orderList.size(); i++) {
            Order selectedOrder = orderList.get(i);
            System.out.print("Order" + selectedOrder.get() + " ");

        }
        System.out.println("");

        for (int i = 0; i < answerContainer.getCountJob(); i++) {
            Job job = jobList.get(i);
            System.out.print(job.getViewName() + "  ");
            for (int j = 0; j < answerContainer.getCountMachine(); j++) {

                System.out.print(answerContainer.getStarttimeInteger(job, orderList.get(j)) + "  ");
            }
            System.out.println("");
            System.out.println("");
        }


    }

    private void expressionTableJobMachine(I_AnswerReportContainer answerContainer) {

        List<Job> jobList = answerContainer.getJobList();
        List<Machine> machineList = answerContainer.getMachineList();

        //ジョブ毎のアクション

        System.out.println("開始時間テーブル　ジョブ/マシン");
        for (int i = 0; i < machineList.size(); i++) {
            Machine selectedMachine = machineList.get(i);
            System.out.print(selectedMachine.getViewName() + " ");

        }
        System.out.println("");

        for (int i = 0; i < answerContainer.getCountJob(); i++) {
            Job job = jobList.get(i);
            System.out.print(job.getViewName() + "  ");
            for (int j = 0; j < answerContainer.getCountMachine(); j++) {
                System.out.print(answerContainer.getStarttimeInteger(job, machineList.get(j)) + "  ");




            }
            System.out.println("");
            System.out.println("");
        }


    }
}
