package com.reagryan.online_banking.dto.request;

import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailRequest {
    private String recipient;
    private String subject;
    private String body;
}
