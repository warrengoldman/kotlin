package com.wgoldman.kotlin.data

class Address {
	val street1: String
	val city: String
	val state: String
	val zip: Int
	constructor() {
		this.street1 = ""
		this.city = ""
		this.state = ""
		this.zip = 0
	}
	constructor(street1: String, city: String, state: String, zip: Int) {
    	this.street1 = street1
    	this.city = city
    	this.state = state
    	this.zip = zip
  	}
}