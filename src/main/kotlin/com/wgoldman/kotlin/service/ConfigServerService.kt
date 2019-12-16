package com.wgoldman.kotlin

import org.springframework.stereotype.Service
import org.springframework.ui.set
import java.io.File

@Service
class ConfigServerService {

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
		  if (hasUrl(appProp) && !appProp.contains("#")) {
			  appProperties.add(AppData("", "", appProp))
		  }
	  }
  }
  fun hasUrl(appProp: String) : Boolean {
	  return appProp.contains("http:") || appProp.contains("v00") || appProp.contains("paceport.pacelabs.com");
  }
  fun getAppProps(app: String, appContext: String, env: String) : List<String> {
	  try {
		  var fileName = "C://pace/gitsb/config-server/" + app + "/" + appContext + "-" + env + ".properties" 
		  return File(fileName).readLines()
	  } catch (e: Exception) {
		  return listOf<String>()
	  }
  }
  data class AppData (val appContext: String, val appEnv: String, val httpProp: String)
}