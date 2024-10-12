package GROUP_ID.backend.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import GROUP_ID.backend.exceptions.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class DefaultController {

    @RequestMapping("/**")
    public void notFound(HttpServletRequest request) throws NotFoundException {
        throw new NotFoundException("Could not find path: '" + request.getRequestURI() + "'");
    }

}
