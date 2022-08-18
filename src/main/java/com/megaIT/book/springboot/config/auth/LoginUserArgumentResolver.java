package com.megaIT.book.springboot.config.auth;

import com.megaIT.book.springboot.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor    // final 필드나 @NotNull 필드인 것의 생성자를 생성해줌
@Component  // 직접 작성한 Class 를 Bean 으로 등록
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {
                                                    // 구현체가 지정한 값으로 해당 메소드의 파라미터로 넘길 수 있다.
    private final HttpSession httpSession;

    @Override   // 컨트롤 메소드의 특정 파라미터를 지원하는지 판단
    public boolean supportsParameter(MethodParameter parameter){
        boolean isLoginUserAnnotation = parameter.getParameterAnnotation(LoginUser.class) != null;
        boolean isUserClass = SessionUser.class.equals(parameter.getParameterType());
        return isLoginUserAnnotation && isUserClass;
    }
    @Override   // 파라미터에 전달한 객체 생성
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception{
        return httpSession.getAttribute("user");
    }
}
