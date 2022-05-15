package ru.university.client;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.concurrent.*;

public class RequestAcceptionApplication1 {

	@Benchmark
//	@OutputTimeUnit(TimeUnit.NANOSECONDS)
	@BenchmarkMode(Mode.AverageTime)
	public void testGetRequest(ExecutionPlan executionPlan) {
		manyThread(executionPlan.sizePool, executionPlan.client, executionPlan.URL);
	}

	public void manyThread(final int SIZE_POOL, WebClient client, final String URL) {
		Callable<String> task = () -> runTest(client, URL);
		ExecutorService service = Executors.newFixedThreadPool(SIZE_POOL);

		for (int i = 0; i < SIZE_POOL; i++) {
			Future result = service.submit(task);

			try {
				result.get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		service.shutdown();
	}

	public String runTest(WebClient client, String URL) {
		WebClient.ResponseSpec responseSpec = client.get()
				.uri(URL)
				.retrieve();

		String responseBody = responseSpec.bodyToMono(String.class).block();

		return responseBody;
	}

	public static void main(String... args) throws RunnerException {
		Options opt = new OptionsBuilder()
				.include(RequestAcceptionApplication1.class.getSimpleName())
				.forks(1)
				.build();
		new Runner(opt).run();
	}
}