package com.palashmax.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class ResourceYml(
	@JsonProperty("Resources")
	var _Resources: List<*>
)