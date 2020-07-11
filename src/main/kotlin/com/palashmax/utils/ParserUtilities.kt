package com.palashmax.utils

import com.fasterxml.jackson.databind.ObjectMapper
import com.palashmax.model.FunctionYml
import com.palashmax.model.ServerlessYml
import org.yaml.snakeyaml.TypeDescription
import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.Constructor
import java.io.File

class ParserUtilities {
    var ymlLocaltion: String = ""

    fun readFromFile(path: String): String {
        // Read data from fileReader
        val stringBuilder = File("${ymlLocaltion}${File.separator}${path}").readText(Charsets.UTF_8)
        return stringBuilder.toString()
    }

    fun parseYml(): ServerlessYml? {
        val slsFile = this.readFromFile("serverless.yml")
        val yamlParser = Yaml()
        val map = yamlParser.load<Map<*,*>>(slsFile) //classLoader.getResourceAsStream("serverless.yml")
        val objMapped = ObjectMapper().convertValue(map, ServerlessYml::class.java)
        objMapped.parseFunctions(this)
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
                    Yaml().load<Map<String, FunctionYml>>(this.readFromFile(functionFile))
                if (functionsRead.isNotEmpty()) {
                    _functionsCompiled.putAll(functionsRead)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
        return _functionsCompiled
    }
}