package com.deviceregistry;

/**
 * 
 * @author Kazim ULUSOY
 *
 */
public class WebApiConstant {
	/**
     * The REST API version for all the endpoints from this project.
     */
    public static final String REST_VERSION = "v1";

    /**
     * The prefix for all the URL for from this project.
     */
    public static final String BASE_URL = "/api/" + REST_VERSION;

    /**
     * The prefix for all the URL for the WoS REST.
     */
    public static final String DEVICE_REGISTRY_URL = BASE_URL + "/deviceregistry";


    /**
     * The prefix for all resources related URL for this project.
     */
    public static final String RESOURCE_URL = DEVICE_REGISTRY_URL + "/resources";


    /**
     * Do not let anyone to instantiate this class.
     */
    private WebApiConstant() {
        // UNIMPLEMENTED
    }
}
