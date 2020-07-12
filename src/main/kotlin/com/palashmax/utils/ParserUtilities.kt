package com.palashmax.utils

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.palashmax.model.ServerlessFunction
import com.palashmax.model.ServerlessYml
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.yaml.snakeyaml.Yaml
import java.io.File

@Component
class ParserUtilities {
    @Autowired
    lateinit var ymlLocation: String

    val mapper = ObjectMapper().registerModule(KotlinModule())

    fun readFromFile(path: String): String {
        // Read data from fileReader
        val stringBuilder = File("${ymlLocation}${File.separator}${path}").readText(Charsets.UTF_8)
        return stringBuilder.toString()
    }

    fun parseYml(): ServerlessYml? {
        val slsFile = this.readFromFile("serverless.yml")
        val yamlParser = Yaml()
        val map = yamlParser.load<Map<*,*>>(slsFile) //classLoader.getResourceAsStream("serverless.yml")
        val objMapped = mapper.convertValue(map, ServerlessYml::class.java)
        objMapped.parseFunctions(this)
        return objMapped
    }


    fun loadFunctions(_functions: List<String>): Map<String, ServerlessFunction> {
        var _functionsCompiled = LinkedHashMap<String, ServerlessFunction>()
        for (function in _functions) {
            //${file(api/functions.yml)}
            var functionFile = function.replace("\${file(", "")
            functionFile = functionFile.substring(0, functionFile.length - 2)
            val functionData = this.readFromFile(functionFile)
            try {
                val functionsRead =
                    Yaml().load<Map<String, ServerlessFunction>>(functionData)
                if (functionsRead.isNotEmpty()) {
                    functionsRead.entries.stream().forEach {
                        _functionsCompiled[it.key] = mapper.convertValue(it.value, ServerlessFunction::class.java)
                    }
                    // _functionsCompiled.putAll(functionsRead)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
        return _functionsCompiled
    }
}