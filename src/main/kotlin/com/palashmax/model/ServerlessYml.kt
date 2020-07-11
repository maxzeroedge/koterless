package com.palashmax.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import com.palashmax.utils.ParserUtilities
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

/**
 * Base class for serverless yml file.
 * Includes service, package and frameworkVersion sections
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class ServerlessYml {
    /**
     * The name of the service
     */
    @JsonProperty("service")
    var _service: String? = null

    @JsonIgnoreProperties(ignoreUnknown = true)
    class Package {
        /**
         * Path of the artifact
         */
        @JsonProperty("artifact")
        var _artifact: String? = null
    }

    @JsonProperty("provider")
    var _provider: ProviderYml? = null

    @JsonProperty("package")
    var _package: Package? = null

    /**
     * Making it of object type, since it could be list of files, with function definition or a map
     */
    @JsonProperty("functions")
    var _functions: Any? = null
    /**
     * The compiled version of functions
     */
    var _functionsCompiled: Map<String, FunctionYml>? = null

    fun parseFunctions(){
        if (_functionsCompiled == null) {
            if (_functions is Map<*, *>) {
                _functionsCompiled = _functions as Map<String, FunctionYml>
                ObjectMapper().convertValue((_functions as Map<String, Map<*,*>>).get("usersCreate"), FunctionYml::class.java)
            } else if (_functions is List<*>) {
                // Load and compile
                _functionsCompiled = ParserUtilities().loadFunctions(_functions as List<String>)
            }
        }
    }

}