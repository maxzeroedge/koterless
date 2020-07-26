package com.palashmax.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import org.yaml.snakeyaml.constructor.Constructor

@JsonIgnoreProperties(ignoreUnknown = true)
data class ServerlessProvider(

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
        var _environment: Map<String, String>? = null,

        /*
        *  Use a custom name for the CloudFormation stack
        * */
        @JsonProperty("stackName")
        var _stackName: String? = null,

        /*
        * Use a custom name for the API Gateway API
        * */
        @JsonProperty("apiName")
        var _apiName: String? = null,

        /*
        * Use a custom name for the websockets API
        * */
        @JsonProperty("websocketsApiName")
        var _websocketsApiName: String? = null,

        /*
        *  Use a custom name for the websockets API
        * */
        @JsonProperty("websocketsApiRouteSelectionExpression")
        var _websocketsApiRouteSelectionExpression: String? = null,

        /*
        * The default profile to use with this service
        * */
        @JsonProperty("profile")
        var _profile: String? = null,

        /*
        * Overwrite the default memory size. Default is 1024
        * */
        @JsonProperty("memorySize")
        var _memorySize: Long? = null,

        /*
        * optional, Overwrite the default reserved concurrency limit.
        * By default, AWS uses account concurrency limit
        * */
        @JsonProperty("reservedConcurrency")
        var _reservedConcurrency: Int? = null,

        /*
        * The default is 6 seconds. Note: API Gateway current maximum is 30 seconds
        * */
        @JsonProperty("timeout")
        var _timeout: Int? = 6,

        /*
        * Set the default RetentionInDays for a CloudWatch LogGroup
        * */
        @JsonProperty("logRetentionInDays")
        var _logRetentionInDays: Int? = 14

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