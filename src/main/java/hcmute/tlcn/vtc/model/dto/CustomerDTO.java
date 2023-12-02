package hcmute.tlcn.vtc.model.dto;

import hcmute.tlcn.vtc.model.entity.vtc.Customer;
import hcmute.tlcn.vtc.model.extra.Role;
import hcmute.tlcn.vtc.model.extra.Status;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    private Long customerId;

    private String username;

    private String email;

    private boolean gender;

    private String fullName;

//    private String phone;

    private Date birthday;


    private Status status;

    @Enumerated(EnumType.STRING)
    private Set<Role> roles;


    public static CustomerDTO convertEntityToDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerId(customer.getCustomerId());
        customerDTO.setUsername(customer.getUsername());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setGender(customer.isGender());
        customerDTO.setFullName(customer.getFullName());
        customerDTO.setBirthday(customer.getBirthday());
        customerDTO.setRoles(customer.getRoles());
        customerDTO.setStatus(customer.getStatus());
        return customerDTO;
    }


}
