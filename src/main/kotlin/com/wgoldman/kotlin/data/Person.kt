package com.wgoldman.kotlin.data

class Person {
	val firstName: String
	val lastName: String
	constructor() {
		this.firstName = ""
		this.lastName = ""
	}
	constructor(firstName: String, lastName: String) {
		this.firstName = firstName
		this.lastName = lastName
  	}
}