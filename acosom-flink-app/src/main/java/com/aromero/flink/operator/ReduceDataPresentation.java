package com.aromero.flink.operator;

import com.aromero.flink.model.UserDataCombine;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.java.tuple.Tuple4;

import java.util.HashMap;

public class ReduceDataPresentation implements ReduceFunction<Tuple4<UserDataCombine, String, Integer, HashMap<String, Double>>> {

    @Override
    public Tuple4<UserDataCombine, String, Integer, HashMap<String, Double> > reduce(Tuple4<UserDataCombine, String, Integer, HashMap<String, Double> > userDataCombine,
                                                                                      Tuple4<UserDataCombine, String, Integer, HashMap<String, Double> > t1) throws Exception {
        HashMap<String, Double> userLevelMap = userDataCombine.f3;
        int actionCounter = userDataCombine.f2 + t1.f2;
        return new Tuple4<>(userDataCombine.f0, "", actionCounter, userLevelMap);
    }

    private HashMap<String, Double> processUserLevelAverage(HashMap<String, Double> userLevelMap, String region, int actionCount) {
        if (userLevelMap == null) {
            userLevelMap = new HashMap<>();
        }

        if (userLevelMap.containsKey(region)){
            Double regionValue = userLevelMap.get(region);

        }

        return null;
    }
}
