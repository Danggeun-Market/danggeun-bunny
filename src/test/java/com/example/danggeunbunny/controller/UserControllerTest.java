package com.example.danggeunbunny.controller;

import com.example.danggeunbunny.controller.user.UserController;
import com.example.danggeunbunny.dto.user.UserDto;
import com.example.danggeunbunny.model.board.post.Post;
import com.example.danggeunbunny.service.login.LoginService;
import com.example.danggeunbunny.service.user.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static com.example.danggeunbunny.controller.user.UserController.USER_API_URI;
import static com.example.danggeunbunny.fixture.UserFixture.NEW_USER;
import static com.example.danggeunbunny.fixture.UserFixture.USER_REGISTRATION_REQUEST;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(RestDocumentationExtension.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @MockBean
    private UserService userService;


    @MockBean
    private LoginService loginService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp(WebApplicationContext applicationContext, RestDocumentationContextProvider contextProvider) {

        mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext)
                .apply(documentationConfiguration(contextProvider))
                .build();
    }

    private String toJsonString(UserDto userDto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(userDto);
    }

//    @Test
//    @DisplayName("회원가입에 성공할 경우 HTTP 상태코드 200 반환")
//    void successRegistrationUser() throws Exception {
//
//
//        doNothing().when(userService).registrationUser(NEW_USER);
//
//        mockMvc.perform(post(USER_API_URI)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(toJsonString(USER_REGISTRATION_REQUEST)
//                        .andDo(print())
//                        .andExpect(status().isConflict())
//                        .andDo(document("user/create/fail",
//                                preprocessRequest(prettyPrint()),
//                                preprocessResponse(prettyPrint()),
//                                requestFields(
//                                        fieldWithPath("email").type(JsonFieldType.STRING)
//                                                .description("로그인시 사용할 사용자 이메일"),
//                                        fieldWithPath("password").type(JsonFieldType.STRING)
//                                                .description("하나 이상의 대소문자, 숫자, 특수문자를 포함한 8자 이상 16자 이하의 비밀번호"),
//                                        fieldWithPath("nickname").type(JsonFieldType.STRING)
//                                                .description("사용자의 닉네임")
//                                )
//                        ))
//    }


    @Test
    @DisplayName("중복된 이메일이 존재할 경우 HTTP 상태코드 400을 반환")
    void failToRegistrationUserByDuplicatedEmail() throws Exception {

        when(userService.isDuplicatedEmail(anyString())).thenReturn(true);

        mockMvc.perform(post(USER_API_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJsonString(USER_REGISTRATION_REQUEST)))
                .andDo(print())
                .andExpect(status().isConflict())
                .andDo(document("user/create/fail",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        requestFields(
                                fieldWithPath("email").type(JsonFieldType.STRING)
                                        .description("로그인시 사용할 사용자 이메일"),
                                fieldWithPath("password").type(JsonFieldType.STRING)
                                        .description("하나 이상의 대소문자, 숫자, 특수문자를 포함한 8자 이상 16자 이하의 비밀번호"),
                                fieldWithPath("nickname").type(JsonFieldType.STRING)
                                        .description("사용자의 닉네임")
                        )
                ));

    }
}
