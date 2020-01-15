package com.wgoldman.kotlin

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.junit.jupiter.api.Assertions.*

@SpringBootTest
class HtmlControllerTests {

	@Test
	fun getCommentForm() {
		val userMock = HtmlController()
		val cf = userMock.getCommentForm("comment=hello&age=23")
		assertEquals("hello", cf.comment)
		assertEquals("23", cf.age)
	}

}
