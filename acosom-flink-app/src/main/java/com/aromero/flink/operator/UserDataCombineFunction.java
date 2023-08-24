package com.aromero.flink.operator;

import com.aromero.flink.model.UserDataCombine;
import com.aromero.flink.model.UserInformation;
import com.aromero.flink.model.UserInteraction;
import org.apache.flink.streaming.api.functions.co.CoProcessFunction;
import org.apache.flink.util.Collector;

public class UserDataCombineFunction extends CoProcessFunction<UserInformation, UserInteraction, UserDataCombine> {


    @Override
    public void processElement1(UserInformation userInformation,
                                CoProcessFunction<UserInformation, UserInteraction, UserDataCombine>.Context ctx,
                                Collector<UserDataCombine> out) throws Exception {
        // TODO implement process
    }

    @Override
    public void processElement2(UserInteraction userInteraction,
                                CoProcessFunction<UserInformation, UserInteraction, UserDataCombine>.Context ctx,
                                Collector<UserDataCombine> out) throws Exception {
        // TODO implement process
    }
}
