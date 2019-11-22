package com.wgoldman.kotlin

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IntegrationTests(@Autowired val restTemplate: TestRestTemplate) {

  @Test
  fun `Assert blog page title, content and status code`() {
	  val entity = restTemplate.getForEntity<String>("/")
      assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
      assertThat(entity.body).contains("username")
      assertThat(entity.body).contains("<form")
      assertThat(entity.body).contains("</form>")
  }

}