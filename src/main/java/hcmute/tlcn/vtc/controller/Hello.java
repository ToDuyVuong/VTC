package hcmute.tlcn.vtc.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hcmute.tlcn.vtc.dto.AdminDTO;
import hcmute.tlcn.vtc.entity.Admin;
import hcmute.tlcn.vtc.entity.extra.Role;
import hcmute.tlcn.vtc.service.IAdminService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.util.Date;

@RequiredArgsConstructor
@RestController
@RequestMapping("/hello")
public class Hello {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private IAdminService adminService;

    @GetMapping
    public ResponseEntity<?> hello() {
        return new ResponseEntity<>("Hello Server", HttpStatus.OK);
    }

    @GetMapping("/admin")
    public ResponseEntity<Admin> testMapper(@RequestParam String us) throws JsonProcessingException {
        // Create an Admin object with the provided values
        Admin admin = new Admin(
                1L,                 // adminId
                "aa",               // username
                "bb",               // password
                "cc",               // email
                "phone_number",     // phone (replace with an actual phone number)
                "address",          // address (replace with an actual address)
                "full_name",        // fullName (replace with an actual full name)
                "avatar_url",       // avatar (replace with an actual avatar URL)
                new Date(),         // birthday (replace with an actual Date)
                Role.ADMIN,         // role (assuming Role is an enum with value ADMIN)
                OffsetDateTime.now(), // atCreate
                OffsetDateTime.now()  // atUpdate
        );
        // Convert Admin object to JSON string
        String adminJson = objectMapper.writeValueAsString(admin);

        AdminDTO dto = mapper.map(admin, AdminDTO.class);

        System.out.println("dto mapper: " + dto);
        // Print the JSON string (optional)
        System.out.println("Admin JSON: " + adminJson);

        String a = adminService.getAdmin(us);


        Admin ad2 = mapper.map(dto, Admin.class);
        ad2.setPassword(a);
        System.out.println("ad2"+ ad2);
        return ResponseEntity.ok(ad2);
    }

}
