package com.palashmax.koterless

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@RestController
class KoterController {
    @Autowired(required = false)
    lateinit var koterlessInitializer: KoterlessInitializer

    @RequestMapping(value= ["/**"])
    fun handleRequest(@RequestParam pathVariable: Any?, @RequestBody body: Any?, request: HttpServletRequest, response: HttpServletResponse): String{
        val serverlessYml = koterlessInitializer.serverlessYml
        val requestPath = request.servletPath.substring(1)
        var output = "Hello $pathVariable ${request.servletPath}. Couldn't find a matching request"
        serverlessYml._functionsCompiled!!.values.stream().forEach {
            functionEntry -> run {
                if(!functionEntry._events.isNullOrEmpty()){
                    functionEntry._events!!.stream().forEach {
                        functionEvent -> run {
                            if (functionEvent._http != null){
                                functionEvent._http!!._path?.let {
                                    // TODO: Define route using this
                                    val params = matchUrlToRequest(requestPath, it)
                                    if( !params.isNullOrEmpty() ){
                                        // TODO: respond to this
                                        output = "Got request with ${params.values}"
                                    }
                                }
                            } else {
                                // TODO:
                            }
                        }
                    }
                }
            }
        }
        return output
    }

    fun matchUrlToRequest(requestUrl: String, urlToMatch: String) : Map<String, String>?{
        val requestUrlParts = requestUrl.split("/")
        val urlToMatchParts = urlToMatch.split("/")
        if (requestUrlParts.size != urlToMatchParts.size){
            return null;
        }
        // Loop through each part. Check if matches found for non parametric parts
        val params = mutableMapOf<String, String>()
        for (indx in 0 until urlToMatchParts.size){
            if(urlToMatchParts[indx].equals(requestUrlParts[indx])){
                continue
            } else if(urlToMatchParts[indx].matches(Regex("\\{\\w+\\}", RegexOption.IGNORE_CASE))){
                var key = urlToMatchParts[indx].replace("{", "").replace("}", "")
                params[key] = requestUrlParts[indx]
            }
        }
        return params;
    }

    // TODO: Create a responder function
}