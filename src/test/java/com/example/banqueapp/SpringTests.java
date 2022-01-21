package com.example.banqueapp;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
class SpringTests {

	public static final String CUSTOMER_ID = "8b081381-cabc-4471-9e00-f239cfbb7f3d";
	public static final String CUSTOMER_URL =
			"http://localhost:8080/customers/" + CUSTOMER_ID;
	public static final String BALANCE_URL = CUSTOMER_URL + "/balance";
	public static final String ADD_101_URL = CUSTOMER_URL + "/add?money=101";
	private static final String WITHDRAW_99_URL = CUSTOMER_URL + "/withdraw?money=99";

	@Autowired
	MockMvc mockMvc;
	@MockBean
	AccountService mockAccountService;

	@Test
	void test() throws Exception {
		mockMvc.perform(get("/hello"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("hello Marina!")));
	}

	@Test
	void getBalanceTest() throws Exception {
		mockMvc.perform(get(BALANCE_URL))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("0")));

		verify(mockAccountService).getBalance(CUSTOMER_ID);
	}


//	@Test
//	void withdrawTest() throws Exception {
//		mockMvc.perform(get(WITHDRAW_99_URL))
//				.andDo(print())
//				.andExpect(status().isOk());
//
//		verify(mockAccountService).withdrawMoney(99, CUSTOMER_ID);
//	}

	@Test
	void addMoneyTestPost() throws Exception {
		mockMvc.perform(post(CUSTOMER_URL + "/add").content("101"))
				.andDo(print())
				.andExpect(status().isOk());

		verify(mockAccountService).addMoney(101, CUSTOMER_ID );
	}

	@Test
	void withdrawTestPost() throws Exception {
		mockMvc.perform(post(CUSTOMER_URL + "/withdraw").content("99"))
				.andDo(print())
				.andExpect(status().isOk());

		verify(mockAccountService).withdrawMoney(99, CUSTOMER_ID);
	}

}
