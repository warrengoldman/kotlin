package com.wgoldman.kotlin

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@Controller
class HtmlController {

  @GetMapping("/")
  fun blog(model: Model): String {
    model["title"] = "Blog"
    return "blog"
  }
  @PostMapping("/saveComment")
  fun saveComment(model: Model, @RequestBody comment: CommentForm): String {
	  model["title"] = "live" + comment.comment
	  return "blog"
  }
  data class CommentForm (val comment: String)
}