package com.wgoldman.kotlin

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.beans.factory.annotation.Autowired
 import java.net.URLDecoder
import java.nio.charset.StandardCharsets

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

  @PostMapping("/saveComment", consumes=arrayOf("application/x-www-form-urlencoded;charset=UTF-8"))
  fun saveComment(model: Model, request: javax.servlet.http.HttpServletRequest): String {
    var s = org.apache.commons.io.IOUtils.toString(request.getReader())
    var fields = s.split("&")
    var comment = ""
    var age = ""
    for (field in fields) {
      var nameValue = field.split("=")
      var name = nameValue[0]
      var value = nameValue[1]
      if ("comment".equals(name)) {
        comment = URLDecoder.decode(value, StandardCharsets.UTF_8.name());
      } else {
        age = URLDecoder.decode(value, StandardCharsets.UTF_8.name());
      }
    }
    var commentForm = CommentForm(comment, age)
    model["title"] = "comment:" + commentForm.comment + ", age:" + age
    return "blog"
  }
  class CommentForm { 
    val comment: String
    val age: String
    constructor() {
       this.comment = "d"
       this.age = "4"
    }
    constructor(comment: String, age: String) {
      this.comment = comment
      this.age = age
    }
  }
  data class AppData (val appContext: String, val appEnv: String, val httpProp: String)
}