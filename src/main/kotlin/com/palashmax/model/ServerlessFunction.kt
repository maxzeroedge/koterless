package com.palashmax.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import org.yaml.snakeyaml.TypeDescription
import org.yaml.snakeyaml.constructor.Constructor

@JsonIgnoreProperties(ignoreUnknown = true)
data class ServerlessFunction (
    /**
     * Class of the function handler
     */
    @JsonProperty("handler")
    var _handler: Class<*>? = null,

    /**
     * Timeout to be used for the api gateway
     */
    @JsonProperty("timeout")
    var _timeout: Int = 0,

    /**
     * List of events that will trigger the lambda
     */
    @JsonProperty("events")
    var _events: List<ServerlessEvent>? = null
) {

    companion object {
        @JvmStatic
        fun ymlConstructor(): Constructor {
            val constructor = Constructor(ServerlessFunction::class.java)
            val typeDescription = TypeDescription(ServerlessFunction::class.java)
            typeDescription.addPropertyParameters("events", ServerlessEvent::class.java)
            constructor.addTypeDescription(typeDescription)
            return constructor
        }
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
data class ServerlessEvent (

    @JsonProperty("http")
    var _http: ServerlessHttpEvent? = null,

    @JsonProperty("s3")
    var _s3: ServerlessS3Event? = null
) /*{
    companion object {
        @JvmStatic
        fun ymlConstructor(): Constructor {
            val constructor = Constructor(ServerlessEvent::class.java)
            val typeDescription = TypeDescription(ServerlessEvent::class.java)
            typeDescription.addPropertyParameters("http", ServerlessHttpEvent::class.java)
            typeDescription.addPropertyParameters("s3", ServerlessS3Event::class.java)
            constructor.addTypeDescription(typeDescription)
            return constructor
        }
    }
}*/

/**
 * Http event
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
data class ServerlessHttpEvent (
    /**
     * URI of the lambda trigger
     */
    @JsonProperty("path")
    var _path: String? = null,
    /**
     * REST method
     */
    @JsonProperty("method")
    var _method: String? = null,
    /**
     * Whether to support cors
     */
    @JsonProperty("cors")
    var _cors: Boolean = false,

    /**
     * Request parameters: TODO
     */
    @JsonProperty("request")
    var _request: Map<String, Any>? = null
)

/**
 * S3 event
 */
@JsonIgnoreProperties(ignoreUnknown = true)
data class ServerlessS3Event (
    /**
     * Name of the target bucket
     */
    @JsonProperty("bucket")
    var _bucket: String? = null,
    /**
     * Event type
     */
    @JsonProperty("event")
    var _event: S3EventEnum? = null
) /*{
    companion object {
        @JvmStatic
        fun ymlConstructor(): Constructor {
            val constructor = Constructor(ServerlessS3Event::class.java)
            val typeDescription = TypeDescription(ServerlessS3Event::class.java)
            typeDescription.addPropertyParameters("event", S3EventEnum::class.java)
            constructor.addTypeDescription(typeDescription)
            return constructor
        }
    }
}*/

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