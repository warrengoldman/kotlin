package com.wgoldman.kotlin

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType;

@Controller
class HtmlController {

  @Autowired
  lateinit var configServerService: ConfigServerService 
	
  @GetMapping("/")
  fun blog(model: Model): String {
    model["title"] = "Blog"
    return "blog"
  }

  @GetMapping("/configServerReport")
  fun configServerReport(model: Model): String {
    model["title"] = "Config Server Report"
	var httpProps = configServerService.readHttpProperties()
    model["configServerReport"] = httpProps
    return "configServerReport"
  }

  @PostMapping("/saveComment", consumes=arrayOf("text/plain"))
  fun saveComment(model: Model, @RequestBody commentForm: String): String {
	  model["title"] = "live" + commentForm
	  return "blog"
  }
  data class AppData (val appContext: String, val appEnv: String, val httpProp: String)
}