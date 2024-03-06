package com.study.mvc.diAndIoc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor // final을 추가하고 이 어노테이션을 추가하면 autowired대신 사용가능
// scan할때 autowired와 함께 찾게 된다., 여러개 autowired를 달아야 하는 경우 유용하다. 가독성도 상승
public class IoCService {

    private final IoCRepository ioCRepository;

    public String getJson() throws JsonProcessingException {
        Map<String, String> nameMap = ioCRepository.convertNameMap();
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(nameMap);
    }
}
