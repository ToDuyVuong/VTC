package hcmute.tlcn.vtc.controller.demo;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@Hidden
@RequestMapping("/api/v1/demo-controller")
public class DemoController {

    @GetMapping
    public ResponseEntity<?> demo(@RequestBody Date date) {
        return ResponseEntity.ok(date);
    }
}
