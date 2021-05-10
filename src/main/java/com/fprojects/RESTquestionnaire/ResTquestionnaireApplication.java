package com.fprojects.RESTquestionnaire;

import com.cedarsoftware.util.io.JsonReader;
import com.cedarsoftware.util.io.JsonWriter;
import com.fprojects.RESTquestionnaire.dto.request.QuestionRequest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ResTquestionnaireApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResTquestionnaireApplication.class, args);
	}

}
