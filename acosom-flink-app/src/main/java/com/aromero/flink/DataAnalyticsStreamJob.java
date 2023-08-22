package com.aromero.flink;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class DataAnalyticsStreamJob {

	public static void main(String[] args) throws Exception {

		final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

		// Execute program, beginning computation.
		env.execute("Flink Java API Skeleton");
	}
}
