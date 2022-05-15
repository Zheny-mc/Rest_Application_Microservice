package ru.university.client;

import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.springframework.web.reactive.function.client.WebClient;

@State(Scope.Benchmark)
public class ExecutionPlan {

	@Param({"1"})
    public int sizePool;

	WebClient client = WebClient.create();
	String URL = "http://localhost:8082/api/customer/7";

}