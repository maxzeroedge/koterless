package com.palashmax.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class FunctionYml: LinkedHashMap<String, Any>() {
    /**
     * Class of the function handler
     */
    @JsonProperty("handler")
    var _handler: Class<*>? = null

    /**
     * Timeout to be used for the api gateway
     */
    @JsonProperty("timeout")
    var _timeout: Int = 0

    /**
     * List of events that will trigger the lambda
     */
    @JsonProperty("events")
    var _events: List<ServerlessEvent>? = null

    @JsonIgnoreProperties(ignoreUnknown = true)
    class ServerlessEvent {

        @JsonProperty("http")
        var _http: ServerlessHttpEvent? = null

        @JsonProperty("s3")
        var _s3: ServerlessS3Event? = null
        /**
         * - http:
         * path: getUploadUrl/{type}/{orderId}/{campaignId}
         * method: post
         * cors: true
         * request:
         * parameters:
         * paths:
         * type: true
         * orderId: true
         * campaignId: true
         */
        /**
         * Http event
         *
         */
        @JsonIgnoreProperties(ignoreUnknown = true)
        class ServerlessHttpEvent {
            /**
             * URI of the lambda trigger
             */
            @JsonProperty("path")
            var _path: String? = null
            /**
             * REST method
             */
            @JsonProperty("method")
            var _method: String? = null
            /**
             * Whether to support cors
             */
            @JsonProperty("cors")
            var _cors: Boolean = false

            /**
             * Request parameters: TODO
             */
            @JsonProperty("request")
            var _request: Map<String, Any>? = null
        }

        /**
         * S3 event
         */
        @JsonIgnoreProperties(ignoreUnknown = true)
        class ServerlessS3Event {
            /**
             * Name of the target bucket
             */
            @JsonProperty("bucket")
            var _bucket: String? = null
            /**
             * Event type
             */
            @JsonProperty("event")
            var _event: S3EventEnum? = null

            enum class S3EventEnum(label: String) {
                S3ObjectCreatedAll("s3:ObjectCreated:*");

                val label: String = label

                override fun toString(): String{
                    return this.label
                }

                fun valueOf(): String{
                    return this.label
                }
            }
        }
    }
}