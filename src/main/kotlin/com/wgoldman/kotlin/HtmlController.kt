package com.wgoldman.kotlin

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import java.net.URL
import java.io.File

@Controller
class HtmlController {

  @GetMapping("/")
  fun blog(model: Model): String {
    model["title"] = "Blog"
    return "blog"
  }

  @GetMapping("/configServerReport")
  fun configServerReport(model: Model): String {
    model["title"] = "Config Server Report"
	var httpProps = readHttpProperties()
    model["configServerReport"] = httpProps
    return "configServerReport"
  }
  fun readHttpProperties() : List<AppData> {
	  var appProperties = mutableListOf(AppData("","",""));
	  readHttpProperties("clientportal", "ClientPortal", appProperties)
	  readHttpProperties("RawData", appProperties)
	  readHttpProperties("B2BServices", appProperties)
	  readHttpProperties("cos", appProperties)
	  readHttpProperties("databasechangenotification", appProperties)
	  readHttpProperties("databasetester", appProperties)
	  readHttpProperties("ecoc", appProperties)
	  readHttpProperties("fileservice-web", appProperties)
	  readHttpProperties("global-template-manager", appProperties)
	  readHttpProperties("jasper-reports-web", appProperties)
	  readHttpProperties("notifications-web", appProperties)
	  readHttpProperties("pace-gateway", appProperties)
	  readHttpProperties("paceport-auto-edd", appProperties)
	  readHttpProperties("paceport-etl", appProperties)
	  readHttpProperties("pdf-merge-web", appProperties)
	  readHttpProperties("product-book-web", appProperties)
	  readHttpProperties("validation-web", appProperties)
	  readHttpProperties("workbench-tools", appProperties)
	  readHttpProperties("lims-access", appProperties)
	  return appProperties
  }
  fun readHttpProperties(app: String, appProperties: MutableList<AppData>) {
	  return readHttpProperties(app, app, appProperties)
  }
  fun readHttpProperties(app: String, appContext: String, appProperties: MutableList<AppData>) {
  	  var contextAppData = AppData(appContext, "", "")
	  appProperties.add(contextAppData)
	  getAppHttpProps(app, appContext, "local", appProperties)
	  getAppHttpProps(app, appContext, "dev", appProperties)
	  getAppHttpProps(app, appContext, "qa", appProperties)
	  getAppHttpProps(app, appContext, "staging", appProperties)
	  getAppHttpProps(app, appContext, "prod", appProperties)
  }
  fun getAppHttpProps(app: String, appContext: String, env: String, appProperties: MutableList<AppData>)  {
	  var envContextAppData = AppData("", env, "")
	  appProperties.add(envContextAppData);
	  var appProps = getAppProps(app, appContext, env);
	  getAppHttpProps(appProps, appProperties)
  }
  fun getAppHttpProps(appProps:List<String>, appProperties: MutableList<AppData>) {
	  for (appProp in appProps) {
		  if (appProp.contains("http:") && !appProp.contains("#")) {
			  appProperties.add(AppData("", "", appProp))
		  }
	  }
  }
  fun getAppProps(app: String, appContext: String, env: String) : List<String> {
	  try {
		  var fileName = "C://pace/gitsb/config-server/" + app + "/" + appContext + "-" + env + ".properties" 
		  return File(fileName).readLines()
	  } catch (e: Exception) {
		  return listOf<String>()
	  }
  }
  @PostMapping("/saveComment")
  fun saveComment(model: Model, @RequestBody comment: CommentForm): String {
	  model["title"] = "live" + comment.comment
	  return "blog"
  }
  data class CommentForm (val comment: String)
  data class AppData (val appContext: String, val appEnv: String, val httpProp: String)
}