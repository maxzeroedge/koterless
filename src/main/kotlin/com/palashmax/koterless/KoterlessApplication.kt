package com.palashmax.koterless

import com.palashmax.model.ServerlessYml
import com.palashmax.utils.ParserUtilities
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component


@SpringBootApplication
open class KoterlessApplication

fun main(args: Array<String>) {
	runApplication<KoterlessApplication>(*args)
}

@Component
class KoterlessInitializer {
    lateinit var serverlessYml: ServerlessYml
    lateinit var parserUtilities: ParserUtilities
    lateinit var ymlLocation: String
}

@Component
class CommandLineAppStartupRunner : CommandLineRunner {

    @Autowired
    lateinit var koterlessInitializer: KoterlessInitializer

    @Throws(Exception::class)
    override fun run(vararg args: String) {
        LOG.info("Increment counter")
        counter++


        val parser = ParserUtilities()
        // parser.ymlLocaltion = ClassPathResource("test_ymls/serverless.yml").path
        //"/media/pc/Store/Projects/kotlin/koterless/serverless.yml" //""/media/pc/Store/Projects/office/sls/print_import_export/serverless.yml"

        println("args: ")
        println(args.forEach { println(it.toString()) })
        if(args.size > 0){
            println(args.forEach { println(it.toString()) })
            parser.ymlLocation = args[0]
        } else {
            // println(System.getProperty("user.dir"))
            parser.ymlLocation = ClassPathResource("test_ymls").file.absolutePath //"${System.getProperty("user.dir")}/serverless.yml"
        }
        //println("Current Working Directory: ${System.getProperty("user.dir")}")
        val serverlessYml = parser.parseYml()
        koterlessInitializer.serverlessYml = serverlessYml!!
        koterlessInitializer.parserUtilities = parser
        koterlessInitializer.ymlLocation = parser.ymlLocation
    }

    companion object {
        private val LOG: Logger = LoggerFactory.getLogger(CommandLineAppStartupRunner::class.java)
        var counter = 0
    }
}