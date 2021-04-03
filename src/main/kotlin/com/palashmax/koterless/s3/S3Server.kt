package com.palashmax.koterless.s3

import org.apache.catalina.startup.Tomcat
import org.apache.catalina.webresources.DirResourceSet
import org.apache.catalina.webresources.StandardRoot
import java.io.File

class S3Server(port: Int = 4569) {
	private val port = port
	private val docBase = listOf("src", "main", "kotlin", "com", "palashmax", "s3", "servlet").joinToString(File.separator)

	fun startServer(){
		val tomcat = Tomcat()
		tomcat.setPort(port)
		val appCtx = tomcat.addWebapp("/", File(docBase).absolutePath)
		val additionalWebInfClasses = File("target/classes/s3")
		val resources = StandardRoot(appCtx)
		resources.addPreResources(DirResourceSet(resources, "/WEB-INF/classes", additionalWebInfClasses.absolutePath, "/"))
		appCtx.resources = resources

		tomcat.start()
		tomcat.server.await()
	}
}