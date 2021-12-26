package com.example.banqueapp;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class SprintTests {
	@Autowired
	MockMvc mockMvc;
	@MockBean
	AccountService service;

	@Test
	void test() throws Exception {
		mockMvc.perform(get("/hello"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("hello Marina!")));
	}

	@Test
	void getBalanceTest() throws Exception {
		mockMvc.perform(get("/balance"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("0")));

		verify(service).getBalance();
	}
	@Test
	void addMoneyTest() throws Exception {
		mockMvc.perform(get("/add?money=100"))
				.andDo(print())
				.andExpect(status().isOk());

		verify(service).addMoney(100);
	}



}
