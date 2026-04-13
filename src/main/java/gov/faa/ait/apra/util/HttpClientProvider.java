/*
 * Federal Aviation Administration (FAA) public work 
 * 
 * As a work of the United States Government, this project is in the 
 * public domain within the United States. Additionally, we waive copyright 
 * and related rights in the work worldwide 
 * through the Creative Commons 0 (CC0) 1.0 Universal public domain dedication
 * 
 * APRA is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 */
package gov.faa.ait.apra.util;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import java.util.concurrent.TimeUnit;

/**
 * Provides a single, lazily-initialized, thread-safe JAX-RS Client instance
 * with connect and read timeouts configured.
 */
public final class HttpClientProvider {

    private static volatile Client instance;

    private HttpClientProvider() {
        // utility class
    }

    public static Client getClient() {
        if (instance == null) {
            synchronized (HttpClientProvider.class) {
                if (instance == null) {
                    instance = ClientBuilder.newBuilder()
                            .connectTimeout(10, TimeUnit.SECONDS)
                            .readTimeout(30, TimeUnit.SECONDS)
                            .build();
                }
            }
        }
        return instance;
    }
}
