package ru.university.client;

import org.junit.jupiter.api.Assertions;
import ru.university.client.base.HttpClient;
import ru.university.client.base.HttpClientJdk;

import java.util.concurrent.*;

public class Test5 {
	private static final HttpClient client = new HttpClientJdk();
	private static String URL = "http://localhost:8082/api/customer/7";

	public static String runTest() {
		return client.performRequest(URL);
	}

	public static void main(String[] args) {
		final int SIZE_POOL = 1000;
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
