package com.palashmax.model.resources

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class DynamoDBYml(
	@JsonProperty("Type")
	var _type: String,

	@JsonProperty("DeletionPolicy")
	var _deletionPolicy: String,

	@JsonProperty("Property")
	var _properties: DynamoDBTableYml
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class DynamoDBTableYml(
	@JsonProperty("TableName")
	var _tableName: String,
	@JsonProperty("IndexName")
	var _indexName: String,
	@JsonProperty("AttributeDefinitions")
	var _attributeDefinitions: List<DynamoDBAttributeDefinition>,
	@JsonProperty("KeySchema")
	var _keySchema: List<DynamoDBAttributeDefinition>,
	@JsonProperty("ProvisionedThroughput")
	var _provisionedThroughput: DynamoDBThroughput,
	@JsonProperty("Projection")
	var _projection: DynamoDBProjection,
	@JsonProperty("GlobalSecondaryIndexes")
	var _globalSecondaryIndexes: List<DynamoDBTableYml>
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class DynamoDBAttributeDefinition(
	@JsonProperty("AttributeName")
	var _attributeName: String,
	@JsonProperty("AttributeType")
	var AttributeType: String,
	@JsonProperty("KeyType")
	var _keyType: String
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class DynamoDBThroughput(
	@JsonProperty("ReadCapacityUnits")
	var _readCapacityUnits: Int,
	@JsonProperty("WriteCapacityUnits")
	var _writeCapacityUnits: Int
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class DynamoDBProjection(
	@JsonProperty("ProjectionType")
	var _projectionType: String
)