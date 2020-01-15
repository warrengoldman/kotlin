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
import com.wgoldman.kotlin.data.Person

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

  @GetMapping("/persons")
  fun getPersons(model: Model): String {
    // https://github.com/warrengoldman/data/blob/master/Person.1
    var persons = mutableListOf(Person("Joe", "West"))
    persons.add(Person("Jane", "East"))
    model["title"] = "Person List"
    model["persons"] = persons
    return "persons"
  }

  @PostMapping("/saveComment", consumes=arrayOf("application/x-www-form-urlencoded;charset=UTF-8"))
  fun saveComment(model: Model, request: javax.servlet.http.HttpServletRequest): String {
    var formContent = org.apache.commons.io.IOUtils.toString(request.getReader())
    var commentForm = getCommentForm(formContent);
    model["title"] = "comment:" + commentForm.comment + ", age:" + commentForm.age
    return "blog"
  }
  fun getCommentForm(formContent: String) : CommentForm {
    var fields = formContent.split("&")
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
    return commentForm
  }

  data class AppData (val appContext: String, val appEnv: String, val httpProp: String)
}