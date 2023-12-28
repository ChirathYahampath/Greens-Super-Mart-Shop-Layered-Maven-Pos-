package dto;

import lombok.*;
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @Getter
    @Setter
    public class CustomerDto {
        private String code;
        private String desc;
        private double unitPrice;
        private int qty;
    }

