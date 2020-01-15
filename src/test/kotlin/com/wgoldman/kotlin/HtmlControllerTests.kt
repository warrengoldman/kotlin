package com.wgoldman.kotlin

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.junit.jupiter.api.Assertions.*
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlin.collections.List

@SpringBootTest
class HtmlControllerTests {

	@Test
	fun getCommentForm() {
		val userMock = HtmlController()
		val cf = userMock.getCommentForm("comment=hello&age=23")
		assertEquals("hello", cf.comment)
		assertEquals("23", cf.age)
	}

	@Test
	fun gson() {
//		val json = "[ \"name\", \"Baeldung\", \"java\" ]"
//		val list = Gson().fromJson(json, List::class.java)
//		assertFalse(list.isNullOrEmpty())
//		for (el in list) {
//			assertTrue("name".equals(el) || "Baeldung".equals(el) || "java".equals(el))
//		}
		val jsonCf = "{ \"comment\":\"hi\", \"age\":\"23\" }"
		val cf = Gson().fromJson(jsonCf, CommentForm::class.java)
		assertEquals("hi", cf.comment)
		assertEquals("23", cf.age)
	}
}
