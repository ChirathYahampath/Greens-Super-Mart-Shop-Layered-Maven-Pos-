package dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderDTO {
    private String orderId;
    private String date;
    private String custId;
    private List<OrderDetailsDTO> details;
}
