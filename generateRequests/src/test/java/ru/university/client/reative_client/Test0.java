package ru.university.client.reative_client;

import org.junit.jupiter.api.Assertions;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.concurrent.*;

public class Test0 {

	private static final WebClient client = WebClient.create();
	private static String URL = "http://localhost:8081/customer/7";

	public static String runTest() {
		WebClient.ResponseSpec responseSpec = client.get()
				.uri(URL)
				.retrieve();

		String responseBody = responseSpec.bodyToMono(String.class).block();

		return responseBody;
	}

	public static void main(String[] args) {
		final int SIZE_POOL = 100;
		Callable<String> task = () -> runTest();
		ExecutorService service = Executors.newFixedThreadPool(SIZE_POOL);

		for (int i = 0; i < SIZE_POOL; i++) {
			Future result = service.submit(task);

			try {
				Assertions.assertEquals(result.get(), "{\"id\":7,\"name\":\"testName5\"}");
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		service.shutdown();
	}
}
