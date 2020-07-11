package com.palashmax.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import com.palashmax.utils.ParserUtilities
import org.yaml.snakeyaml.TypeDescription
import org.yaml.snakeyaml.constructor.Constructor

/**
 * Base class for serverless yml file.
 * Includes service, package and frameworkVersion sections
 */
@JsonIgnoreProperties(ignoreUnknown = true)
data class ServerlessYml (
        /**
     * The name of the service
     */
    @JsonProperty("service")
    var _service: String? = null,

    @JsonProperty("provider")
    var _provider: ServerlessProvider? = null,

    @JsonProperty("package")
    var _package: ServerlessPackage? = null,

        /**
     * Making it of object type, since it could be list of files, with function definition or a map
     */
    @JsonProperty("functions")
    var _functions: Any? = null,
        /**
     * The compiled version of functions
     */
    var _functionsCompiled: Map<String, ServerlessFunction>? = null
) {

    fun parseFunctions(parserUtilities: ParserUtilities){
        if (_functionsCompiled == null) {
            if (_functions is Map<*, *>) {
                _functionsCompiled = _functions as Map<String, ServerlessFunction>
                ObjectMapper().convertValue((_functions as Map<String, Map<*,*>>).get("usersCreate"), ServerlessFunction::class.java)
            } else if (_functions is List<*>) {
                // Load and compile
                _functionsCompiled = parserUtilities.loadFunctions(_functions as List<String>)
            }
        }
    }
    /*companion object {
        @JvmStatic
        fun ymlConstructor(): Constructor {
            val constructor = Constructor(ServerlessYml::class.java)
            val typeDescription = TypeDescription(ServerlessYml::class.java)
            typeDescription.addPropertyParameters("provider", ServerlessProvider::class.java)
            return constructor
        }
    }*/

}



@JsonIgnoreProperties(ignoreUnknown = true)
data class ServerlessPackage (
    /**
     * Path of the artifact
     */
    @JsonProperty("artifact")
    var _artifact: String? = null
) /*{
    companion object {
        @JvmStatic
        fun ymlConstructor(): Constructor {
            val constructor = Constructor(ServerlessPackage::class.java)
            return constructor
        }
    }
}*/