package com.example.danggeunbunny.controller;

import com.example.danggeunbunny.domain.user.presentation.UserController;
import com.example.danggeunbunny.domain.user.presentation.dto.UserDto;
import com.example.danggeunbunny.domain.user.service.UserService;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static com.example.danggeunbunny.domain.user.presentation.UserController.USER_API_URI;
import static com.example.danggeunbunny.fixture.UserFixture.USER_REGISTRATION_REQUEST;
import static org.mockito.ArgumentMatchers.anyString;
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
    void setup(WebApplicationContext applicationContext, RestDocumentationContextProvider contextProvider) {

        mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext)
                .apply(documentationConfiguration(contextProvider))
                .build();
    }

    private String toJsonString(UserDto userDto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(userDto);
    }

//    @Test
//    @DisplayName("??????????????? ????????? ?????? HTTP ???????????? 200 ??????")
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
//                                                .description("???????????? ????????? ????????? ?????????"),
//                                        fieldWithPath("password").type(JsonFieldType.STRING)
//                                                .description("?????? ????????? ????????????, ??????, ??????????????? ????????? 8??? ?????? 16??? ????????? ????????????"),
//                                        fieldWithPath("nickname").type(JsonFieldType.STRING)
//                                                .description("???????????? ?????????")
//                                )
//                        ))
//    }


    @Test
    @DisplayName("????????? ???????????? ????????? ?????? HTTP ???????????? 400??? ??????")
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
                                        .description("???????????? ????????? ????????? ?????????"),
                                fieldWithPath("password").type(JsonFieldType.STRING)
                                        .description("?????? ????????? ????????????, ??????, ??????????????? ????????? 8??? ?????? 16??? ????????? ????????????"),
                                fieldWithPath("nickname").type(JsonFieldType.STRING)
                                        .description("???????????? ?????????")
                        )
                ));

    }

//    @Test
//    @DisplayName("????????? ??????????????? ???????????? ????????? ???????????? ????????? HTTP ???????????? 200 ??????")
//    void isNotExistDuplicatedEmail() throws Exception {
//
//     when(userService.isDuplicatedEmail(UNIQUE_USER_EMAIL)).thenReturn(false);
//
//        mockMvc.perform(get(USER_API_URI + "/duplicated/{email}", UNIQUE_USER_EMAIL))
//            .andExpect(status().isOk())
//            .andDo(document("user/duplicatedEmail/success",
//                   preprocessRequest(prettyPrint()),
//    pathParameters(
//            parameterWithName("email")
//                                    .description("???????????? ????????? ????????? ?????????")
//                        )
//                                ));
//}
//
//    @Test
//    @DisplayName("????????? ??????????????? ???????????? ????????? ???????????? ???????????? HTTP ???????????? 409??? ????????????.")
//    void isExistDuplicatedEmail() throws Exception {
//        when(userService.isDuplicatedEmail(DUPLICATED_USER_EMAIL)).thenReturn(true);
//
//        mockMvc.perform(get(USER_API_URI + "/duplicated/{email}", DUPLICATED_USER_EMAIL))
//                .andExpect(status().isConflict())
//                .andDo(document("user/duplicatedEmail/fail",
//                        preprocessRequest(prettyPrint()),
//                        pathParameters(
//                                parameterWithName("email")
//                                        .description("???????????? ????????? ????????? ?????????")
//                        )
//                ));
//    }

}
