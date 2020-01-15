package com.wgoldman.kotlin

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
