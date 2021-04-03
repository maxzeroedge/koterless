package com.palashmax.model.resources

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class S3Yml (
	@JsonProperty("Type")
	var _type: String,

	@JsonProperty("Property")
	var _properties: S3PropertiesYml
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class S3PropertiesYml(
	@JsonProperty("AccessControl")
	var _AccessControl: String,

	@JsonProperty("BucketName")
	var _BucketName: String,

	@JsonProperty("CorsConfiguration")
	var _CorsConfiguration: S3CorsConfiguration
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class S3CorsConfiguration (
	@JsonProperty("CorsRules")
	var _CorsRules: S3CorsRules
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class S3CorsRules(
	@JsonProperty("AllowedMethods")
	var _AllowedMethods: List<String>,

	@JsonProperty("AllowedOrigins")
	var _AllowedOrigins: List<String>,

	@JsonProperty("AllowedHeaders")
	var _AllowedHeaders: List<String>
)