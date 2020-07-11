package com.palashmax.koterless

import com.palashmax.utils.ParserUtilities
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.core.io.ClassPathResource
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintStream
import java.lang.ProcessBuilder.Redirect
import java.util.*


@SpringBootApplication
class KoterlessApplication

fun main(args: Array<String>) {

	val parser = ParserUtilities()
	// parser.ymlLocaltion = ClassPathResource("test_ymls/serverless.yml").path
            //"/media/pc/Store/Projects/kotlin/koterless/serverless.yml" //""/media/pc/Store/Projects/office/sls/print_import_export/serverless.yml"

    if(args.size > 0){
        println(args.forEach { println(it.toString()) })
        parser.ymlLocaltion = args[0]
    } else {
        // println(System.getProperty("user.dir"))
        parser.ymlLocaltion = ClassPathResource("test_ymls").file.absolutePath //"${System.getProperty("user.dir")}/serverless.yml"
    }
    //println("Current Working Directory: ${System.getProperty("user.dir")}")
	val serverlessYml = parser.parseYml()
    serverlessYml!!._functionsCompiled!!.values.stream().forEach {
        functionEntry -> run {
            if(!functionEntry._events.isNullOrEmpty()){
                functionEntry._events!!.stream().forEach {
                    functionEvent -> run {
                        functionEvent._http!!._path?.let {
                            // TODO: Define route using this
                            println(it)
                        }
                    }
                }
            }
        }
    }
	runApplication<KoterlessApplication>(*args)
}

@Bean
fun commandLineRunner(ctx: ApplicationContext) : CommandLineRunner {
    return CommandLineRunner {
        args -> run {
            val beanNames = ctx.beanDefinitionNames
            Arrays.sort(beanNames)
            for (beanName in beanNames) {
                println(beanName)
            }
            println(args)
        }
    }
}
