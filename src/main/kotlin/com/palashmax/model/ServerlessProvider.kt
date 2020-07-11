package com.palashmax.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import org.yaml.snakeyaml.constructor.Constructor

@JsonIgnoreProperties(ignoreUnknown = true)
data class ServerlessProvider (

    /**
     * Name of the service provider
     */
    @JsonProperty("name")
    var _name: String? = null,

    /**
     * Runtime to be used
     */
    @JsonProperty("runtime")
    var _runtime: String? = null,

    /**
     * Environment type
     */
    @JsonProperty("stage")
    var _stage: String? = null,

    /**
     * Region under aws
     */
    @JsonProperty("region")
    var _region: String? = null,

    /**
     * Environment variables to be set
     */
    @JsonProperty("environment")
    var _environment: Map<String, String>? = null

    /**
     * Identity Access Management Role Statements
     */
    /*@JsonProperty("iamRoleStatements")
    public IamRoleStatements _iamRoleStatements;*/
) /*{
    companion object {
        @JvmStatic
        fun ymlConstructor(): Constructor {
            val constructor = Constructor(ServerlessProvider::class.java)
            return constructor
        }
    }
}*/
    // TODO: Define
    /*@JsonIgnoreProperties(ignoreUnknown = true)
    data class IamRoleStatements(){}*/