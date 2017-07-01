package com.crazy.estimation.service;

import com.crazy.estimation.bean.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by xuawai on 15/05/2017.
 */
@Service
public class FPService {

    @Autowired
    EntityService entityService;

    @Autowired
    TransactionService transactionService;

    public int calculateUFP(String id){


        //统计数据功能的UFP
        List<Entity> entities = entityService.getAllEntities(id);
        int dataFunctionUFP = 0;
        //统计每一个逻辑文件的UFP
        for(int i=0; i<entities.size(); i++){
            Entity entity = entities.get(i);
            List<RET> rets = entity.getRETs();

            String logicalFileType = entity.getLogicalFileType();
            //RET
            int dataRET = rets.size();
            //DET
            int dataDET = 0;
            for(int j=0; j<dataRET; j++){
                RET ret = rets.get(j);
                String RETField = ret.getRETField();
                int count = RETField.split("\\s+").length;
                dataDET += count;
            }

            //计算复杂度以及UFP
            dataFunctionUFP += UFP4DataFunction(dataRET, dataDET, logicalFileType);
            System.out.println("data ufp：" + dataFunctionUFP);
        }


        //统计事务功能的UFP
        List<Transaction> transactions = transactionService.getAllTransactions(id);
        int transcationFunctionUFP = 0;
        for(Transaction transaction : transactions){
            Map<String, Set<String>> fileMap = new HashMap<String, Set<String>>();
            List<Step> steps = transaction.getSteps();

            //统计FTR以及DET
            for(Step step : steps){
                List<ConcerningDataSet> concerningDataSets = step.getConcerningDataSets();

                for(ConcerningDataSet concerningDataSet : concerningDataSets){
                    String logicalFileName = concerningDataSet.getLogicalFileName();
                    String logicalFieldName = concerningDataSet.getLogicalFieldName();
                    String[] logicalFieldNames = logicalFieldName.split("\\s+");

                    //判断是否已经有name对应的键值对
                    if(fileMap.get(logicalFileName) == null){
                        Set<String> set = new HashSet<String>();
                        fileMap.put(logicalFileName, set);
                    }
                    Set<String> fieldSet = fileMap.get(logicalFileName);

                    for(String field: logicalFieldNames){
                        fieldSet.add(field);
                    }
                }
            }
            int countRepeatField = Integer.parseInt(transaction.getCountRepeatField());
            int regulationOfSameLogic = Integer.parseInt(transaction.getRegulationOfSameLogic());
            int regulationOfReturningStatus = Integer.parseInt(transaction.getRegulationOfReturningStatus());



            //统计复杂度以及UFP
            //FTR
            int transactionFTR = fileMap.size();
            //DET
            int transactionDET = 0;
            for(Map.Entry<String,Set<String>> entry: fileMap.entrySet()){
                transactionDET  += entry.getValue().size();
            }
            transactionDET -= countRepeatField;
            transactionDET += regulationOfSameLogic;
            transactionDET += regulationOfReturningStatus;
            //transaction type
            String transcationType = transaction.getTransactionType();

            //计算复杂度以及UFP
            transcationFunctionUFP += UFP4TransactionFunction(transactionFTR, transactionDET, transcationType);
            System.out.println(transactionDET+"transactionname:"+transaction.getTransactionName()+"transaction ufp:" + transcationFunctionUFP);
        }


        int UFP = dataFunctionUFP + transcationFunctionUFP;
        System.out.println("ufp:"+UFP);
        return UFP;


    }


    private int UFP4DataFunction(int dataRET, int dataDET, String logicalFileType){
        if(logicalFileType.equals("ILF")){
            if(dataRET == 1){
                return UFPWithDataComplexity(7, 7, 10, dataDET);
            }else if(dataRET>=1 && dataRET<=5){
                return UFPWithDataComplexity(7, 10, 15, dataDET);
            }else if(dataRET>=6){
                return UFPWithDataComplexity(10, 15, 15, dataDET);
            }

        }else if(logicalFileType.equals("EIF")){
            if(dataRET == 1){
                return UFPWithDataComplexity(5, 5, 7, dataDET);
            }else if(dataRET>=1 && dataRET<=5){
                return UFPWithDataComplexity(5, 7, 10, dataDET);
            }else if(dataRET>=6){
                return UFPWithDataComplexity(7, 10, 10, dataDET);
            }
        }
        return 0;
    }

    private int UFPWithDataComplexity(int level1, int level2, int level3, int dataDET){
        if(dataDET>=1 && dataDET<=19){
            return level1;
        }else if(dataDET>=20 && dataDET<=50){
            return level2;
        }else if(dataDET>=51){
            return level3;
        }
        //不太确定为什么这里要加return，不加会报错。但是每个条件都已经return了。
        return 0;
    }

    private int UFP4TransactionFunction(int transactionFTR, int transactionDET, String transcationType){
        if(transcationType.equals("EI")){
            if(transactionFTR>=0 && transactionFTR<=1){
                return UFPWithEITransactionComplexity(3, 3, 4, transactionDET);
            }else if(transactionFTR==2){
                return UFPWithEITransactionComplexity(3, 4, 6, transactionDET);
            }else if(transactionFTR>=3){
                return UFPWithEITransactionComplexity(4, 6, 6, transactionDET);
            }

        }else if(transcationType.equals("EO")){
            if(transactionFTR>=0 && transactionFTR<=1){
                return UFPWithEOEQTransactionComplexity(4, 4, 5, transactionDET);
            }else if(transactionFTR>=2 && transactionFTR<=3){
                return UFPWithEOEQTransactionComplexity(4, 5, 7, transactionDET);
            }else if(transactionFTR>=4){
                return UFPWithEOEQTransactionComplexity(5, 7, 7, transactionDET);
            }
        }else if(transcationType.equals("EQ")){
            if(transactionFTR>=0 && transactionFTR<=1){
                return UFPWithEOEQTransactionComplexity(3, 3, 4, transactionDET);
            }else if(transactionFTR>=2 && transactionFTR<=3){
                return UFPWithEOEQTransactionComplexity(3, 4, 6, transactionDET);
            }else if(transactionFTR>=4){
                return UFPWithEOEQTransactionComplexity(4, 6, 6, transactionDET);
            }
        }
        return 0;
    }

    private int UFPWithEITransactionComplexity(int level1, int level2, int level3, int transactionDET){
        if(transactionDET>=1 && transactionDET<=4){
            return level1;
        }else if(transactionDET>=5 && transactionDET<=15){
            return level2;
        }else if(transactionDET>=16){
            return level3;
        }

        return 0;
    }

    private int UFPWithEOEQTransactionComplexity(int level1, int level2, int level3, int transactionDET){
        if(transactionDET>=1 && transactionDET<=5){
            return level1;
        }else if(transactionDET>=6 && transactionDET<=19){
            return level2;
        }else if(transactionDET>=20){
            return level3;
        }

        return 0;
    }

}
