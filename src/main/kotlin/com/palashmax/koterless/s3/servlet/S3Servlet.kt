package com.palashmax.koterless.s3.servlet

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(
	name="S3Servlet",
	urlPatterns = ["/*"]
)
class S3Servlet: HttpServlet() {

	override fun service(req: HttpServletRequest?, resp: HttpServletResponse?) {
		super.service(req, resp)
		// TODO: Handle S3 Requests
		val out = resp?.outputStream
		out?.write("Got your thing buddy!".toByteArray())
		out?.flush()
		out?.close()
	}
}