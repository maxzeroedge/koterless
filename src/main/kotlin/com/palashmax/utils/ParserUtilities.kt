package com.palashmax.utils

import com.fasterxml.jackson.databind.ObjectMapper
import com.palashmax.model.FunctionYml
import com.palashmax.model.ServerlessYml
import org.yaml.snakeyaml.Yaml
import java.io.File

class ParserUtilities {
    var ymlLocaltion: String = ""

    fun readFromFile(path: String): String {
        // Read data from fileReader
        val stringBuilder = File(path).readText(Charsets.UTF_8)
        return stringBuilder
    }

    fun parseYml(): ServerlessYml? {
        val slsFile = this.readFromFile(ymlLocaltion)
        val yamlParser = Yaml()
        val map = yamlParser.load<Map<*,*>>(slsFile) //classLoader.getResourceAsStream("serverless.yml")
        val objMapped = ObjectMapper().convertValue(map, ServerlessYml::class.java)
        objMapped.parseFunctions()
        return objMapped
    }


    fun loadFunctions(_functions: List<String>): Map<String, FunctionYml> {
        var _functionsCompiled = LinkedHashMap<String, FunctionYml>()
        for (function in _functions) {
            //${file(api/functions.yml)}
            var functionFile = function.replace("\${file(", "")
            functionFile = functionFile.substring(0, functionFile.length - 2)
            val function = this.readFromFile(functionFile)
            try {
                val functionsRead =
                    Yaml().load<Any>(this.readFromFile(functionFile)) as Map<String, FunctionYml>
                if (functionsRead.size > 0) {
                    _functionsCompiled.putAll(functionsRead)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
        return _functionsCompiled
    }
}