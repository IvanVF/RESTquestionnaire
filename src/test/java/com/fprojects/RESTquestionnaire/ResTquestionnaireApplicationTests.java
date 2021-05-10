package com.fprojects.RESTquestionnaire;

import com.cedarsoftware.util.io.JsonReader;
import com.cedarsoftware.util.io.JsonWriter;
import com.fprojects.RESTquestionnaire.dto.request.QuestionRequest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ResTquestionnaireApplicationTests {

	@Test
	void contextLoads() {
		QuestionRequest q1 = new QuestionRequest("ques1", 1);
		QuestionRequest q2 = new QuestionRequest("ques2", 2);
		List<QuestionRequest> foo = new ArrayList<>();
		foo.add(0, q1);
		foo.add(1, q2);

		String jsonStr = JsonWriter.objectToJson(foo);
		System.out.println("!!!!!!!!!!!!!!!!JSON: " + jsonStr);
		QuestionRequest result = (QuestionRequest) JsonReader.jsonToJava(jsonStr);
		//assertEquals(foo.getId(),result.getId());

	}

}
