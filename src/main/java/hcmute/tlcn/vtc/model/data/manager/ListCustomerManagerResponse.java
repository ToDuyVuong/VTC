package hcmute.tlcn.vtc.model.data.manager;

import hcmute.tlcn.vtc.model.dto.CustomerDTO;
import hcmute.tlcn.vtc.model.extra.ResponseAbstract;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ListCustomerManagerResponse extends ResponseAbstract {

    private int count;
    private int size;
    private int page;
    private int totalPage;
    private List<CustomerDTO> customerDTOs;
}
