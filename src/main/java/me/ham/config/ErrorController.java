package me.ham.config;

import me.ham.config.exception.AppError;
import me.ham.config.exception.SampleException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class ErrorController extends RuntimeException{

    @GetMapping("/error/throw")
    public String errorThrow() throws SampleException {
        throw new SampleException();
    }

    @ExceptionHandler(SampleException.class)
    public @ResponseBody AppError sampleError(SampleException sampleException){ //이 컨트롤러에서만 사용됨
        AppError appError = new AppError();
        appError.setMessage("error.app.key");
        appError.setReasone("I don't know");
        return appError;
    }

}
