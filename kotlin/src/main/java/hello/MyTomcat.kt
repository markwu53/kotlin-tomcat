package hello

import java.io.File
import org.apache.catalina.WebResourceRoot
import org.apache.catalina.startup.Tomcat
import org.apache.catalina.webresources.DirResourceSet
import org.apache.catalina.webresources.StandardRoot

val docBase = File("src/main/webapp").absolutePath
val docBase1 = File("src/main/webapp1").absolutePath
val docBase2 = File("src/main/webapp2").absolutePath
val programBase = File("target/classes").absolutePath

fun main(args: Array<String>) {
    val tomcat = Tomcat()

    val ctx = tomcat.addWebapp("", docBase)
    ctx.resources = StandardRoot(ctx)
    ctx.resources.addPreResources(DirResourceSet(ctx.resources, "/WEB-INF/classes", programBase, "/"))

    val ctx1 = tomcat.addWebapp("/app1", docBase1)
    ctx1.resources = StandardRoot(ctx1)
    ctx1.resources.addPreResources(DirResourceSet(ctx1.resources, "/WEB-INF/classes", programBase, "/"))

    val ctx2 = tomcat.addWebapp("/app2", docBase2)
    ctx2.resources = StandardRoot(ctx2)
    ctx2.resources.addPreResources(DirResourceSet(ctx2.resources, "/WEB-INF/classes", programBase, "/"))

    tomcat.setPort(8080)
    tomcat.start()
    tomcat.server.await()
}
