package com.palashmax.koterless.s3

import org.apache.catalina.startup.Tomcat
import org.apache.catalina.webresources.DirResourceSet
import org.apache.catalina.webresources.StandardRoot
import java.io.File
import javax.tools.ToolProvider

class S3Server(private val port: Int = 4569) {
	private val docBase = listOf("src", "main", "kotlin", "com", "palashmax", "s3", "servlet").joinToString(File.separator)
	private val outputBase = listOf("target", "classes").joinToString(File.separator)

	private fun compileServlet() {
		val compiler = ToolProvider.getSystemJavaCompiler()
		val servletPath = File(docBase + File.separator + "S3JavaServlet.java").absolutePath
		val outputPath = File(outputBase).absolutePath
		compiler.run(null, null, null, "-cp", System.getProperty("java.class.path"), "-d", outputPath, servletPath)
	}

	fun startServer(){
		val tomcat = Tomcat()
		tomcat.setPort(port)
		compileServlet()
		val appCtx = tomcat.addWebapp("/", File(docBase).absolutePath)
		val additionalWebInfClasses = File(listOf(outputBase, "com", "palashmax", "s3").joinToString(File.separator))
		println("========" + additionalWebInfClasses.absolutePath)
		val resources = StandardRoot(appCtx)
		resources.addPreResources(DirResourceSet(resources, "/WEB-INF/classes", additionalWebInfClasses.absolutePath, "/"))
		appCtx.resources = resources

		tomcat.start()
		tomcat.server.await()
	}
}