package hu.zerotohero.verseny.httpstatus;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("http-status")
public class HTTPStatusController {

    @ExceptionHandler
    public void handleException() {
        //
    }

    @GetMapping("/getStatusDescription")
    public String getStatusDescription(@RequestParam(value = "statusCode") Integer statusCode) throws Exception {
        String response = HttpStatus.valueOf(statusCode).name();
        
        HttpStatus.valueOf(400).name();
        return response;
    }
}
